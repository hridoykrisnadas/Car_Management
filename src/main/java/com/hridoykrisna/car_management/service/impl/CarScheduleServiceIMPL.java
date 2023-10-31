package com.hridoykrisna.car_management.service.impl;

import com.hridoykrisna.car_management.Utils.CommonUtils;
import com.hridoykrisna.car_management.model.CarSchedule;
import com.hridoykrisna.car_management.repository.CarScheduleRepo;
import com.hridoykrisna.car_management.service.CarScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarScheduleServiceIMPL implements CarScheduleService {
    private final CarScheduleRepo carScheduleRepo;
    @Override
    public void saveCarSchedule(CarSchedule carSchedule) {
        carSchedule.setEmployee_id(CommonUtils.employee.getId());
        carSchedule.setEmployee_name(CommonUtils.employee.getName());
        carSchedule.setStatus(0);
        carSchedule.setCreatedBy(CommonUtils.employee.getId());
        carScheduleRepo.save(carSchedule);
    }

    @Override
    public List<CarSchedule> getAllRequest() {
        return carScheduleRepo.employeeWiseReport(CommonUtils.employee.getId());
    }
}
