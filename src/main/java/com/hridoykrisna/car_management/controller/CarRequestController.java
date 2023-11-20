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
            List<CarSchedule> carSchedules = carScheduleService.getAllRequest();
            model.addAttribute("requestList", carSchedules);
            model.addAttribute("currentUserName", CommonUtils.employee.getName());

            if (Objects.equals(CommonUtils.employee.getUser_type(), "ADMIN")){
                List<CarSchedule> pendingList = carScheduleService.getPendingList();
                model.addAttribute("pendingList", pendingList);
                List<Employee> driverList = employeeService.driverList();
                driverList.add(0, new Employee("Select Driver"));
                model.addAttribute("drivers", driverList);
                List<Car> carList = carService.getAllCar();
                carList.add(0, new Car("Select Car"));
                model.addAttribute("cars", carList);
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

    @PostMapping("/request-approve-form")
    public String requestApproveForm(@Valid @ModelAttribute("carRequestApprove") CarSchedule carSchedule){
        carScheduleService.requestApprove(carSchedule);
        return "redirect:/car-request";
    }

    @PostMapping("/request-cancel-form/{id}")
    public String requestCancelForm(@PathVariable("id") int id){
//        carScheduleService.requestApprove(carSchedule);
        return "redirect:/car-request";
    }
}
