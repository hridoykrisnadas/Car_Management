package com.hridoykrisna.car_management.controller;

import com.hridoykrisna.car_management.Utils.CommonUtils;
import com.hridoykrisna.car_management.model.Employee;
import com.hridoykrisna.car_management.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@ControllerAdvice
@RequiredArgsConstructor
@RequestMapping("")
public class LoginController {
    private final LoginService loginService;
    @GetMapping({"/login", "login/"})
    public String login(){
        return "login.html";
    }

    @PostMapping("/login-form")
    public String loginForm(@Valid @ModelAttribute("login") Employee employee, Model model, RedirectAttributes redirectAttributes){
        int result = loginService.makeLogin(employee);
        if (result==1){
            return "redirect:/";
        } else {
            redirectAttributes.addFlashAttribute("failed", "Wrong Credentials, Please Enter valid Value");
            return "redirect:/login";
        }
    }

//    @GetMapping("/logout")
//    public String logout(){
//        if (CommonUtils.isAuthenticate){
//            CommonUtils.employee = null;
//            CommonUtils.isAuthenticate = false;
//        }
//        return "/login";
//    }
}
