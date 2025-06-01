package com.hotels.service;

import java.time.LocalDate;
import java.util.LinkedHashMap;

import com.hotels.entity.IHotelDetails;
import com.hotels.exception.NoRoomTypeExistsException;

public interface IHotelService {

	public String addHotelDetails(IHotelDetails hotelDetails);
	public IHotelDetails getHotelDetails();
	
	public LinkedHashMap<LocalDate , Integer> availability(String type);
	public int checkAvailabilityWithInDate(String type , LocalDate checkIn , LocalDate checkOut);
	
	public int getPrice(String type);
	public String updatePrice(String type , int price);
	
	public String updateRoomsafterBookingOrCancelation(String type , int rooms , LocalDate checkIn ,
			LocalDate checkOut , String BookingOrCancel) throws NoRoomTypeExistsException;
	public String updateRoomsOfType(String type , int room);
	public String removeTypesAndRooms(String type);
	
//	public String addTypesAndRooms(String type , int rooms);
//	public String removeTypesAndRooms(String type) throws NoRoomTypeExistsException;
	
}
