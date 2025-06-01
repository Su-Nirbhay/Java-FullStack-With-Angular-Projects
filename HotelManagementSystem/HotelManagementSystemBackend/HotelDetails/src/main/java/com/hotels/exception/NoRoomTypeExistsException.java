package com.hotels.exception;

public class NoRoomTypeExistsException extends RuntimeException{
	public NoRoomTypeExistsException(String message) {
		super(message);
	}
}
