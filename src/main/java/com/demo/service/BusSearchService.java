package com.demo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.BusDao;
import com.demo.dao.SeatAvailabilityDao;
import com.demo.model.Bus;
import com.demo.model.SeatAvailability;

@Service
public class BusSearchService {

	@Autowired
	BusDao busDao;

	@Autowired
	SeatAvailabilityDao seatAvailabilityDao;

	public List<Bus> searchForBus(String sourceCity, String destinationCity, String travelDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		List<Bus> bus = busDao.findBySourceCityAndDestinationCityAndDepartureTime(sourceCity, destinationCity,
				sdf.parse(travelDate));
		Collections.sort(bus);
		return bus;
	}

	public List<SeatAvailability> getBusDetails(Long busNumber) {
		List<SeatAvailability> busSeatStatus = null;
		Optional<Bus> bus = busDao.findById(busNumber);

		if (bus.isPresent()) {
			busSeatStatus = seatAvailabilityDao.findByBus(bus.get());
		}
		return busSeatStatus;
	}

}
