package com.hridoykrisna.car_management.repository;

import com.hridoykrisna.car_management.model.DutyPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DutyPaymentRepo extends JpaRepository<DutyPayment, Integer> {

}
