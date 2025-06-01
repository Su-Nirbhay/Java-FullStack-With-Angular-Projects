package com.reservation.exception;
@SuppressWarnings("serial")
public class NoReservationExistException  extends RuntimeException{
	public NoReservationExistException(String message) {
		super(message);
	}
}
