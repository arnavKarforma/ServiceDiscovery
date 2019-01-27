package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class SpringService1Application {

	@Value("${service.instance.name}")
	public String instance;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringService1Application.class, args);
	}
	@RequestMapping("/")
	public String message() {

		return "Hello from"+ instance;
	}

}

