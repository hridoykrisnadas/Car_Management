package com.hridoykrisna.car_management.service.impl;

import com.hridoykrisna.car_management.Utils.CommonUtils;
import com.hridoykrisna.car_management.model.Car;
import com.hridoykrisna.car_management.model.CarSchedule;
import com.hridoykrisna.car_management.repository.CarRepo;
import com.hridoykrisna.car_management.repository.CarScheduleRepo;
import com.hridoykrisna.car_management.repository.EmployeeRepo;
import com.hridoykrisna.car_management.service.CarScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.hridoykrisna.car_management.model.Employee;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarScheduleServiceIMPL implements CarScheduleService {
    private final CarScheduleRepo carScheduleRepo;
    private final EmployeeRepo employeeRepo;
    private final CarRepo carRepo;
    @Override
    public void saveCarSchedule(CarSchedule carSchedule) {
        Employee employee = new Employee(CommonUtils.employee.getId());
        carSchedule.setEmployee(employee);
        carSchedule.setEmployee_id(CommonUtils.employee.getId());
        carSchedule.setStatus(0);
        carSchedule.setCreatedBy(CommonUtils.employee.getId());
        carScheduleRepo.save(carSchedule);
    }

    @Override
    public List<CarSchedule> getAllRequest() {
        return carScheduleRepo.employeeWiseReport(CommonUtils.employee.getId());
//        return carScheduleRepo.findAllByIsActiveTrueOrderByCreatedAtDesc(CommonUtils.employee.getId());
    }

    @Override
    public List<CarSchedule> getPendingList() {
        List<CarSchedule> carSchedulesList = carScheduleRepo.pendingReport();
        System.out.println(carSchedulesList.toString());
        return carSchedulesList;
//        return carScheduleRepo.pendingReport();
    }

    @Override
    public void requestApprove(CarSchedule carSchedule) {
        Optional<CarSchedule> getSchedule = carScheduleRepo.findById(carSchedule.getId());
        if (getSchedule.isPresent()){
            Car car = new Car(carSchedule.getCar_id());
            getSchedule.get().setCar(car);
            getSchedule.get().setCar_id(carSchedule.getCar_id());
            Employee driver = new Employee(carSchedule.getDriver_id());
            getSchedule.get().setDriver(driver);
            getSchedule.get().setDriver_id(carSchedule.getDriver_id());
            getSchedule.get().setStatus(1);
            getSchedule.get().setUpdateBy(CommonUtils.employee.getId());
            carScheduleRepo.save(getSchedule.get());
        }
    }

    @Override
    public void cancelSchedule(int id) {
       Optional<CarSchedule> carSchedule =  carScheduleRepo.findById(id);
       if (carSchedule.isPresent()){
          carSchedule.get().setStatus(2);
          carSchedule.get().setUpdateBy(CommonUtils.employee.getId());
          carScheduleRepo.save(carSchedule.get());
       }
    }

    @Override
    public List<CarSchedule> getAllScheduleByEmp() {
        return carScheduleRepo.findAllByDriverIsNotNullAndEmployeeIdOrderByCreatedAtDesc(CommonUtils.employee.getId());
    }

    @Override
    public void addStartTime(String startScheduleDate, String startTime, int scheduleId) {
        Optional<CarSchedule> carSchedule =  carScheduleRepo.findById(scheduleId);
        if (carSchedule.isPresent()){
            carSchedule.get().setStart_time(startScheduleDate + " "+ startTime);
            carSchedule.get().setStatus(3);
            carSchedule.get().setUpdateBy(CommonUtils.employee.getId());
            carScheduleRepo.save(carSchedule.get());
        }
    }

    @Override
    public void addStopTime(String stopScheduleDate, String stopTime, int scheduleId) {
        Optional<CarSchedule> carSchedule =  carScheduleRepo.findById(scheduleId);
        if (carSchedule.isPresent()){
//            Set Data


//            Calculation Over time

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date start_Date_time = null;
            Date stop_Date_time = null;

            try {
                start_Date_time = dateFormat.parse(carSchedule.get().getStart_time());
                stop_Date_time = dateFormat.parse(stopScheduleDate + " "+ stopTime);
                System.out.println(stop_Date_time + " : "+ start_Date_time);
            } catch (ParseException e) {
                System.out.println("Exception: "+e.getMessage());
            }

            assert stop_Date_time != null;
            long duration = stop_Date_time.getTime() - start_Date_time.getTime();
            System.out.println(duration);

            long minutes = duration / (1000 * 60);
            long hours = minutes / 60;
            minutes = minutes % 60;

            float perHour = 100;
            float perMinutes = (float) 100 /60;
            float totalBill = 0;
            totalBill += hours*perHour;
            totalBill += minutes * perMinutes;

            // Print the hours and minutes
            System.out.println("Hours: " + hours);
            System.out.println("Minutes: " + minutes);
            String totalDutyTime = hours+":"+minutes;

            carSchedule.get().setStop_time(stopScheduleDate + " "+ stopTime);
            carSchedule.get().setTotal_duty_time(totalDutyTime);
            carSchedule.get().setTotal_bill(totalBill);
            carSchedule.get().setStatus(4);
            carSchedule.get().setUpdateBy(CommonUtils.employee.getId());
            carScheduleRepo.save(carSchedule.get());

//          Set on Driver Billing:
            Employee employee = carSchedule.get().getDriver();
            employee.setTotal_bill(employee.getTotal_bill()+totalBill);
            employee.setTotal_due_amount(employee.getTotal_due_amount()+totalBill);
            employee.setUpdateBy(CommonUtils.employee.getId());
            employeeRepo.save(employee);
        }
    }
}
