package com.project.Movie.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.project.Movie.Collections.domain.entities")
public class MovieCollectionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieCollectionsApplication.class, args);
	}

}
