package com.hotels.entity;

import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class IHotelDetails {
	@Id
	private String ID;
	private String name;
	private HashMap<String , Integer> roomType;	
	private HashMap<String , Integer> roomTypePrice;
}
