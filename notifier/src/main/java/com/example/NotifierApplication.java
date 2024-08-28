package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class NotifierApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(NotifierApplication.class, args);
	}

}
