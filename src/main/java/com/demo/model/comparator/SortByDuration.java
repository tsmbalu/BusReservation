package com.demo.model.comparator;

import java.util.Comparator;

import com.demo.model.Bus;

public class SortByDuration implements Comparator<Bus> {

	@Override
	public int compare(Bus bus1, Bus bus2) {
		return bus1.getDuration() - bus2.getDuration();
	}
}
