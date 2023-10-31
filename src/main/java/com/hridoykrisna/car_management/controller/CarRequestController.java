package com.hridoykrisna.car_management.controller;

import com.hridoykrisna.car_management.Utils.CommonUtils;
import com.hridoykrisna.car_management.model.Car;
import com.hridoykrisna.car_management.model.CarSchedule;
import com.hridoykrisna.car_management.model.Employee;
import com.hridoykrisna.car_management.service.CarScheduleService;
import com.hridoykrisna.car_management.service.CarService;
import com.hridoykrisna.car_management.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;

@Controller
@ControllerAdvice
@RequiredArgsConstructor
@RequestMapping("")
public class CarRequestController {
    private final EmployeeService employeeService;
    private final CarService carService;
    private final CarScheduleService carScheduleService;


    @GetMapping({"/car-request", "/car-request/"})
    public String CarRequest(Model model){
        if (CommonUtils.isAuthenticate){
            List<Employee> employeeList = employeeService.employeeList();
            List<CarSchedule> carSchedules = carScheduleService.getAllRequest();
            model.addAttribute("requestList", carSchedules);
            model.addAttribute("employees", employeeList);
            model.addAttribute("currentUserName", CommonUtils.employee.getName());

            if (Objects.equals(CommonUtils.employee.getUser_type(), "ADMIN")){
                model.addAttribute("user_type", "ADMIN");
            }
            return "car_request.html";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/car-request-form")
    public String carRequestForm(@Valid @ModelAttribute("carRequest") CarSchedule schedule, Model model, RedirectAttributes redirectAttributes){
        carScheduleService.saveCarSchedule(schedule);
        return "redirect:/car-request";
    }
}
