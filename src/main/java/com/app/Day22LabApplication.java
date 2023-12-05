package com.app;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Day22LabApplication {

	public static void main(String[] args) {
		SpringApplication.run(Day22LabApplication.class, args);
	}
	//configure BCryptPassword encode bean
	@Bean
	public PasswordEncoder encoder()
	{
		return new BCryptPasswordEncoder();
	}

	@Bean
	public ModelMapper mapper()
	{
		return new ModelMapper();
	}
}
