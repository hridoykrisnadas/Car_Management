package com.hridoykrisna.car_management.Utils;

import com.hridoykrisna.car_management.model.Car;
import com.hridoykrisna.car_management.model.Employee;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class CommonUtils {
    public static String ImagePath = "images/";
    public static Boolean isAuthenticate = false;
    public static Employee employee;
    public static List<Employee> driverList;
    public static List<Car> carList;

    public static long calculateSecondsBetween(LocalDateTime a, LocalDateTime b) {
        return Duration.between(a, b).getSeconds();
    }
}
