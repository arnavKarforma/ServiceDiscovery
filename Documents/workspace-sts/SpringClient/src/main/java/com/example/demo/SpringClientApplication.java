package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class SpringClientApplication {

	@Autowired
	RestTemplateBuilder restTemplate;
	
	@Autowired
	EurekaClient eurekaClient;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringClientApplication.class, args);
	}
	
	@RequestMapping("/")
	public String getMessage() {
		InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("service1", false);
		String baseUrl = instanceInfo.getHomePageUrl();
		ResponseEntity<String> res = restTemplate.build().exchange(baseUrl, HttpMethod.GET, null, String.class);
		return res.getBody();
	}

}

