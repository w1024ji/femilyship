package com.example.femilyship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// This line explicitly tells Spring to scan every package starting with "com.example.femilyship"
// This ensures that our RestExceptionHandler in the .exception package is found and activated.
@ComponentScan(basePackages = {"com.example.femilyship"})
public class FemilyshipApplication {

	public static void main(String[] args) {
		SpringApplication.run(FemilyshipApplication.class, args);
	}

}
