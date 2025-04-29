package com.hridoykrisna.car_management.controller;

import com.hridoykrisna.car_management.Utils.CommonUtils;
import com.hridoykrisna.car_management.model.DutyPayment;
import com.hridoykrisna.car_management.model.Employee;
import com.hridoykrisna.car_management.repository.EmployeeRepo;
import com.hridoykrisna.car_management.service.DutyPaymentService;
import com.hridoykrisna.car_management.service.EmployeeService;
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
public class DutyPaymentController {
    private final DutyPaymentService dutyPaymentService;
    private final EmployeeRepo employeeRepo;
    private final EmployeeService employeeService;

    private Employee user;

    @GetMapping({"/duty-payment", "/duty-payment/"})
    public String payment(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            user = CommonUtils.getEmployeeByEmail(authentication.getName(), employeeRepo);
            model.addAttribute("currentUserName", user.getName());
            model.addAttribute("currentUserLogo", user.getImagePath());

            List<DutyPayment> dutyPaymentList = dutyPaymentService.getAll();
            List<Employee> driverList = employeeService.driverList();
            if (user.getUser_type().equals("DRIVER")) {
                driverList.removeIf(driver -> driver.getId() != user.getId());
                dutyPaymentList = dutyPaymentService.getDriverWiseDutyPaymentList(user.getId());
            }
            model.addAttribute("dutyPaymentList", dutyPaymentList);
            driverList.add(0, new Employee("Select Driver"));
            model.addAttribute("drivers", driverList);
            model.addAttribute("user_type", user.getUser_type());
            return "duty_payment.html";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/duty-payment-form")
    public String savePayment(@Valid @ModelAttribute("dutyPayment") DutyPayment dutyPayment, RedirectAttributes redirectAttributes) {
        dutyPaymentService.save(dutyPayment, user.getId());
        redirectAttributes.addFlashAttribute("success", "Expense Payment Insert Successfully.");
        return "redirect:/duty-payment";
    }
}
