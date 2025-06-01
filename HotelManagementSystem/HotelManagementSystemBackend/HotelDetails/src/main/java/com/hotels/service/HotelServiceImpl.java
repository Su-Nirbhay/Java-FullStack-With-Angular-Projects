package com.hotels.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.hotels.entity.HotelDetails;
import com.hotels.entity.IHotelDetails;
import com.hotels.exception.NoRoomTypeExistsException;
import com.hotels.repository.HotelRepository;
import com.hotels.repository.IHotelDetailsRepository;

@Service
public class HotelServiceImpl implements IHotelService {

	@Autowired
	HotelRepository hotelRepository;
	
	@Autowired
	IHotelDetailsRepository detailsRepository;

	@Override
	public String addHotelDetails(IHotelDetails IHotelDetailsObj) {
		HotelDetails hotelDetails = new HotelDetails();
		hotelDetails.setName(IHotelDetailsObj.getName());
		HashMap<String , LinkedHashMap<LocalDate,Integer>> roomTypeObj = new HashMap<>();
		HashMap<String , Integer> IroomTypeObj = IHotelDetailsObj.getRoomType();
		for(Map.Entry<String, Integer> item : IroomTypeObj.entrySet()) {
			String roomType = item.getKey();
			int rooms = item.getValue();
			addRoomTypesWithDates(roomTypeObj, roomType, rooms);
		}
		hotelDetails.setRoomType(roomTypeObj);
		hotelDetails.setRoomTypePrice(IHotelDetailsObj.getRoomTypePrice());
		hotelRepository.save(hotelDetails);
		detailsRepository.save(IHotelDetailsObj);
		return "Your Hotel Details Added Successfully";
	}

	@Override
	public IHotelDetails getHotelDetails() {
		List<IHotelDetails> IHotelDetailsObj = detailsRepository.findAll();
		return IHotelDetailsObj.get(0);
	}

	@Override
	public int getPrice(String type) {
		List<HotelDetails> hotelDetails = hotelRepository.findAll();
		HotelDetails HotelDetailsObj = hotelDetails.get(0);
		HashMap<String, Integer> map = HotelDetailsObj.getRoomTypePrice();
		return map.get(type);
	}

	@Override
	public String updatePrice(String type, int price) {
		List<HotelDetails> hotelDetails = hotelRepository.findAll();
		List<IHotelDetails> IHotelDetails = detailsRepository.findAll();
		HotelDetails HotelDetailsObj = hotelDetails.get(0);
		IHotelDetails IHotelDetailsObj = IHotelDetails.get(0);
		HashMap<String, Integer> priceHotelDetails = HotelDetailsObj.getRoomTypePrice();
		HashMap<String, Integer> priceIHotelDetails = IHotelDetailsObj.getRoomTypePrice();
		priceHotelDetails.put(type, price);
		priceIHotelDetails.put(type, price);
		HotelDetailsObj.setRoomTypePrice(priceHotelDetails);
		IHotelDetailsObj.setRoomTypePrice(priceIHotelDetails);
		hotelRepository.save(HotelDetailsObj);
		detailsRepository.save(IHotelDetailsObj);
		return "Price Updated Successfully";
	}
	
	@Override
	public LinkedHashMap<LocalDate, Integer> availability(String type) {
		HotelDetails hotelDetails = hotelRepository.findAll().get(0);
		HashMap<String , LinkedHashMap<LocalDate,Integer>> roomType = hotelDetails.getRoomType();
		LinkedHashMap<LocalDate,Integer> availablity = roomType.get(type);
		return availablity;
	}
	
	@Override
	public int checkAvailabilityWithInDate(String type, LocalDate checkIn, LocalDate checkOut) {
		HotelDetails hotelDetails = hotelRepository.findAll().get(0);
		HashMap<String , LinkedHashMap<LocalDate,Integer>> roomType = hotelDetails.getRoomType();
		LinkedHashMap<LocalDate,Integer> availablity = roomType.get(type);
		int availableRooms = Integer.MAX_VALUE;
		long numberOfNight = ChronoUnit.DAYS.between(checkIn, checkOut);
		for(int i=0;i<=numberOfNight;i++) {
			availableRooms = Math.min(availableRooms, availablity.get(checkIn.plusDays(i)));
		}
		return availableRooms;
	}

