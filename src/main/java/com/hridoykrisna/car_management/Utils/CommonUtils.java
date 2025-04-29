package com.hridoykrisna.car_management.Utils;

import com.hridoykrisna.car_management.model.Car;
import com.hridoykrisna.car_management.model.Employee;
import com.hridoykrisna.car_management.repository.EmployeeRepo;

import javax.naming.Context;
import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class CommonUtils {
    public static String ImagePath = "images/";
    public static Boolean isAuthenticate = false;
    public static Employee employee;
    public static List<Employee> driverList;
    public static List<Car> carList;

    public static long calculateSecondsBetween(LocalDateTime a, LocalDateTime b) {
        return Duration.between(a, b).getSeconds();
    }

    public static Employee getEmployeeByEmail(String email, EmployeeRepo employeeRepo){
        Optional<Employee> employee = employeeRepo.findByEmail(email);
        return employee.get();
    }

}
