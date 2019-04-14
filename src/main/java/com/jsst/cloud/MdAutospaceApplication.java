package com.jsst.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.jsst.cloud"})
public class MdAutospaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MdAutospaceApplication.class, args);
	}

}
