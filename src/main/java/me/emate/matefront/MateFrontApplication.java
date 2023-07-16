package me.emate.matefront;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class MateFrontApplication {

	public static void main(String[] args) {
		SpringApplication.run(MateFrontApplication.class, args);
	}
}
