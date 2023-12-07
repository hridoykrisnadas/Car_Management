package com.hridoykrisna.car_management.service;

import com.hridoykrisna.car_management.model.Employee;

import java.util.*;
public interface EmployeeService {
    List<Employee> employeeList();
    void saveEmployee(Employee employee);

    Employee getEmployeeById(int id);

    List<Employee> driverList();
}
