package com.hridoykrisna.car_management.repository;

import com.hridoykrisna.car_management.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Roles, Integer> {

}
