package com.hridoykrisna.car_management.controller;

import com.hridoykrisna.car_management.Utils.CommonUtils;
import com.hridoykrisna.car_management.model.Employee;
import com.hridoykrisna.car_management.model.ExpensePayment;
import com.hridoykrisna.car_management.repository.EmployeeRepo;
import com.hridoykrisna.car_management.service.EmployeeService;
import com.hridoykrisna.car_management.service.ExpensePaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@ControllerAdvice
@RequiredArgsConstructor
@RequestMapping("")
public class ExpensePaymentController {
    private final EmployeeService employeeService;
    private final EmployeeRepo employeeRepo;
    private final ExpensePaymentService expensePaymentService;

    private Employee user;

    @GetMapping({"/expense-payment", "/expense-payment/"})
    public String payment(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            user = CommonUtils.getEmployeeByEmail(authentication.getName(), employeeRepo);
            model.addAttribute("currentUserName", user.getName());
            model.addAttribute("currentUserLogo", user.getImagePath());

            List<ExpensePayment> expensePaymentList = expensePaymentService.expenseList();
            List<Employee> driverList = employeeService.driverList();
            if (user.getUser_type().equals("DRIVER")) {
                driverList.removeIf(driver -> driver.getId() != user.getId());
                expensePaymentList = expensePaymentService.expenseListByDriver(user.getId());
            }
            model.addAttribute("expensePaymentList", expensePaymentList);
            driverList.add(0, new Employee("Select Driver"));
            model.addAttribute("drivers", driverList);
            model.addAttribute("user_type", user.getUser_type());
            return "expense_payment.html";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/expense-payment-form")
    public String savePayment(@Valid @ModelAttribute("expensePayment") ExpensePayment expensePayment, RedirectAttributes redirectAttributes) {
        expensePaymentService.save(expensePayment, user.getId());
        redirectAttributes.addFlashAttribute("success", "Expense Payment Insert Successfully.");
        return "redirect:/expense-payment";
    }
}
