package com.hotels.exception;

public class NoRoomsExistsException extends RuntimeException{
	public NoRoomsExistsException(String message) {
		super(message);
	}
}
