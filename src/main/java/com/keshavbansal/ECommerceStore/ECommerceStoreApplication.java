package com.keshavbansal.ECommerceStore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ECommerceStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceStoreApplication.class, args);
	}

}
