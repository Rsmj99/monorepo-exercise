package com.exercise.msclientperson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsClientPersonApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsClientPersonApplication.class, args);
	}

}
