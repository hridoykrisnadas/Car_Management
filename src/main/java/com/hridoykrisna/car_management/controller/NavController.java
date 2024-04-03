package com.hridoykrisna.car_management.controller;

import com.hridoykrisna.car_management.Utils.CommonUtils;
import lombok.RequiredArgsConstructor;
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
public class NavController {


    @GetMapping("/team")
    public String team(Model model){
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            model.addAttribute("currentUserName", CommonUtils.employee.getName());
            return "team.html";
        } else {
            return "redirect:login";
        }
    }
}
