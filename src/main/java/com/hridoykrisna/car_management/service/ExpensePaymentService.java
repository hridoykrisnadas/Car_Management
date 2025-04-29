package com.hridoykrisna.car_management.service;

import com.hridoykrisna.car_management.model.ExpensePayment;

import java.util.List;

public interface ExpensePaymentService {
    void save(ExpensePayment expensePayment, int id);

    List<ExpensePayment> expenseList();

    List<ExpensePayment> expenseListByDriver(int id);
}
