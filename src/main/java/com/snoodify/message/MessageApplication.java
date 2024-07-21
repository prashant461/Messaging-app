package com.snoodify.message;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient 
@EnableKafka
public class MessageApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageApplication.class, args);
	}
	
	@Bean
	 ModelMapper modelMapper() {
		return new ModelMapper();
	}
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
