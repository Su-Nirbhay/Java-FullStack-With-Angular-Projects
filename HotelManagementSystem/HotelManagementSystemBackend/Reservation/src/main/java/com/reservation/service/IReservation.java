package com.reservation.service;

import java.util.List;

import com.reservation.entity.ReservationUser;
import com.reservation.exception.NoReservationExistException;
import com.reservation.exception.RoomNotAvailableException;

public interface IReservation {
	
	public long makeReservation(ReservationUser user) throws RoomNotAvailableException;
	public String cancelReservation(Long BookingID) throws NoReservationExistException;
	public int calculateBill(String roomType , int room);
	public List<ReservationUser> myBookings(long contact) throws NoReservationExistException;
	public List<ReservationUser> allBookings() throws NoReservationExistException;
	public String updateStatus(long BookingId , String status);
	public String modifyBooking(long BookingId , String field , Object value);
}
