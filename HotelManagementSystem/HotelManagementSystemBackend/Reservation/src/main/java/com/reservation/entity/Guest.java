package com.reservation.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Guest {
	@NotBlank(message = "name should not be blank")
	private String name;
	@Min(value = 1 , message = "should be greater than one")
	private int age;
}
