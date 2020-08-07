package com.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatAvailability {
	
	@Id
	@GeneratedValue
	private Long id; 
	
	private int seatNumber;
	
	@ManyToOne
	private Bus bus;
	
	@ManyToOne
	private Booking booking;
	
	
}
