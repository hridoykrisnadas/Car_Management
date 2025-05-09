package com.hridoykrisna.car_management.repository;

import com.hridoykrisna.car_management.model.CarSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarScheduleRepo extends JpaRepository<CarSchedule, Integer> {

    List<CarSchedule> findAllByIsActiveTrue();

    @Query(value = "from CarSchedule where employee_id=?1 order by createdAt desc")
    List<CarSchedule> employeeWiseReport(int id);

    @Query(value = "from CarSchedule where status=?1 and schedule_date is not null and schedule_time is not null and employee is not null order by createdAt desc")
    List<CarSchedule> pendingReport(int status);

    List<CarSchedule> findAllByDriverIsNotNullAndEmployeeIdOrderByCreatedAtDesc(int employeeId);

    List<CarSchedule> findAllByDriverIsNotNullAndDriverIdOrderByCreatedAtDesc(int employeeId);

    @Query(value = "SELECT COUNT(*) FROM CarSchedule WHERE driver is not null")
    int getApproveSchedule();

    @Query(value = "SELECT COUNT(*) FROM CarSchedule WHERE employee is not null AND status=0")
    int getNonApproveSchedule();

    @Query(value = "SELECT COALESCE(sum(total_bill), 0) FROM CarSchedule WHERE driver is not null")
    double getTotalBill();

    List<CarSchedule> findAllByDriverIsNotNullAndIsActiveTrueOrderByCreatedAtDesc();
}
