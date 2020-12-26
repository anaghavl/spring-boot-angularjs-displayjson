package com.userdata.display;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration

public class DisplayApplication {

	// Run this to start the application
	public static void main(String[] args) {
		SpringApplication.run(DisplayApplication.class, args);
	}

}
