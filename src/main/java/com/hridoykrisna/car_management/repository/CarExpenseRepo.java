package com.hridoykrisna.car_management.repository;

import com.hridoykrisna.car_management.model.CarExpenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarExpenseRepo extends JpaRepository<CarExpenses, Integer> {

    @Query(value = "from CarExpenses where isActive=true order by createdAt desc")
    List<CarExpenses> findAllByIsActiveTrueOrderByCreatedAtDesc();

    @Query(value = "select COALESCE(sum(amount), 0) from CarExpenses")
    double getTotalCarExpense();

    List<CarExpenses> findAllByDriverIdAndIsActiveTrueOrderByCreatedAtDesc(int id);
}
