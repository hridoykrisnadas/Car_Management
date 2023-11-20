package com.hridoykrisna.car_management.service;

import com.hridoykrisna.car_management.model.CarExpenses;

import java.util.List;

public interface CarExpenseService {
    void save(CarExpenses expense);

    List<CarExpenses> getAllExpenseReport();
}
