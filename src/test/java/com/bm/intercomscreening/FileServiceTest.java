package com.bm.intercomscreening;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.bm.intercomscreening.domain.Customer;
import com.bm.intercomscreening.services.FileService;
import com.bm.intercomscreening.services.impl.FileServiceImpl;

public class FileServiceTest {
	
	private static final String INTERCOM_CUSTOMERS_FILE_NAME ="customers-test.txt";
	
	private FileService fileService;
	
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
	  
		fileService = new FileServiceImpl();
		
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
	
	}
	
	@Test
	public void testNullForFilenameArgs() {
		
		try {
			Assert.assertNull("", fileService.readFileFromResourcesAndParse(null));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	@Test
	public void testReadAndParse() {
		
		List<Customer> actual = null;
		try {
			actual = fileService.readFileFromResourcesAndParse(INTERCOM_CUSTOMERS_FILE_NAME);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assert.assertEquals(actual.size(), customers.size());	
	}

}
