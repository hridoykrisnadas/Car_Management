package com.hridoykrisna.car_management.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ControllerAdvice
@RequestMapping("/")
public class Employee {
    @GetMapping("employee")
    public String employee( ){
        return "employee_reg.html";
    }

    @PostMapping("employee-registration-form")
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee){
        System.out.println(employee.);
        return "employee_reg.html";
    }
}
