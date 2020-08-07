package com.demo.exception;

public class BookingFailedException extends BusReservationException {

	public BookingFailedException() {
		super();
	}

	public BookingFailedException(String message) {
		super(message);
	}
 
}
