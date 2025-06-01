package com.employee.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Employee {
	
	@Id
	private String ID;
	@NotBlank(message = "name should not be blank")
	private String name;
	@Min(value = 18 , message = "age should be greater than or equal to 18")
	@Max(value = 60 , message = "age should be less than or equal to 60")
	private int age;
	@NotBlank(message = "email should not be blank")
	private String email;
	@Valid
	private Address address;
	@Min(value = 1000000000 , message = "should be of ten digits")
	@Max(value = 9999999999l , message = "should be of ten digits")
	private long phone;
	@NotBlank(message = "designation should not be blank")
	private String designation;
	private int salary;

}
