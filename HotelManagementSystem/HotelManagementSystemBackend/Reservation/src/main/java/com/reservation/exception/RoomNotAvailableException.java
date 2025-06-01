package com.reservation.exception;

@SuppressWarnings("serial")
public class RoomNotAvailableException extends RuntimeException{
	public RoomNotAvailableException(String message) {
		super(message);
	}
}
