package com.demo.view;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.demo.dto.BusSearchDto;
import com.demo.model.Bus;
import com.demo.model.SeatAvailability;
import com.demo.service.BusSearchService;

@Controller
public class BusSearchController {

	@Autowired
	BusSearchService busSearchService;
	
	@GetMapping(value = "/searchForBus")
	public String searchForBus(Model model, @Valid BusSearchDto busSearchDto) throws Exception {
		
		List<Bus> towardBus = busSearchService.searchForBus(busSearchDto.getFromCity(), busSearchDto.getToCity(), busSearchDto.getTravelDate());
		List<Bus> returnBus = null;
		if(busSearchDto.getReturnDate()!=null) {
			returnBus = busSearchService.searchForBus(busSearchDto.getToCity(), busSearchDto.getFromCity(), busSearchDto.getReturnDate());
		}
		model.addAttribute("towardBus", towardBus);
		model.addAttribute("returnBus", returnBus);
		return "searchResult";
	}
	
	@GetMapping(value = "/getBusDetail")
	public String getBusDetails(Model model, Long busNumber) throws Exception {
		List<SeatAvailability> busDetail = busSearchService.getBusDetails(busNumber);
		model.addAttribute("busDetail", busDetail);
		return "busDetails";
	}
}
