package com.hridoykrisna.car_management.controller;

import com.hridoykrisna.car_management.Utils.CommonUtils;
import com.hridoykrisna.car_management.model.CarSchedule;
import com.hridoykrisna.car_management.model.Employee;
import com.hridoykrisna.car_management.regular_expression.RegularExpression;
import com.hridoykrisna.car_management.repository.EmployeeRepo;
import com.hridoykrisna.car_management.service.CarScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@ControllerAdvice
@RequiredArgsConstructor
@RequestMapping("")
public class ChatController {
    private final EmployeeRepo employeeRepo;
    private final CarScheduleService carScheduleService;
    private Employee user;

    @GetMapping("/chat")
    public String getAIChat(Model model) {
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            user = CommonUtils.getEmployeeByEmail(SecurityContextHolder.getContext().getAuthentication().getName(), employeeRepo);
            model.addAttribute("currentUserName", user.getName());
            return "chat.html";
        }
        else {
            return "redirect:login";
        }
    }

    @PostMapping("/input-chat")
    public String carRegistration(@Valid @RequestParam String input_text, RedirectAttributes redirectAttributes) throws Exception {
        String result = input_text;
        RegularExpression regularExpression = new RegularExpression();
        String startLocation = regularExpression.extractStartLocation(input_text);
        String endLocation = regularExpression.extractDestination(input_text);
        LocalDate date = regularExpression.extractDate(input_text);
        String time = regularExpression.extractTime(input_text);
//        Set Schedule Data
        CarSchedule schedule = new CarSchedule();
        schedule.setPickup_point(startLocation);
        schedule.setDrop_point(endLocation);
        schedule.setSchedule_date(String.valueOf(date));
        schedule.setSchedule_time(time);
        schedule.setEmployee(user);
        schedule.setEmployee_id(user.getId());
        schedule.setCreatedBy(user.getId());

        carScheduleService.saveCarSchedule(schedule);
//        redirectAttributes.addFlashAttribute("message", result);
//        redirectAttributes.addFlashAttribute("success", "success");
        return "redirect:/car-request";
    }
}
