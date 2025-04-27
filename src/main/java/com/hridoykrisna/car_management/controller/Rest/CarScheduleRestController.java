package com.hridoykrisna.car_management.controller.Rest;

import com.hridoykrisna.car_management.model.CarSchedule;
import com.hridoykrisna.car_management.service.CarScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/carSchedule")
public class CarScheduleRestController {
    private final CarScheduleService carScheduleService;

    @GetMapping("/request/pending")
    public List<CarSchedule> allPendingCarSchedule(){
        return carScheduleService.getPendingList();
    }

    @PutMapping("{id}")
    public CarSchedule cancelSchedule(@PathVariable("id") int id){
        return carScheduleService.cancelSchedule(id);
    }

}
