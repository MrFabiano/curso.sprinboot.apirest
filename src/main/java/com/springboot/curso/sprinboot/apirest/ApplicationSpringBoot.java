package com.springboot.curso.sprinboot.apirest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ApplicationSpringBoot {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationSpringBoot.class, args);
	}

}
