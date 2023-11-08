package com.hridoykrisna.car_management.service.impl;

import com.hridoykrisna.car_management.Utils.CommonUtils;
import com.hridoykrisna.car_management.model.Car;
import com.hridoykrisna.car_management.model.CarSchedule;
import com.hridoykrisna.car_management.model.Employee;
import com.hridoykrisna.car_management.repository.CarRepo;
import com.hridoykrisna.car_management.repository.CarScheduleRepo;
import com.hridoykrisna.car_management.repository.EmployeeRepo;
import com.hridoykrisna.car_management.service.CarScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
}
