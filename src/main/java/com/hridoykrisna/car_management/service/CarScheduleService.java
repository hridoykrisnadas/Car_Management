package com.hridoykrisna.car_management.service;

import com.hridoykrisna.car_management.model.CarSchedule;

import java.util.List;

public interface CarScheduleService {
    void saveCarSchedule(CarSchedule carSchedule);

    List<CarSchedule> getAllRequest();

    List<CarSchedule> getPendingList();

    void requestApprove(CarSchedule carSchedule);

    void cancelSchedule(int id);
}
