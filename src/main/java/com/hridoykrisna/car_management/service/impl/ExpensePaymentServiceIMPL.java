package com.hridoykrisna.car_management.service.impl;

import com.hridoykrisna.car_management.Utils.CommonUtils;
import com.hridoykrisna.car_management.model.Employee;
import com.hridoykrisna.car_management.model.ExpensePayment;
import com.hridoykrisna.car_management.repository.EmployeeRepo;
import com.hridoykrisna.car_management.repository.ExpensePaymentRepo;
import com.hridoykrisna.car_management.service.ExpensePaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpensePaymentServiceIMPL implements ExpensePaymentService {
    private final ExpensePaymentRepo expensePaymentRepo;
    private final EmployeeRepo employeeRepo;

    @Override
    public void save(ExpensePayment expensePayment) {
        System.out.println("Expense Payment: "+expensePayment.toString());
        Optional<Employee> employee = employeeRepo.findById(expensePayment.getDriver_id());
        if (employee.isPresent()){
            expensePayment.setCreatedBy(CommonUtils.employee.getId());
//          Balance Update
            Employee driver = employee.get();
            driver.setBalance(driver.getBalance()+expensePayment.getAmount());
            driver.setUpdateBy(CommonUtils.employee.getId());
            employeeRepo.save(driver);
            expensePayment.setDriver(driver);
            expensePaymentRepo.save(expensePayment);
        }
    }

    @Override
    public List<ExpensePayment> ExpenseList() {
        return expensePaymentRepo.findAllByIsActiveTrueOrderByCreatedAtDesc();
    }
}
