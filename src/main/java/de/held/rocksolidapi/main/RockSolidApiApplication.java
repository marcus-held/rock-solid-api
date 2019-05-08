package de.held.rocksolidapi.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"de.held.rocksolidapi"})
@EnableScheduling
public class RockSolidApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RockSolidApiApplication.class, args);
	}

}
