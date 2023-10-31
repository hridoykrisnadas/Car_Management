package com.hridoykrisna.car_management.controller;

import com.hridoykrisna.car_management.Utils.CommonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ControllerAdvice
@RequestMapping("/")
public class Dashboard {
    @GetMapping
    public String dashboard(Model model){
        if (CommonUtils.isAuthenticate){
            model.addAttribute("currentUserName", CommonUtils.employee.getName());

            return "dashboard.html";
        } else {
            return "redirect:login";
        }
    }
}