	@Override
	public String updateRoomsafterBookingOrCancelation(String type, int rooms, LocalDate checkIn, LocalDate checkOut,
			String BookingOrCancel) throws NoRoomTypeExistsException {
		HotelDetails hotelDetails = hotelRepository.findAll().get(0);
		HashMap<String , LinkedHashMap<LocalDate,Integer>> roomType = hotelDetails.getRoomType();
		LinkedHashMap<LocalDate,Integer> roomsWithDate = roomType.get(type);
		long numberOfNight = ChronoUnit.DAYS.between(checkIn, checkOut);
		if(BookingOrCancel.equalsIgnoreCase("Booking")) {
			for(int i=0;i<=numberOfNight;i++) {
				LocalDate date = checkIn.plusDays(i);
				int newRooms = roomsWithDate.get(date) - rooms;
				roomsWithDate.put(date , newRooms);
			}
		}
		if(BookingOrCancel.equalsIgnoreCase("Cancel")) {
			for(int i=0;i<=numberOfNight;i++) {
				LocalDate date = checkIn.plusDays(i);
				int newRooms = roomsWithDate.get(date) + rooms;
				roomsWithDate.put(date , newRooms);
			}
		}
		roomType.put(type , roomsWithDate);
		hotelDetails.setRoomType(roomType);
		hotelRepository.save(hotelDetails);
		return BookingOrCancel+" Successfully";
	}
	
	@Override
	public String updateRoomsOfType(String type, int room) {
		List<IHotelDetails> IHotelDetails = detailsRepository.findAll();
		IHotelDetails IHotelDetailsObj = IHotelDetails.get(0);
		HashMap<String , Integer> roomTypes = IHotelDetailsObj.getRoomType();
		roomTypes.put(type, room);
		IHotelDetailsObj.setRoomType(roomTypes);
		detailsRepository.save(IHotelDetailsObj);
		return "Rooms Updated Successfully";
	}
	
	@Override
	public String removeTypesAndRooms(String type) {
		List<IHotelDetails> IHotelDetails = detailsRepository.findAll();
		IHotelDetails IHotelDetailsObj = IHotelDetails.get(0);
		HashMap<String , Integer> roomTypes = IHotelDetailsObj.getRoomType();
		roomTypes.remove(type);
		detailsRepository.save(IHotelDetailsObj);
		return "Rooms Updated Successfully";
	}
	
	// LOGICAL METHODS , These methods are not directly called by an APIs 
	// these are used in some computations on data before saving into the database
	// or before returning to the user.
	
	public void addRoomTypesWithDates(HashMap<String , LinkedHashMap<LocalDate,Integer>> roomTypeObj , String roomType , int rooms) {
		LinkedHashMap<LocalDate, Integer> roomsWithDate = new LinkedHashMap<>();
		LocalDate today = LocalDate.now();
		for (int i = 0; i < 30; i++) {
			roomsWithDate.put(today.plusDays(i), rooms);
		}
		roomTypeObj.put(roomType, roomsWithDate);
	}

	@Scheduled(cron = "0 0 0 * * ?")
	public void addRoomsForNextDay() {
		List<HotelDetails> hotelDetails = hotelRepository.findAll();
		List<IHotelDetails> IhotelDetails= detailsRepository.findAll();
		
		HotelDetails hotelDetailsObj = hotelDetails.get(0);
		IHotelDetails IHotelDetailsObj = IhotelDetails.get(0);
		
		HashMap<String, LinkedHashMap<LocalDate, Integer>> roomTypesObj = hotelDetailsObj.getRoomType();
		for(Map.Entry<String, LinkedHashMap<LocalDate, Integer>> item : roomTypesObj.entrySet()) {
			String roomType = item.getKey();
			LinkedHashMap<LocalDate, Integer> roomsWithDate = roomTypesObj.get(roomType);
			roomsWithDate.put(LocalDate.now().plusDays(29), IHotelDetailsObj.getRoomType().get(roomType));
			roomsWithDate.remove(LocalDate.now().minusDays(1));
			roomTypesObj.put(roomType, roomsWithDate);
		}
		hotelDetailsObj.setRoomType(roomTypesObj);
		hotelRepository.save(hotelDetailsObj);
	}	
}
