package br.com.clyvo.vitalia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class VitaliaApplication {

	public static void main(String[] args) {
		SpringApplication.run(VitaliaApplication.class, args);
	}

}
