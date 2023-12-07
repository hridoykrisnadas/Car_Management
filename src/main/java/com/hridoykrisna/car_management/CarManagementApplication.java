package com.hridoykrisna.car_management;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class CarManagementApplication {


	public static void main(String[] args) {
		SpringApplication.run(CarManagementApplication.class, args);
	}
}
