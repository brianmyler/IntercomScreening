package com.bm.intercomscreening.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bm.intercomscreening.domain.Customer;
import com.bm.intercomscreening.services.ProximitySearchService;

@Service
public class ProximitySearchServiceImpl implements ProximitySearchService{

	public List<Customer> findCustomersWithinARange(double fixedPointLongitude, double fixedPointLatitude, List<Customer> customers, Integer kilometerRange){
		
		if(customers==null || kilometerRange==null){
			return null;
		}
		
		List<Customer> customersWithinRange = new ArrayList<Customer>();
		for(Customer c : customers){
			double distanceBetween2Points = distanceFromAGivenPoint(fixedPointLongitude, fixedPointLatitude, c.getLongitude(), c.getLatitude());
			
			if(distanceBetween2Points < kilometerRange){
				customersWithinRange.add(c);
			}
			
		}
		return customersWithinRange;
	}
	
	
	/**
	 * The formula used to work out the distance between 2 points is the haversine formula
	 * Ref - https://en.wikipedia.org/wiki/Haversine_formula
	 * 
	 */
	private double distanceFromAGivenPoint(double point1Longitude, double point1Latitude, double point2Longitude, double point2Latitude) {
		
		double earthRadius = 6371.0; 
		
		double latitudeAbsoluteDifference = Math.toRadians(point2Latitude-point1Latitude);
		double sinOfLatitudeAbsoluteDifference = Math.sin(latitudeAbsoluteDifference / 2);
		double sinOfLatitudeAbsoluteDifferenceSquared = Math.pow(sinOfLatitudeAbsoluteDifference, 2);
	    
	    double longitudeAbsoluteDifference = Math.toRadians(point2Longitude-point1Longitude);
	    double sinOfLongitudeAbsoluteDifference = Math.sin(longitudeAbsoluteDifference / 2);
	    double sinOfLongitudeAbsoluteDifferenceSquared = Math.pow(sinOfLongitudeAbsoluteDifference, 2);
	    
	    double cosOfPoint1Latitude = Math.cos(Math.toRadians(point1Latitude));
	    double cosOfPoint2Latitude = Math.cos(Math.toRadians(point2Latitude));
	    
	    double preSqrtResult = sinOfLatitudeAbsoluteDifferenceSquared + (cosOfPoint1Latitude * cosOfPoint2Latitude * sinOfLongitudeAbsoluteDifferenceSquared);
	    
	    double distance = (2*earthRadius) * Math.asin(Math.sqrt(preSqrtResult));
	    
	    return distance;
	}
}
