package com.hotels.entity;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class HotelDetails {
	
	@Id
	private String ID;
	private String name;
	private HashMap<String , LinkedHashMap<LocalDate,Integer>> roomType;	
	private HashMap<String , Integer> roomTypePrice;
	
}
