package com.hridoykrisna.car_management.controller;

import com.hridoykrisna.car_management.Utils.CommonUtils;
import com.hridoykrisna.car_management.model.Employee;
import com.hridoykrisna.car_management.repository.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ControllerAdvice
@RequiredArgsConstructor
@RequestMapping("")
public class ComingSoonController {
    private final EmployeeRepo employeeRepo;
    private Employee user;

    @GetMapping("/car-parking")
    public String getCarParking(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            user = CommonUtils.getEmployeeByEmail(authentication.getName(), employeeRepo);
            model.addAttribute("currentUserName", user.getName());
            model.addAttribute("currentUserLogo", user.getImagePath());
            return "coming_soon.html";
        } else {
            return "redirect:login";
        }
    }

    @GetMapping("/car-tracking")
    public String getCarTracking(Model model) {
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            user = CommonUtils.getEmployeeByEmail(SecurityContextHolder.getContext().getAuthentication().getName(), employeeRepo);
            model.addAttribute("currentUserName", user.getName());
            return "coming_soon.html";
        } else {
            return "redirect:login";
        }
    }

}
