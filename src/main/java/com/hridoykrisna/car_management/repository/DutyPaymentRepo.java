package com.hridoykrisna.car_management.repository;

import com.hridoykrisna.car_management.model.DutyPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface DutyPaymentRepo extends JpaRepository<DutyPayment, Integer> {

    @Query(value = "from DutyPayment where isActive=true order by createdAt desc")
    List<DutyPayment> getDutyPaymentListByCreatedAtOrderByDesc();
}
