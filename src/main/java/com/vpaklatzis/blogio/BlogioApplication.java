package com.vpaklatzis.blogio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class BlogioApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogioApplication.class, args);
	}

}
