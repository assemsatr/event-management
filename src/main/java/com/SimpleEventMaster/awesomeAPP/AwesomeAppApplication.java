package com.SimpleEventMaster.awesomeAPP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling//enabling scheduling, by default it is disabled, so we are enabling, and you
// can out it anywhere within the app
@SpringBootApplication
public class AwesomeAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwesomeAppApplication.class, args);
	}

}
