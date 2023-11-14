package com.hridoykrisna.car_management.controller;

import com.hridoykrisna.car_management.Utils.CommonUtils;
import com.hridoykrisna.car_management.model.CarSchedule;
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

@Controller
@ControllerAdvice
@RequiredArgsConstructor
@RequestMapping("")
public class CarScheduleController {
    private final EmployeeService employeeService;
    private final CarService carService;
    private final CarScheduleService carScheduleService;


    @GetMapping({"/car-schedule", "/car-schedule/"})
    public String CarRequest(Model model) {
        if (CommonUtils.isAuthenticate) {
            List<CarSchedule> carSchedules = carScheduleService.getAllScheduleByEmp();
            model.addAttribute("scheduleList", carSchedules);
            model.addAttribute("currentUserName", CommonUtils.employee.getName());
            return "car_schedule.html";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/add-start-time")
    public String addStartTime(RedirectAttributes attribute, @RequestParam("start_schedule_date") String startScheduleDate, @RequestParam("start_start_time") String startTime, @RequestParam("id") int scheduleId){
        carScheduleService.addStartTime(startScheduleDate, startTime, scheduleId);
        attribute.addFlashAttribute("success", "Schedule Update Successfully");
        return "redirect:/car-schedule";
    }
    @PostMapping("/add-stop-time")
    public String addStopTime(RedirectAttributes attribute, @RequestParam("stop_schedule_date") String stopScheduleDate, @RequestParam("stop_stop_time") String stopTime, @RequestParam("id") int scheduleId){
        carScheduleService.addStopTime(stopScheduleDate, stopTime, scheduleId);
        attribute.addFlashAttribute("success", "Schedule Update Successfully");
        return "redirect:/car-schedule";
    }
}
