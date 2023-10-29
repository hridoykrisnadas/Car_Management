package com.hridoykrisna.car_management;

import com.hridoykrisna.car_management.model.Role;
import com.hridoykrisna.car_management.model.Employee;
import com.hridoykrisna.car_management.repository.EmployeeRepo;
import com.hridoykrisna.car_management.repository.RoleRepo;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
@RequiredArgsConstructor
public class CarManagementApplication {
//	private final RoleRepo roleRepo;
//	private final EmployeeRepo employeeRepo;


	public static void main(String[] args) {
		SpringApplication.run(CarManagementApplication.class, args);
	}

//	@PostConstruct
//	@Transactional
//	public void initData(){
//		Role role = new Role();
//		role.setName("ADMIN");
//		roleRepo.save(role);
//		Employee employee = new Employee();
//		employee.setName("Super Admin");
//		employee.setEmail("admin@domain.com");
//		employee.setPassword("admin");
//		employee.setRoles(Collections.singleton(role));
//		employeeRepo.save(employee);
//	}

}
