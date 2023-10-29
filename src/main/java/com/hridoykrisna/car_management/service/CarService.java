package com.hridoykrisna.car_management.service;

import com.hridoykrisna.car_management.model.Car;

import java.util.List;

public interface CarService {
    void saveCar(Car car);

    List<Car> getAllCar();
}
