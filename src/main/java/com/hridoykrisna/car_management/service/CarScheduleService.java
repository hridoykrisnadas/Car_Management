package com.hridoykrisna.car_management.service;

import com.hridoykrisna.car_management.model.CarSchedule;

import java.util.List;

public interface CarScheduleService {

//    Car Request
    void saveCarSchedule(CarSchedule carSchedule);

    List<CarSchedule> getAllRequest(int id);

    List<CarSchedule> getPendingList();

    void requestApprove(CarSchedule carSchedule);

    void cancelSchedule(int id);

//    Car Schedule

    List<CarSchedule> getAllScheduleByEmp(int id);

    void addStartTime(String startScheduleDate, String startTime, int scheduleId, int id);

    void addStopTime(String stopScheduleDate, String stopTime, int scheduleId, int id);
}
