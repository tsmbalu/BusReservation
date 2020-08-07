package com.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.BookingDao;
import com.demo.dao.BusDao;
import com.demo.dao.SeatAvailabilityDao;
import com.demo.dto.BookingDto;
import com.demo.exception.BookingFailedException;
import com.demo.model.Booking;
import com.demo.model.Bus;
import com.demo.model.SeatAvailability;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class BookingService {

	@Autowired
	BusDao busDao;

	@Autowired
	BookingDao bookingDao;

	@Autowired
	SeatAvailabilityDao seatAvailabilityDao;

	public BookingDto doBooking(BookingDto bookingDto) throws BookingFailedException {

		try {
			Optional<Bus> bus = busDao.findById(bookingDto.getBusNumber());

			if (bus.isPresent()) {
				bookingDto.setIsSeatSelected();

				double totalFare = calculateTotalFare(bus.get().getPrice(), bookingDto.getNoOfSeatBooked(),
						bookingDto.getIsSeatSelected());

				Booking booking = Booking.builder().bus(bus.get()).passengerName(bookingDto.getPassengerName())
						.totalFare(totalFare).build();

				booking = bookingDao.save(booking);

				for (int i = 0; i < bookingDto.getNoOfSeatBooked(); i++) {

					int seatNumber = 0;
					if (bookingDto.getIsSeatSelected()) {
						seatNumber = bookingDto.getSelectedSeatNumber().get(i);
					} else {
						seatNumber = allocateSeatNumber(bus.get());
					}

					SeatAvailability seatAvailability = SeatAvailability.builder().bus(bus.get()).booking(booking)
							.seatNumber(seatNumber).build();

					seatAvailabilityDao.save(seatAvailability);
				}

			} else {
				throw new BookingFailedException("Selected bus is not available for booking process");
			}
		} catch (Exception e) {
			log.error("Exception occurred while booking", e);
			throw new BookingFailedException("Error occurred while booking process");
		}

		return bookingDto;
	}

	private int allocateSeatNumber(Bus bus) throws BookingFailedException {
		List<SeatAvailability> seatAvailabilities = seatAvailabilityDao.findByBusAndBooking(bus, null);
		if (seatAvailabilities != null && seatAvailabilities.size() != 0) {
			return seatAvailabilities.get(0).getSeatNumber();
		} else {
			throw new BookingFailedException("No seats available in the selected bus");
		}

	}

	private double calculateTotalFare(Double fare, int noOfSeatBooked, boolean isSeatSelected) {
		double totalFare = fare * noOfSeatBooked;
		if (isSeatSelected) {
			totalFare = totalFare + (totalFare * 0.1);
		}
		return totalFare;
	}

}
