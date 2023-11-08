package com.hridoykrisna.car_management;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
