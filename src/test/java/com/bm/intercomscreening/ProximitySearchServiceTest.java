package com.bm.intercomscreening;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bm.intercomscreening.domain.Customer;
import com.bm.intercomscreening.services.ProximitySearchService;
import com.bm.intercomscreening.services.impl.ProximitySearchServiceImpl;

public class ProximitySearchServiceTest {

	private static final double INTERCOM_HQ_LONGITUDE = -6.2576841;
	private static final double INTERCOM_HQ_LATITUDE =53.3393;
	
	private ProximitySearchService proximitySearchService;
	
	private Customer customer1;
	private Customer customer2;
	private Customer customer3;
	private Customer customer4;
	private Customer customer5;
	private Customer customer6;
	private Customer customer7;
	
	private List<Customer> customers = new ArrayList<Customer>();
	
	@Before
	public void setUp(){
	  
		proximitySearchService = new ProximitySearchServiceImpl();
		
		customer1 = new Customer(53.038056, 26, "Stephen McArdle", -7.653889);
		customer2 = new Customer(54.1225, 27, "Enid Gallagher", -8.143333);
		customer3 = new Customer(53.1229599, 6, "Theresa Enright", -6.2705202);
		customer4 = new Customer(52.2559432, 9, "Jack Dempsey", -7.1048927);
		customer5 = new Customer(52.240382, 10, "Georgina Gallagher", -6.972413);
		customer6 = new Customer(53.2451022, 4, "Ian Kehoe", -6.238335);
		customer7 = new Customer(53.1302756, 5, "Nora Dempsey", -6.23972);
		
		customers.add(customer1);
		customers.add(customer2);
		customers.add(customer3);
		customers.add(customer4);
		customers.add(customer5);
		customers.add(customer6);
		customers.add(customer7);
		
	}
	
	@Test
	public void testNullForCustomersAndRangeArgs() {
		
		Assert.assertNull("", proximitySearchService.findCustomersWithinARange(0.0, 0.0, null, null));		
	}
	
	@Test
	public void testEmptyArrayForDefaultDoubleVals() {
		
		Assert.assertEquals(0, proximitySearchService.findCustomersWithinARange(0.0, 0.0, customers, 100).size());		
	}
	
	
	/**
	 * Should return 1 of the 7 customers
	 * 
	 * 	USER ID: 4 NAME: Ian Kehoe
	 * 
	 */
	@Test
	public void findCustomersWithinARangeOf15KmsTest() {
		
		List<Customer> actual = proximitySearchService.findCustomersWithinARange(INTERCOM_HQ_LONGITUDE, INTERCOM_HQ_LATITUDE, customers, 15);
        List<Customer> expected = Arrays.asList(customer6);

        assertThat(actual, is(expected));
        
        //assertEquals(actual, expected);
		
	}
	
	/**
	 * Should return 4 of the 7 customers
	 * 
	 * 	USER ID: 26 NAME: Stephen McArdle
	 * 	USER ID: 6 NAME: Theresa Enright
	 * 	USER ID: 4 NAME: Ian Kehoe
	 * 	USER ID: 5 NAME: Nora Dempsey
	 * 	
	 */
	@Test
	public void findCustomersWithinARangeOf100KmsTest() {
		
		List<Customer> actual = proximitySearchService.findCustomersWithinARange(INTERCOM_HQ_LONGITUDE, INTERCOM_HQ_LATITUDE, customers, 100);
        List<Customer> expected = Arrays.asList(customer1, customer3, customer6, customer7);

        assertThat(actual, is(expected));
        
        //assertEquals(actual, expected);
		
	}
	

}
