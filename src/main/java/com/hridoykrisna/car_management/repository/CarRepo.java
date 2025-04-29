package com.hridoykrisna.car_management.repository;

import com.hridoykrisna.car_management.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepo extends JpaRepository<Car, Integer> {

    List<Car> findAllByIsActiveTrue();

    @Query(value = "from Car where isActive=true order by car_name asc")
    List<Car> findAllByIsActiveTrueOrderByCar_nameAsc();

    //    @Query(value = "Select Car.car_name from Car where id=?1")
    String findNameById(int id);
}
