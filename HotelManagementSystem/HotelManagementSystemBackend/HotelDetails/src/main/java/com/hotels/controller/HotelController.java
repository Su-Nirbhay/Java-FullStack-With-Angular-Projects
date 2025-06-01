package com.hotels.controller;


import java.time.LocalDate;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotels.entity.IHotelDetails;
import com.hotels.service.HotelServiceImpl;

@RestController
@RequestMapping("/hotel")
@CrossOrigin(origins = "http://localhost:4200")
public class HotelController {

	@Autowired
	HotelServiceImpl hotelServiceImpl;
	
	@PostMapping("/addhoteldetails") 
	public ResponseEntity<String> addHotelDetails(@RequestBody IHotelDetails IHotelDetailsObj){
		return new ResponseEntity<>(hotelServiceImpl.addHotelDetails(IHotelDetailsObj) , HttpStatus.OK);
	}
	
	@GetMapping("/gethoteldetails")
	public ResponseEntity<IHotelDetails> getHotelDetails() {
		return new ResponseEntity<>(hotelServiceImpl.getHotelDetails() , HttpStatus.OK);
	}
	
	@GetMapping("/getprice/{type}")
	public ResponseEntity<Integer> getPrice(@PathVariable String type) {
		System.out.println("REQUEST COMING");
		return new ResponseEntity<>(hotelServiceImpl.getPrice(type) , HttpStatus.OK);
	}
	
	@PutMapping("/updatePrice/{type}/{price}")
	public ResponseEntity<String> updatePrice(@PathVariable String type , @PathVariable int price){
		return new ResponseEntity<>(hotelServiceImpl.updatePrice(type , price) , HttpStatus.OK);
	}
	
	@GetMapping("/checkavailability/{type}/{checkIn}/{checkOut}")
	public ResponseEntity<Integer> checkAvailability(@PathVariable String type , @PathVariable LocalDate checkIn , @PathVariable LocalDate checkOut) {
		return new ResponseEntity<>(hotelServiceImpl.checkAvailabilityWithInDate(type , checkIn , checkOut) , HttpStatus.OK);
	}
	
	
	@GetMapping("/availability/{type}")
	public ResponseEntity<LinkedHashMap<LocalDate , Integer>> availability(@PathVariable String type) {
		return new ResponseEntity<>(hotelServiceImpl.availability(type) , HttpStatus.OK);
	}
	
	@PutMapping("/updateroomsafterbookingorcancelation/{type}/{rooms}/{checkIn}/{checkOut}/{BookingOrCancel}")
	public ResponseEntity<String> updateRoomsafterBookingOrCancelation(@PathVariable String type , @PathVariable int rooms , @PathVariable LocalDate checkIn , @PathVariable LocalDate checkOut , @PathVariable String BookingOrCancel) {
		return new ResponseEntity<>(hotelServiceImpl.updateRoomsafterBookingOrCancelation(type , rooms , checkIn , checkOut , BookingOrCancel) , HttpStatus.OK);
	}
	
	@PutMapping("/updaterooms/{type}/{rooms}")
	public ResponseEntity<String> updateRoomsOfType(@PathVariable String type , @PathVariable int rooms){
		return new ResponseEntity<>(hotelServiceImpl.updateRoomsOfType(type , rooms) , HttpStatus.OK);
	}
	
	@DeleteMapping("/removetypesandrooms/{type}")
	public ResponseEntity<String> removeTypesAndRooms(@PathVariable String type){
		return new ResponseEntity<>(hotelServiceImpl.removeTypesAndRooms(type) , HttpStatus.OK);
	}
	

	
}
