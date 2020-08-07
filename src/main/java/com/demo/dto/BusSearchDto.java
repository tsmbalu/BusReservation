package com.demo.dto;

import javax.validation.constraints.NotBlank;

import com.demo.validation.TravelAndReturnDateConstraint;

import lombok.Data;

@Data
@TravelAndReturnDateConstraint
public class BusSearchDto {
	
	@NotBlank(message = "{validation.error.mandatoryField}")
	private String fromCity;
	@NotBlank(message = "{validation.error.mandatoryField}")
	private String toCity;
	@NotBlank(message = "{validation.error.mandatoryField}")
	private String travelDate;
	private String returnDate;
}
