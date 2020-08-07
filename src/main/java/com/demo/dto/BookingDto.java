package com.demo.dto;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.demo.validation.SeatSelectionConstraint;

import lombok.Data;

@Data
@SeatSelectionConstraint
public class BookingDto {

	private Long bookingId;
	@NotBlank(message = "{validation.error.bus.number}")
	private Long busNumber;
	@Min(value = 1, message = "{validation.error.minimum.numberOfSeat}")
	private int noOfSeatBooked;
	private List<Integer> selectedSeatNumber;
	@NotBlank(message = "{validation.error.passenger.name}")
	private String passengerName;
	private Long totalFare;
	private boolean isSeatSelected;
	
	public void setIsSeatSelected() {
		if(this.selectedSeatNumber == null || this.selectedSeatNumber.size() == 0) {
			this.isSeatSelected = false;
		} else {
			this.isSeatSelected = true;
		}
	}
	
	public boolean getIsSeatSelected() {
		return this.isSeatSelected;
	}

}
