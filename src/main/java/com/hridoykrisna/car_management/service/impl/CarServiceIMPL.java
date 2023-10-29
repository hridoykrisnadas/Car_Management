package com.hridoykrisna.car_management.service.impl;

import com.hridoykrisna.car_management.model.Car;
import com.hridoykrisna.car_management.repository.CarRepo;
import com.hridoykrisna.car_management.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceIMPL implements CarService {
    private final CarRepo carRepo;
    @Override
    public void saveCar(Car car) {
        var result = carRepo.save(car);
    }

    @Override
    public List<Car> getAllCar() {
        return carRepo.findAll();
    }
}
