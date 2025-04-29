package com.hridoykrisna.car_management.controller.Rest;

import com.hridoykrisna.car_management.Utils.CommonUtils;
import com.hridoykrisna.car_management.model.CarSchedule;
import com.hridoykrisna.car_management.model.Employee;
import com.hridoykrisna.car_management.repository.EmployeeRepo;
import com.hridoykrisna.car_management.service.CarScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/carSchedule")

public class CarScheduleRestController {
    private final CarScheduleService carScheduleService;
    private final EmployeeRepo employeeRepo;

    @GetMapping("/request/pending")
    public List<CarSchedule> allPendingCarSchedule(){
        return carScheduleService.getPendingList();
    }

    @GetMapping("/all-requests")
    public List<CarSchedule> getAllRequests() {
        return carScheduleService.getAllRequests();
    }

    @PostMapping("/cancel/{id}")
    public CarSchedule cancelSchedule(@PathVariable("id") int id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Employee employee = CommonUtils.getEmployeeByEmail(authentication.getName(), employeeRepo);
            return carScheduleService.cancelSchedule(id, employee.getId());

    }

}
