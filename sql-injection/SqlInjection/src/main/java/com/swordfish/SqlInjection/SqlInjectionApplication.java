package com.swordfish.SqlInjection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class SqlInjectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SqlInjectionApplication.class, args);
	}
}
