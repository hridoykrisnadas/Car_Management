package com.hridoykrisna.car_management.repository;

import com.hridoykrisna.car_management.model.ExpensePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpensePaymentRepo extends JpaRepository<ExpensePayment, Integer> {

    List<ExpensePayment> findAllByIsActiveTrueOrderByCreatedAtDesc();
}
