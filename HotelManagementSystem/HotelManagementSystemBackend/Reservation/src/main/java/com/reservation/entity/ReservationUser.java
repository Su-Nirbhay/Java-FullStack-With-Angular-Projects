package com.reservation.entity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationUser{
	
	@Id
	private Long id;
	@NotBlank(message = "name should not be blank")
	private String name;
	@NotBlank(message = "email should not be blank")
	private String email;
	@Min(value = 1000000000 , message = "should be of ten digits")
	@Max(value = 9999999999l , message = "should be of ten digits")
	private long contact;
	@NotBlank(message = "room type should not be blank")
	private String roomType;
	@NotNull(message = "room should not be blank")
	private int room;
	@NotNull(message = "date should not be blank")
	private LocalDate checkIn;
	@NotNull(message = "date should not be blank")
	private LocalDate checkOut;
	@Valid
	private List<Guest> guest;
	private int bill;
	private String Status;
	
	
	
}
