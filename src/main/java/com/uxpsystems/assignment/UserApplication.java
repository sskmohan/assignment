package com.uxpsystems.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Assignment Application Class.
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
@EnableCaching
public class UserApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(UserApplication.class, args);
	}

}
