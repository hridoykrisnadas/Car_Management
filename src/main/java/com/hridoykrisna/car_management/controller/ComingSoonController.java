package com.hridoykrisna.car_management.controller;

import com.hridoykrisna.car_management.Utils.CommonUtils;
import lombok.RequiredArgsConstructor;
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
    @GetMapping("/chat")
    public String getAIChat(Model model) {
        if (CommonUtils.isAuthenticate) {
            model.addAttribute("currentUserName", CommonUtils.employee.getName());
            return "coming_soon.html";
        }
        else {
            return "redirect:login";
        }
    }

    @GetMapping("/car-parking")
    public String getCarParking(Model model) {
        if (CommonUtils.isAuthenticate) {
            model.addAttribute("currentUserName", CommonUtils.employee.getName());
            return "coming_soon.html";
        }
        else {
            return "redirect:login";
        }
    }

    @GetMapping("/car-tracking")
    public String getCarTracking(Model model) {
        if (CommonUtils.isAuthenticate) {
            model.addAttribute("currentUserName", CommonUtils.employee.getName());
            return "coming_soon.html";
        }
        else {
            return "redirect:login";
        }
    }

}
