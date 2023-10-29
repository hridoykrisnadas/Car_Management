package com.hridoykrisna.car_management.repository;

import com.hridoykrisna.car_management.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface CarRepo extends JpaRepository<Car, Integer> {

    List<Car> findAllByIsActiveTrue();

}
