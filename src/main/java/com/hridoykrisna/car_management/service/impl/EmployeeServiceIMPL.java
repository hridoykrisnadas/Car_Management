package com.hridoykrisna.car_management.service.impl;

import com.hridoykrisna.car_management.Utils.CommonUtils;
import com.hridoykrisna.car_management.model.Employee;
import com.hridoykrisna.car_management.repository.EmployeeRepo;
import com.hridoykrisna.car_management.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceIMPL implements EmployeeService {
    private final EmployeeRepo employeeRepo;

    @Override
    public List<Employee> employeeList() {
        List<Employee> employeeList = employeeRepo.findAllByIsActiveTrue();
        if (employeeList != null){
            return  employeeList;
        }else {
            return null;
        }
    }

    @Override
    public void saveEmployee(Employee employee) {
        employee.setCreatedBy(CommonUtils.employee.getId());
        var result = employeeRepo.save(employee);
    }
}
