package com.hridoykrisna.car_management.repository;

import com.hridoykrisna.car_management.model.CarExpenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarExpenseRepo extends JpaRepository<CarExpenses, Integer> {

}
