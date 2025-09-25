package com.example.femilyship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.femilyship"})
public class FemilyshipApplication {

	public static void main(String[] args) {
		SpringApplication.run(FemilyshipApplication.class, args);
	}

}
