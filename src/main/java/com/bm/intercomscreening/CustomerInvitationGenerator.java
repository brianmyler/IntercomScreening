package com.bm.intercomscreening;


import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.bm.intercomscreening.domain.Customer;
import com.bm.intercomscreening.services.FileService;
import com.bm.intercomscreening.services.ProximitySearchService;

/**
 * @author Brian Myler
 *
 * Main class used to execute Customer Invitation Generation code
 */

@Configuration
@ComponentScan
public class CustomerInvitationGenerator {

	@Autowired
	ProximitySearchService proximitySearchService;
	
	@Autowired
	FileService fileService;
	
	private static final double INTERCOM_HQ_LONGITUDE = -6.2576841;
	private static final double INTERCOM_HQ_LATITUDE =53.3393;
	
	public List<Customer> generateCustomerInvitations(Integer range){
		
		List<Customer> customers = null;
		try {
			customers = fileService.readFileFromResourcesAndParse("customers.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<Customer> invitedCustomers = proximitySearchService.findCustomersWithinARange(INTERCOM_HQ_LONGITUDE,INTERCOM_HQ_LATITUDE, customers, range);
		
		// Sort by id
		Collections.sort(invitedCustomers, (a, b) -> a.getUser_id() < b.getUser_id() ? -1 : a.getUser_id() == b.getUser_id() ? 0 : 1);
		
		return invitedCustomers;
	}
	
	public static void main(String[] args) {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(CustomerInvitationGenerator.class);
		
		CustomerInvitationGenerator cif = context.getBean("customerInvitationGenerator", CustomerInvitationGenerator.class);
		
		for(Customer c : cif.generateCustomerInvitations(100)){
			System.out.println("USER ID: "+c.getUser_id()+" NAME: "+c.getName());
		}
	}

}
