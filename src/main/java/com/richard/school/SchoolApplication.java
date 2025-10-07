package com.richard.school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= {"com.richard.school",
		"com.richard.school.controller",
		"com.richard.school.model",
		"com.richard.school.repository",
		"com.richard.school.service"})
public class SchoolApplication {
	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);
	}

}
