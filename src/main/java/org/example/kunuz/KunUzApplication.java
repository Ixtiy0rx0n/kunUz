package org.example.kunuz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class KunUzApplication {
	public static void main(String[] args) {
		SpringApplication.run(KunUzApplication.class, args);
	}

}
