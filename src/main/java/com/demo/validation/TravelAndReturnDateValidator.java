package com.demo.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.demo.dto.BusSearchDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TravelAndReturnDateValidator implements ConstraintValidator<TravelAndReturnDateConstraint, BusSearchDto> {

	@Override
	public boolean isValid(BusSearchDto busSearchDto, ConstraintValidatorContext context) {

		// Only use the date for comparison
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		Date today = calendar.getTime();

		if (busSearchDto == null) {
			return false;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		try {
			Date travelDate = sdf.parse(busSearchDto.getTravelDate());
			
			// Your date must be after today or today
			if (travelDate == null || travelDate.before(today)) {
				return false;
			}

			if (busSearchDto.getReturnDate() != null && !travelDate.before(sdf.parse(busSearchDto.getReturnDate()))) {
				return false;
			}
			
		} catch (ParseException e) {
			log.error("Date Format error", e);
			return false;
		}

		

		return true;
	}

}
