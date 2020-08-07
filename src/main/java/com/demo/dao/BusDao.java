package com.demo.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.Bus;

@Repository
public interface BusDao extends JpaRepository<Bus, Long> {

	public List<Bus> findBySourceCityAndDestinationCityAndDepartureTime(String sourceCity, String destinationCity, Date departmentTime);
}
