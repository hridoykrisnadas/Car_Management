package com.hridoykrisna.car_management.controller;

import com.hridoykrisna.car_management.Utils.CommonUtils;
import com.hridoykrisna.car_management.model.Car;
import com.hridoykrisna.car_management.model.Employee;
import com.hridoykrisna.car_management.repository.EmployeeRepo;
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
    private final EmployeeRepo employeeRepo;
    private Employee user;

    @GetMapping({"/car", "/car/"})
    public String Car(Model model){
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            System.out.println("Security Context User Email: "+SecurityContextHolder.getContext().getAuthentication().getName());
            user = CommonUtils.getEmployeeByEmail(SecurityContextHolder.getContext().getAuthentication().getName(), employeeRepo);
            model.addAttribute("currentUserName", user.getName());

            List<Car> carList = carService.getAllCar();
            model.addAttribute("cars", carList);
            model.addAttribute("currentUserName", user.getName());
            if (Objects.equals(user.getUser_type(), "ADMIN")){
                model.addAttribute("user_type", "ADMIN");
            }
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
