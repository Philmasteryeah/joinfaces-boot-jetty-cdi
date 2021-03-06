package org.philmaster.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
public class JoinFacesApplication {

	public static void main(String[] args) {

		SpringApplication.run(JoinFacesApplication.class, args);

	}
}