package com.example.FirebaseInitializer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.FirebaseInitializer")
public class FirebaseInitializerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirebaseInitializerApplication.class, args);
	}

}
