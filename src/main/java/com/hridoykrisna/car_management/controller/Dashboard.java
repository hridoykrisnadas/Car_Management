package com.hridoykrisna.car_management.controller;

import com.hridoykrisna.car_management.Utils.CommonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ControllerAdvice
@RequestMapping("/")
public class Dashboard {
    @GetMapping
    public String dashboard(){
        if (CommonUtils.isAuthenticate){
            return "dashboard.html";
        } else {
            return "redirect:login";
        }
    }
}
