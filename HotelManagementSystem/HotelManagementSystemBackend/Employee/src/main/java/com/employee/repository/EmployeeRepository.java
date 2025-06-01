package com.employee.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.employee.entity.Employee;

import jakarta.validation.Valid;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String>  {
	public Employee findByPhone(long phone);
	public String deleteByPhone(long phone);
}
