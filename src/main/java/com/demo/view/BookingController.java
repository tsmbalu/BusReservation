package com.demo.view;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.demo.dao.BookingDao;
import com.demo.dto.BookingDto;
import com.demo.exception.BusReservationException;
import com.demo.service.BookingService;

@Controller
public class BookingController {
	
	@Autowired
	BookingService bookingService;
	
	@PostMapping(value = "/booking")
	public String doBooking(Model model, @RequestBody BookingDto bookingDto) {
		try {
			BookingDto bookingDetail = bookingService.doBooking(bookingDto);
			model.addAttribute("bookingDetail", bookingDetail);
		} catch (BusReservationException e) {
			model.addAttribute("bookingDetail", null);
			model.addAttribute("errorMessage", e.getMessage());
		}
		
		return "bookingStatus";
	}
}
