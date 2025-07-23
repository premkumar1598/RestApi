package com.Springboot.restApi;
 
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
 
 
@SpringBootApplication
@EntityScan("com.Springboot.restApi.entity")
 
public class RestApiApplication {
	
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
 
	
	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}
 
}
 
