package com.bm.intercomscreening;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.bm.intercomscreening.domain.Customer;
import com.bm.intercomscreening.services.FileService;
import com.bm.intercomscreening.services.ProximitySearchService;

@RunWith(MockitoJUnitRunner.class)
public class CustomerInvitationGeneratorTest {
	
	private static final Integer KILOMETRE_RANGE =100;
	private static final Double INTERCOM_HQ_LONGITUDE = -6.2576841;
	private static final Double INTERCOM_HQ_LATITUDE =53.3393;
	private static final String INTERCOM_CUSTOMERS_FILE_NAME ="customers.txt";
	
	@Mock
	private FileService mockedFileService;
	
	@Mock
	private ProximitySearchService mockedProximitySearchService;
	
	private CustomerInvitationGenerator customerInvitationGenerator;
	
	private Customer customer1;
	private Customer customer2;
	private Customer customer3;
	private Customer customer4;
	private Customer customer5;
	private Customer customer6;
	private Customer customer7;
	
	private List<Customer> customers;
	
  	@Before
  	public void setUp(){
	  
  		customerInvitationGenerator = new CustomerInvitationGenerator(mockedFileService, mockedProximitySearchService);
  	    
  		customer1 = new Customer(53.038056, 26, "Stephen McArdle", -7.653889);
  		customer2 = new Customer(54.1225, 27, "Enid Gallagher", -8.143333);
  		customer3 = new Customer(53.1229599, 6, "Theresa Enright", -6.2705202);
  		customer4 = new Customer(52.2559432, 9, "Jack Dempsey", -7.1048927);
  		customer5 = new Customer(52.240382, 10, "Georgina Gallagher", -6.972413);
  		customer6 = new Customer(53.2451022, 4, "Ian Kehoe", -6.238335);
  		customer7 = new Customer(53.1302756, 5, "Nora Dempsey", -6.23972);
  		
  		customers = new ArrayList<Customer>();
	  
  		customers.add(customer1);
  		customers.add(customer2);
  		customers.add(customer3);
  		customers.add(customer4);
  		customers.add(customer5);
  		customers.add(customer6);
  		customers.add(customer7);
	  
  		try {
  			when(
  				mockedFileService.readFileFromResourcesAndParse(INTERCOM_CUSTOMERS_FILE_NAME)
  			)
  			.thenReturn(
  				Arrays.asList(customer1, customer2, customer3, customer4, customer5, customer6, customer7)
  			);
  		} catch (IOException e) {
  			e.printStackTrace();
  		}
  		
  		when(
  			mockedProximitySearchService.findCustomersWithinARange(INTERCOM_HQ_LONGITUDE, INTERCOM_HQ_LATITUDE, customers, KILOMETRE_RANGE)
  		)
  		.thenReturn(
  			Arrays.asList(customer1, customer3, customer6, customer7)
  		);
  		
  		
  	}

  	@Test
	public void testNullForRangeArgs() {
		
		Assert.assertNull("", customerInvitationGenerator.generateCustomerInvitations(null));		
	}
  	
  	/**
	 * Should return 4 of the 7 customers in the following order
	 * 
	 * 	USER ID: 4 NAME: Ian Kehoe
	 * 	USER ID: 5 NAME: Nora Dempsey
	 * 	USER ID: 6 NAME: Theresa Enright
	 * 	USER ID: 26 NAME: Stephen McArdle
	 * 
	 */
  	@Test
    public void testGenerateCustomerInvitations() throws Exception {

  		List<Customer> actual = customerInvitationGenerator.generateCustomerInvitations(KILOMETRE_RANGE);
        List<Customer> expected = Arrays.asList(customer6, customer7, customer3, customer1);

        assertThat(actual, is(expected));
    }
	  
}
