package com.employee.exception;

@SuppressWarnings("serial")
public class EmployeeAlreadyExistsException extends RuntimeException{
	public EmployeeAlreadyExistsException(String message) {
		super(message);
	}
}
