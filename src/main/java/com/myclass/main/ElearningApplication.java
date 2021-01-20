package com.myclass.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.myclass")
@EntityScan("com.myclass.entity")
@EnableJpaRepositories("com.myclass.repository")
public class ElearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElearningApplication.class, args);
	}

}
