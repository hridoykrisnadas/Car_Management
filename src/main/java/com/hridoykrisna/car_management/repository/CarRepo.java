package com.hridoykrisna.car_management.repository;

import com.hridoykrisna.car_management.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface CarRepo extends JpaRepository<Car, Integer> {

    List<Car> findAllByIsActiveTrue();

//    @Query(value = "Select Car.car_name from Car where id=?1")
    String findNameById(int id);
}
