package com.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entity.Employee;
import com.employee.exception.EmployeeAlreadyExistsException;
import com.employee.exception.EmployeeNotFoundException;
import com.employee.service.EmployeeServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeServiceImpl serviceImpl;
	
	@GetMapping("/getemployee")
	public ResponseEntity<List<Employee>> getEmployee() throws EmployeeNotFoundException {
		return new ResponseEntity<>(serviceImpl.getEmployee(), HttpStatus.OK);
	}
	
	@GetMapping("/getemployeebyphone/{phone}")
	public ResponseEntity<Employee> getEmployee(@PathVariable long phone) throws EmployeeNotFoundException {
		return new ResponseEntity<>(serviceImpl.getEmployee(phone), HttpStatus.OK);
	}
	
	@GetMapping("/getemployeebydesignation/{designation}")
	public ResponseEntity<List<Employee>> getEmployeeByDesignation(@PathVariable String designation) throws EmployeeNotFoundException {
		return new ResponseEntity<>(serviceImpl.getEmployeeByDesignation(designation), HttpStatus.OK);
	}
	
	@PostMapping("/addemployee")
	public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee emp) throws EmployeeAlreadyExistsException {
		return new ResponseEntity<>(serviceImpl.addEmployee(emp), HttpStatus.OK);
	}
	
	
	@DeleteMapping("/removeemployee/{phone}")
	public ResponseEntity<String> removeEmployee(@PathVariable long phone) throws EmployeeNotFoundException {
		return new ResponseEntity<>(serviceImpl.removeEmployee(phone), HttpStatus.OK);

	}
	
	@PutMapping("/updateemployee/{phone}/{field}/{value}")
	public ResponseEntity<String> updateEmployee(@PathVariable long phone ,@PathVariable String field , @PathVariable Object value) throws EmployeeNotFoundException {
		return new ResponseEntity<>(serviceImpl.updateEmployee(phone , field , value), HttpStatus.OK);
	}
	
	@PutMapping("/addsalary/{phone}/{salary}")
	public ResponseEntity<String> addSalary(@PathVariable long phone ,@PathVariable int salary){
		return new ResponseEntity<>(serviceImpl.addSalary(phone , salary), HttpStatus.OK);
	}
	
	
}


