package com.hridoykrisna.car_management.controller;

import com.hridoykrisna.car_management.Utils.CommonUtils;
import com.hridoykrisna.car_management.model.Car;
import com.hridoykrisna.car_management.model.Employee;
import com.hridoykrisna.car_management.repository.EmployeeRepo;
import com.hridoykrisna.car_management.service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
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
    private final EmployeeRepo employeeRepo;
    private Employee user;

    @GetMapping({"/car", "/car/"})
    public String Car(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()){
//            System.out.println("Security Context User Email: "+SecurityContextHolder.getContext().getAuthentication().getName());
            user = CommonUtils.getEmployeeByEmail(authentication.getName(), employeeRepo);
            model.addAttribute("currentUserName", user.getName());
            model.addAttribute("currentUserLogo", user.getImagePath());

            List<Car> carList = carService.getAllCar();
            model.addAttribute("cars", carList);
            model.addAttribute("currentUserName", user.getName());
            model.addAttribute("user_type", user.getUser_type());
            return "car.html";
        } else {
            return "redirect:/login";
        }
    }
    @PostMapping("/car-registration-form")
    public String carRegistration(@Valid @ModelAttribute("car") Car car, Model model, RedirectAttributes redirectAttributes){
        car.setCreatedBy(user.getId());
        carService.saveCar(car);
        redirectAttributes.addFlashAttribute("success", "success");
        return "redirect:/car";
    }
}
