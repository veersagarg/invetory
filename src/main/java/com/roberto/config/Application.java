package com.roberto.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by roberto on 02/08/17.
 */
@SpringBootApplication(scanBasePackages = "com.roberto")
@EnableJpaRepositories(basePackages = "com.roberto.repository")
@EntityScan(basePackages = "com.roberto.model")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run( Application.class, args );
	}

}