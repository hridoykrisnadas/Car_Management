package com.hridoykrisna.car_management;

import com.hridoykrisna.car_management.model.Employee;
import com.hridoykrisna.car_management.model.Roles;
import com.hridoykrisna.car_management.repository.EmployeeRepo;
import com.hridoykrisna.car_management.repository.RoleRepo;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@RequiredArgsConstructor
public class CarManagementApplication {
	private final RoleRepo roleRepo;
	private final EmployeeRepo employeeRepo;

	public static void main(String[] args) {
		SpringApplication.run(CarManagementApplication.class, args);
	}

	@PostConstruct
	public void initData() {
		if (employeeRepo.count() == 0){
			Roles role = new Roles();
			role.setName("ADMIN");
//			roleRepo.save(role);

			Employee employee =  new Employee();
			employee.setName("Super Admin");
			employee.setDesignation("Super Admin");
			employee.setTotal_due_amount(0);
			employee.setBalance(0);
			employee.setTotal_bill(0);
			employee.setUser_type("ADMIN");
			employee.setEmail("admin@domain.com");
			employee.setPassword("$2a$10$gZ7TV3JT5iE.MqgINOGADO2hAHsyajKPDCWAnrdSsGhfrOnfdx1bq"); //admin123
			Set<Roles> roles = new HashSet<>();
			roles.add(role);
			employee.setRoles(roles);
			employeeRepo.save(employee);
		}
	}
}
