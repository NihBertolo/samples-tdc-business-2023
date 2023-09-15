package com.nicolebertolo.Microservice.Communication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroserviceCommunicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceCommunicationApplication.class, args);
	}

}
