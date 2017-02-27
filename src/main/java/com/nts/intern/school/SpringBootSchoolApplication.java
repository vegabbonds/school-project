package com.nts.intern.school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SpringBootSchoolApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootSchoolApplication.class, args);
	}
}
