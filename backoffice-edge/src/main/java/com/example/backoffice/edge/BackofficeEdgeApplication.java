package com.example.backoffice.edge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BackofficeEdgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackofficeEdgeApplication.class, args);
	}

}
