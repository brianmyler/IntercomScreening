package com.bm.intercomscreening.services;

import java.util.List;

import com.bm.intercomscreening.domain.Customer;

public interface ProximitySearchService {

	List<Customer> findCustomersWithinARange(double fixedPointLongitude, double fixedPointLatitude, List<Customer> customers, Integer range);
	
}
