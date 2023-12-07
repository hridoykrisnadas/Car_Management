package com.hridoykrisna.car_management.service.impl;

import com.hridoykrisna.car_management.Utils.CommonUtils;
import com.hridoykrisna.car_management.model.Employee;
import com.hridoykrisna.car_management.repository.EmployeeRepo;
import com.hridoykrisna.car_management.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginIMPL implements LoginService {
    private final EmployeeRepo employeeRepo;
    @Override
    public int makeLogin(Employee employee) {
        Optional<Employee> data = employeeRepo.findByEmailAndPassword(employee.getEmail(), employee.getPassword());
        if (data.isPresent()){
            CommonUtils.isAuthenticate = true;
            CommonUtils.employee = data.get();
//            System.out.println(data.get());
            return 1;
        } else {
            return 0;
        }
    }
}
