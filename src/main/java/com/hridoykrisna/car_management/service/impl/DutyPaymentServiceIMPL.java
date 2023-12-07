package com.hridoykrisna.car_management.service.impl;

import com.hridoykrisna.car_management.Utils.CommonUtils;
import com.hridoykrisna.car_management.model.DutyPayment;
import com.hridoykrisna.car_management.repository.DutyPaymentRepo;
import com.hridoykrisna.car_management.repository.EmployeeRepo;
import com.hridoykrisna.car_management.service.DutyPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.hridoykrisna.car_management.model.Employee;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DutyPaymentServiceIMPL implements DutyPaymentService {
    private final DutyPaymentRepo dutyPaymentRepo;
    private final EmployeeRepo employeeRepo;

    @Override
    public void save(DutyPayment dutyPayment) {
        Optional<Employee> employee = employeeRepo.findById(dutyPayment.getDriver_id());
        if (employee.isPresent()){
            Employee driver = employee.get();
            driver.setUpdateBy(CommonUtils.employee.getId());
            driver.setTotal_payment(driver.getTotal_payment()+dutyPayment.getAmount());
            driver.setTotal_due_amount(driver.getTotal_due_amount()-dutyPayment.getAmount());
            employeeRepo.save(driver);
            dutyPayment.setDriver(driver);
            dutyPaymentRepo.save(dutyPayment);
        }
    }

    @Override
    public List<DutyPayment> getAll() {
        return dutyPaymentRepo.getDutyPaymentListByCreatedAtOrderByDesc();
    }
}
