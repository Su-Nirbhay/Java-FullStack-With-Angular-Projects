package com.reservation.communication;

import java.time.LocalDate;
import java.util.LinkedHashMap;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.reservation.config.FeignClientConfig;

@FeignClient(name = "HotelDetails" , url = "http://localhost:9091/hotel" , configuration = FeignClientConfig.class)
public interface HotelDetailsService {
	
	@GetMapping("/getprice/{type}")
	public int getPrice(@PathVariable String type);
	
	@GetMapping("/availability/{type}")
	public LinkedHashMap<LocalDate , Integer> availability(@PathVariable String type);
	
	@GetMapping("/checkavailability/{type}/{checkIn}/{checkOut}")
	public int checkAvailability(@PathVariable String type , @PathVariable LocalDate checkIn ,
			@PathVariable LocalDate checkOut);
	
	@PutMapping("/updateroomsafterbookingorcancelation/{type}/{rooms}/{checkIn}/{checkOut}/{BookingOrCancel}")
	public String updateRoomsafterBookingOrCancelation(@PathVariable String type , @PathVariable int rooms ,
			@PathVariable LocalDate checkIn , @PathVariable LocalDate checkOut , @PathVariable String BookingOrCancel); 
}
