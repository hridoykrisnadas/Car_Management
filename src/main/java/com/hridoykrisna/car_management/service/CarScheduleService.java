package com.hridoykrisna.car_management.service;

import com.hridoykrisna.car_management.model.CarSchedule;

import java.util.List;

public interface CarScheduleService {

//    Car Request
    void saveCarSchedule(CarSchedule carSchedule);

    List<CarSchedule> getAllRequest();

    List<CarSchedule> getPendingList();

    void requestApprove(CarSchedule carSchedule);

    void cancelSchedule(int id);

//    Car Schedule

    List<CarSchedule> getAllScheduleByEmp();

    void addStartTime(String startScheduleDate, String startTime, int scheduleId);

    void addStopTime(String stopScheduleDate, String stopTime, int scheduleId);
}
