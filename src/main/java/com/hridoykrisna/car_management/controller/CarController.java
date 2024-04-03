package com.hridoykrisna.car_management.controller;

import com.hridoykrisna.car_management.Utils.CommonUtils;
import com.hridoykrisna.car_management.model.Car;
import com.hridoykrisna.car_management.service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
@ControllerAdvice
@RequiredArgsConstructor
@RequestMapping("")
public class CarController {
    private final CarService carService;

    @GetMapping({"/car", "/car/"})
    public String Car(Model model){
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            List<Car> carList = carService.getAllCar();
            model.addAttribute("cars", carList);
            model.addAttribute("currentUserName", CommonUtils.employee.getName());
            if (Objects.equals(CommonUtils.employee.getUser_type(), "ADMIN")){
                model.addAttribute("user_type", "ADMIN");
            }
            return "car.html";
        } else {
            return "redirect:/login";
        }
    }
    @PostMapping("/car-registration-form")
    public String carRegistration(@Valid @ModelAttribute("car") Car car, Model model, RedirectAttributes redirectAttributes){
        carService.saveCar(car);
        redirectAttributes.addFlashAttribute("success", "success");
        return "redirect:/car";
    }
}
