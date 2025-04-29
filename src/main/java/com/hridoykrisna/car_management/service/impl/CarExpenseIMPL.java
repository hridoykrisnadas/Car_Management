package com.hridoykrisna.car_management.service.impl;

import com.hridoykrisna.car_management.model.Car;
import com.hridoykrisna.car_management.model.CarExpenses;
import com.hridoykrisna.car_management.model.Employee;
import com.hridoykrisna.car_management.repository.CarExpenseRepo;
import com.hridoykrisna.car_management.repository.CarRepo;
import com.hridoykrisna.car_management.repository.EmployeeRepo;
import com.hridoykrisna.car_management.service.CarExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarExpenseIMPL implements CarExpenseService {
    private final CarExpenseRepo carExpenseRepo;
    private final EmployeeRepo employeeRepo;
    private final CarRepo carRepo;

    @Override
    public void save(CarExpenses carExpenses, int id) {
        System.out.println("Expense Data: " + carExpenses);
        carExpenses.setCreatedBy(id);
        Optional<Employee> driver = employeeRepo.findById(carExpenses.getDriver_id());
        if (driver.isPresent()) {
            Employee driver1 = driver.get();
            driver.get().setBalance(driver1.getBalance() - carExpenses.getAmount());
            driver.get().setUpdateBy(id);
            employeeRepo.save(driver1);
            carExpenses.setDriver(driver1);
            Optional<Car> car = carRepo.findById(carExpenses.getCar_id());
            carExpenses.setCar(car.get());
            carExpenseRepo.save(carExpenses);
        }
    }

    @Override
    public List<CarExpenses> getAllExpenseReport() {
        return carExpenseRepo.findAllByIsActiveTrueOrderByCreatedAtDesc();
    }

    @Override
    public List<CarExpenses> getDriveWiseExpenseReport(int id) {
        return carExpenseRepo.findAllByDriverIdAndIsActiveTrueOrderByCreatedAtDesc(id);
    }
}
