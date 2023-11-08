package com.hridoykrisna.car_management.repository;

import com.hridoykrisna.car_management.model.CarSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarScheduleRepo extends JpaRepository<CarSchedule, Integer> {

    List<CarSchedule> findAllByIsActiveTrue();

    @Query(value = "from CarSchedule where employee_id=?1")
    List<CarSchedule> employeeWiseReport(int id);

    @Query(value = "from CarSchedule where status=0")
    List<CarSchedule> pendingReport();

}
