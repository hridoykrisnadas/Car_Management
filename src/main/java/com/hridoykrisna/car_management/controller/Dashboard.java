package com.hridoykrisna.car_management.controller;

import com.hridoykrisna.car_management.Utils.CommonUtils;
import com.hridoykrisna.car_management.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ControllerAdvice
@RequiredArgsConstructor
@RequestMapping("/")
public class Dashboard {
    private final EmployeeRepo employeeRepo;
    private final CarRepo carRepo;
    private final CarScheduleRepo carScheduleRepo;
    private final DutyPaymentRepo dutyPaymentRepo;
    private final CarExpenseRepo carExpenseRepo;
    private final ExpensePaymentRepo expensePaymentRepo;


    @GetMapping
    public String dashboard(Model model){
        if (CommonUtils.isAuthenticate){
            model.addAttribute("currentUserName", CommonUtils.employee.getName());
            long totalEmp = employeeRepo.count();
            int totalDriver = employeeRepo.getTotalDriver("DRIVER");
            model.addAttribute("totalEmployee", totalEmp);
            model.addAttribute("totalDriver", totalDriver);
            model.addAttribute("totalOfficer", totalEmp-totalDriver);
            model.addAttribute("totalCar", carRepo.count());
            model.addAttribute("totalApproveSchedule", carScheduleRepo.getApproveSchedule());
            model.addAttribute("totalNonApproveSchedule", carScheduleRepo.getNonApproveSchedule());
            model.addAttribute("totalCarExpense", carExpenseRepo.getTotalCarExpense());
            model.addAttribute("totalDriverBill", carScheduleRepo.getTotalBill());
            return "dashboard.html";
        } else {
            return "redirect:login";
        }
    }
}
