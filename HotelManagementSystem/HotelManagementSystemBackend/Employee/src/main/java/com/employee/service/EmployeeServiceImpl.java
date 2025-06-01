package com.employee.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.entity.Employee;
import com.employee.exception.EmployeeAlreadyExistsException;
import com.employee.exception.EmployeeNotFoundException;
import com.employee.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImpl implements IEmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public Employee addEmployee(Employee emp) throws EmployeeAlreadyExistsException {
		Employee obj = employeeRepository.findByPhone(emp.getPhone());
		if(obj == null) {
			log.info("Added Successfully");
			return employeeRepository.save(emp);
		}
		log.error("Already Exists");
		throw new EmployeeAlreadyExistsException("Already exists");			
	}

	@Override
	public List<Employee> getEmployee() throws EmployeeNotFoundException {
		List<Employee> allEmp = employeeRepository.findAll();
		if(!allEmp.isEmpty()) {
			log.info("Fetched Successfully");
			return allEmp;
		}
		throw new EmployeeNotFoundException("No Data Found");
	}
	
	@Override
	public Employee getEmployee(long phone) throws EmployeeNotFoundException {
		Employee emp = employeeRepository.findByPhone(phone);
		if(emp != null) {
			return emp;
		}
		throw new EmployeeNotFoundException("No Data Found");
	}

	@Override
	public String removeEmployee(long phone) {
		Employee emp = employeeRepository.findByPhone(phone);
		if(emp != null) {
			employeeRepository.deleteByPhone(phone);
			log.info("Removed Successfully");
			return "Removed Successfully";
		}
		throw new EmployeeNotFoundException("No Data Found");
	}

	@Override
	public String updateEmployee(long phone ,String field , Object value) throws EmployeeNotFoundException, EmployeeAlreadyExistsException {
		Employee emp = employeeRepository.findByPhone(phone);
		if(emp != null) {
			if(field.equals("age")) {
				int age = Integer.parseInt((String)value);
				emp.setAge(age);
			}
			if(field.equals("email")) {
				emp.setEmail((String)value);
			}
			if(field.equals("designation")) {
				emp.setDesignation((String)value);
			}
			if(field.equals("city")) {
				emp.getAddress().setCity((String)value);
			}
			if(field.equals("postalCode")) {
				int postalCode = Integer.parseInt((String)value);
				emp.getAddress().setPostalCode(postalCode);
			}
			if(field.equals("state")) {
				emp.getAddress().setState((String)value);
			}
			if(field.equals("dist")) {
				emp.getAddress().setDist((String)value);
			}
			if(field.equals("phone")) {
				long contact = Long.parseLong((String)value);
				Employee obj = employeeRepository.findByPhone(contact);
				if(obj == null)
					emp.setPhone(contact);
				else
					throw new EmployeeAlreadyExistsException("Already exists");
			}
			employeeRepository.save(emp);
			return "Data Updated";
		}
		throw new EmployeeNotFoundException("No Data Found");
	}

	@Override
	public List<Employee> getEmployeeByDesignation(String designation) throws EmployeeNotFoundException {
		List<Employee> result = employeeRepository.findAll().stream().filter(obj -> obj.getDesignation().equals(designation)).collect(Collectors.toList());
		if(!result.isEmpty()) {	
			return result;
		}
		throw new EmployeeNotFoundException("No Data Found");
	}

	@Override
	public String addSalary(long phone, int salary) throws EmployeeNotFoundException{
		Employee emp = employeeRepository.findByPhone(phone);
		if(emp != null) {			
			emp.setSalary(salary);
			return "Salary Updated";
		}
		throw new EmployeeNotFoundException("No Data Found");
	}
	
		
}
