package com.hridoykrisna.car_management.controller;

import com.hridoykrisna.car_management.Utils.CommonUtils;
import com.hridoykrisna.car_management.model.CarSchedule;
import com.hridoykrisna.car_management.model.Employee;
import com.hridoykrisna.car_management.repository.EmployeeRepo;
import com.hridoykrisna.car_management.service.CarScheduleService;
import com.hridoykrisna.car_management.service.CarService;
import com.hridoykrisna.car_management.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@ControllerAdvice
@RequiredArgsConstructor
@RequestMapping("")
public class CarScheduleController {
    private final EmployeeService employeeService;
    private final EmployeeRepo employeeRepo;
    private final CarService carService;
    private final CarScheduleService carScheduleService;

    private Employee user;

    @GetMapping({"/car-schedule", "/car-schedule/"})
    public String CarRequest(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            user = CommonUtils.getEmployeeByEmail(authentication.getName(), employeeRepo);

//            Need to collect Driver Wise
            List<CarSchedule> carSchedules;
            if (user.getUser_type().equals("DRIVER")) {
                carSchedules = carScheduleService.getAllScheduleByDriver(user.getId());
            } else {
                carSchedules = carScheduleService.getAllScheduleByEmp(user.getId());
            }
            model.addAttribute("scheduleList", carSchedules);
            model.addAttribute("currentUserName", user.getName());
            model.addAttribute("currentUserId", user.getId());
            model.addAttribute("currentUserLogo", user.getImagePath());
            model.addAttribute("user_type", user.getUser_type());
            return "car_schedule.html";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/add-start-time")
    public String addStartTime(RedirectAttributes attribute, @RequestParam("start_schedule_date") String startScheduleDate, @RequestParam("start_start_time") String startTime, @RequestParam("id") int scheduleId) {
        carScheduleService.addStartTime(startScheduleDate, startTime, scheduleId, user.getId());
        attribute.addFlashAttribute("success", "Schedule Update Successfully");
        return "redirect:/car-schedule";
    }

    @PostMapping("/add-stop-time")
    public String addStopTime(RedirectAttributes attribute, @RequestParam("stop_schedule_date") String stopScheduleDate, @RequestParam("stop_stop_time") String stopTime, @RequestParam("id") int scheduleId) {
        CarSchedule carSchedule = carScheduleService.addStopTime(stopScheduleDate, stopTime, scheduleId, user.getId());
        attribute.addFlashAttribute("success", carSchedule.getStop_time());
        System.out.println("Car Schedule 1: "+ carSchedule.toString());
        return "redirect:/car-schedule";
    }
}
