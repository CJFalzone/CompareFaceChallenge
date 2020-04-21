package com.facialapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class FacialRecognitionEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FacialRecognitionEurekaApplication.class, args);
	}

}
