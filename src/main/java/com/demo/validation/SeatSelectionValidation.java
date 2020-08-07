package com.demo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.demo.dto.BookingDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SeatSelectionValidation implements ConstraintValidator<SeatSelectionConstraint, BookingDto> {

	@Override
	public boolean isValid(BookingDto bookingDto, ConstraintValidatorContext context) {

		if(bookingDto != null) {
			if(bookingDto.getNoOfSeatBooked() != bookingDto.getSelectedSeatNumber().size()) {
				return false;
			}
		}

		return true;
	}

}
