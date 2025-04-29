package com.hridoykrisna.car_management.service;

import com.hridoykrisna.car_management.model.CarExpenses;

import java.util.List;

public interface CarExpenseService {
    void save(CarExpenses expense, int id);

    List<CarExpenses> getAllExpenseReport();

    List<CarExpenses> getDriveWiseExpenseReport(int id);
}
