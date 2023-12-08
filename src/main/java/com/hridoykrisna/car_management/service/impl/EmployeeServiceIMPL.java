package com.hridoykrisna.car_management.service.impl;

import com.hridoykrisna.car_management.model.Employee;
import com.hridoykrisna.car_management.repository.EmployeeRepo;
import com.hridoykrisna.car_management.service.EmployeeService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import lombok.extern.slf4j.XSlf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceIMPL implements EmployeeService {
    private final EmployeeRepo employeeRepo;

    @Override
    public List<Employee> employeeList() {
        return employeeRepo.findAllByIsActiveTrueOrderByNameAsc();
    }

    @Override
    public void saveEmployee(Employee employee) {
        try {
            Employee savedEmployee = employeeRepo.save(employee);
            // Perform actions after successful save, like logging or sending notifications.
            System.out.println("Employee saved successfully with ID: "+ savedEmployee.getId());
        } catch (DataIntegrityViolationException e) {
            // Handle unique constraint violation exception.
            System.out.println("Error saving employee: "+ e.getMessage());
            // Perform error handling actions, like displaying a user-friendly message.
        } catch (Exception e) {
            // Handle any other unexpected exceptions.
            System.out.println("Unexpected error saving employee: {}"+ e.getMessage());
        }
    }

    @Override
    public Employee getEmployeeById(int id) {
        Optional<Employee> employee = employeeRepo.findById(id);
        return employee.orElse(null);
    }

    @Override
    public List<Employee> driverList() {
        return employeeRepo.findByDriver();
    }
}
