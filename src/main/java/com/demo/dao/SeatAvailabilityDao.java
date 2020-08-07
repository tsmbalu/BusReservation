package com.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.Booking;
import com.demo.model.Bus;
import com.demo.model.SeatAvailability;

@Repository
public interface SeatAvailabilityDao extends JpaRepository<SeatAvailability, Long>{

	public List<SeatAvailability> findByBus(Bus bus);

	public List<SeatAvailability> findByBusAndBooking(Bus bus, Booking booking);
	
}
