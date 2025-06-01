package com.hotels;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HotelDetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelDetailsApplication.class, args);
	}

}
