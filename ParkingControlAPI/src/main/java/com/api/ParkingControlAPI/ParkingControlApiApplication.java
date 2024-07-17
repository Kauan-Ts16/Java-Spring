package com.api.ParkingControlAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ParkingControlApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingControlApiApplication.class, args);
//		System.out.println(new BCryptPasswordEncoder().encode("1109"));
//		System.out.println(new BCryptPasswordEncoder().encode("1027"));
	}

}
