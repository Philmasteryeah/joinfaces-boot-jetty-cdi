package org.philmaster.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.philmaster.boot")
public class JoinFacesApplication {

	public static void main(String[] args) {
		SpringApplication.run(JoinFacesApplication.class, args);
	}

}
