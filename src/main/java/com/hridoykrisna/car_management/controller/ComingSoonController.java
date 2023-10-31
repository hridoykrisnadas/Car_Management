package com.hridoykrisna.car_management.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ControllerAdvice
@RequiredArgsConstructor
@RequestMapping("")
public class ComingSoonController {

    @GetMapping("/car-schedule")
    public String CarSchedule(){
        return "coming_soon.html";
    }
    @GetMapping("/car-expense")
    public String CarExpense(){
        return "coming_soon.html";
    }
    @GetMapping("/payment")
    public String payment(){
        return "coming_soon.html";
    }

}
