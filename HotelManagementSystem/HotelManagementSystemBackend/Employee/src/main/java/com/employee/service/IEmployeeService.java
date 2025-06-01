package com.employee.service;

import java.util.List;

import com.employee.entity.Employee;
import com.employee.exception.EmployeeAlreadyExistsException;
import com.employee.exception.EmployeeNotFoundException;


public interface IEmployeeService {
	
	public Employee addEmployee(Employee emp) throws EmployeeAlreadyExistsException;
	public Employee getEmployee(long phone) throws EmployeeNotFoundException;
	public List<Employee> getEmployee() throws EmployeeNotFoundException;
	public List<Employee> getEmployeeByDesignation(String designation) throws EmployeeNotFoundException;
	public String removeEmployee(long phone) throws EmployeeNotFoundException;
	public String updateEmployee(long phone , String field , Object value) throws EmployeeNotFoundException;
	public String addSalary(long phone, int salary) throws EmployeeNotFoundException;
}
