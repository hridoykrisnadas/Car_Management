package com.hridoykrisna.car_management.repository;

import com.hridoykrisna.car_management.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer> {

    List<Employee> findAllByIsActiveTrue();

    @Query(value = "from Employee where email=?1 And password=?2")
    Optional<Employee> findByEmailAndPassword(String email, String password);

}
