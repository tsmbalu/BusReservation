package com.demo.model;

import java.util.Comparator;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
public class Bus implements Comparable<Bus> {

	@Id
	@GeneratedValue
	private Long number;
	private String operatorName;
	private String sourceCity;
	private String destinationCity;
	@Temporal(TemporalType.DATE)
	private Date departureTime;
	@Temporal(TemporalType.DATE)
	private Date arrivalTime;
	private int duration;
	private Double price;
	private int totalSeat;

	@Override
	public int compareTo(Bus bus) {
		return Double.compare(this.price, bus.price);
	}

}
