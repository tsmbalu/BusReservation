package com.demo.service;

import static org.junit.Assert.assertSame;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.dao.BusDao;
import com.demo.dao.SeatAvailabilityDao;
import com.demo.model.Bus;

@RunWith(SpringRunner.class)
public class BusSearchServiceTest {

	@TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
 
        @Bean
        public BusSearchService busSearchService() {
            return new BusSearchService();
        }
    }
 
    @Autowired
    private BusSearchService busSearchService;
 
    @MockBean
    private BusDao busDao;
    
    @MockBean
    private SeatAvailabilityDao seatAvailabilityDao;
    
    @Test
    public void itShouldReturnBusesForSearch() throws ParseException {
    	
    	Calendar calendar = Calendar.getInstance();
    	
    	
    	String sourceCity = "Chennai";
    	String destinationCity = "Trichy";
    	String travelDate = "11-07-2020";
    	
    	calendar.set(2020, 7, 12);
    	Date arrivalTime = calendar.getTime();
    	calendar.set(2020, 7, 11);
    	Date depDate = calendar.getTime();
    	
    	List<Bus> buses = new ArrayList<Bus>();
    	
    	Bus bus = new Bus();
    	bus.setNumber(11L);
    	bus.setSourceCity(sourceCity);
    	bus.setDestinationCity(destinationCity);
    	bus.setArrivalTime(arrivalTime);
    	bus.setDepartureTime(depDate);
    	bus.setDuration(6);
    	bus.setPrice(300D);
    	bus.setOperatorName("SRM");
    	
    	buses.add(bus);
    	
        Mockito.when(busDao.findBySourceCityAndDestinationCityAndDepartureTime(sourceCity, destinationCity, depDate))
          .thenReturn(buses);
        
        
        List<Bus> result = busSearchService.searchForBus(sourceCity, destinationCity, travelDate);
        
        assertSame(buses, result);
    }
}
