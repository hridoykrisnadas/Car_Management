package com.hridoykrisna.car_management.service.impl;

import com.hridoykrisna.car_management.model.Employee;
import com.hridoykrisna.car_management.repository.EmployeeRepo;
import com.hridoykrisna.car_management.service.EmployeeService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import lombok.extern.slf4j.XSlf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceIMPL implements EmployeeService, UserDetailsService {
    private final EmployeeRepo employeeRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<Employee> employeeList() {
        return employeeRepo.findAllByIsActiveTrueOrderByNameAsc();
    }



    @Override
    public void saveEmployee(Employee employee) {
        try {
            Employee savedEmployee = employeeRepo.save(employee);
            System.out.println("Employee saved successfully with ID: "+ savedEmployee.getId());
        } catch (DataIntegrityViolationException e) {
            System.out.println("Error saving employee: "+ e.getMessage());
        } catch (Exception e) {
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


    @Override
    public UserDetails loadUserByUsername(String email)  {
        Employee employee = employeeRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Username or Email Not Found in Database"));

        Set<GrantedAuthority> authoritySet = employee.getRoles().stream()
                .map(roles -> new SimpleGrantedAuthority(roles.getName()))
                .collect(Collectors.toSet());

        return new User(
                email,
                employee.getPassword(),
                authoritySet
        );
    }

}
