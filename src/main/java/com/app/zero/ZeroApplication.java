package com.app.zero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ZeroApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZeroApplication.class, args);
	}

}
