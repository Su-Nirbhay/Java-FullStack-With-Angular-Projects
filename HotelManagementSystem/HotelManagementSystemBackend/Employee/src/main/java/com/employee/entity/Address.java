package com.employee.entity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
	
	@NotBlank(message = "city should not be blank")
	private String city;
	@Min(value = 100000 , message = "should be of six digits")
	@Max(value = 999999 , message = "should be of six digits")
	private int postalCode;
	@NotBlank(message = "state should not be blank")
	private String state;
	@NotBlank(message = "should not be blank")
	private String dist;
	
}
