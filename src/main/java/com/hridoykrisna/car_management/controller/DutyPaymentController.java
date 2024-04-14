package com.hridoykrisna.car_management.controller;

import com.hridoykrisna.car_management.Utils.CommonUtils;
import com.hridoykrisna.car_management.model.DutyPayment;
import com.hridoykrisna.car_management.model.Employee;
import com.hridoykrisna.car_management.repository.EmployeeRepo;
import com.hridoykrisna.car_management.service.DutyPaymentService;
import com.hridoykrisna.car_management.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;

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
    public String payment(Model model){
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            user = CommonUtils.getEmployeeByEmail(SecurityContextHolder.getContext().getAuthentication().getName(), employeeRepo);
            model.addAttribute("currentUserName", user.getName());
            List<DutyPayment> dutyPaymentList = dutyPaymentService.getAll();
            model.addAttribute("dutyPaymentList", dutyPaymentList);
            List<Employee> driverList = employeeService.driverList();
            driverList.add(0, new Employee("Select Driver"));
            model.addAttribute("drivers", driverList);
            if (Objects.equals(user.getUser_type(), "ADMIN")){
                model.addAttribute("user_type", "ADMIN");
            }
            return "duty_payment.html";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/duty-payment-form")
    public String savePayment(@Valid @ModelAttribute("dutyPayment") DutyPayment dutyPayment, RedirectAttributes redirectAttributes){
        dutyPaymentService.save(dutyPayment, user.getId());
        redirectAttributes.addFlashAttribute("success", "Expense Payment Insert Successfully.");
        return "redirect:/duty-payment";
    }
}
