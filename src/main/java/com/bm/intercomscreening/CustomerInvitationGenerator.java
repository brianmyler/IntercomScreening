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

	private final ProximitySearchService proximitySearchService;
	
	private final FileService fileService;
	
	private static final Double INTERCOM_HQ_LONGITUDE = -6.2576841;
	private static final Double INTERCOM_HQ_LATITUDE =53.3393;
	private static final String INTERCOM_CUSTOMERS_FILE_NAME ="customers.txt";
	
	@Autowired
	public CustomerInvitationGenerator(FileService fileService, ProximitySearchService proximitySearchService) {
        this.proximitySearchService = proximitySearchService;
        this.fileService = fileService;
    }
	
	public List<Customer> generateCustomerInvitations(Integer kilometerRange){
		
		if(kilometerRange==null){
			return null;
		}
		
		List<Customer> customers = null;
		try {
			customers = fileService.readFileFromResourcesAndParse(INTERCOM_CUSTOMERS_FILE_NAME);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<Customer> invitedCustomers = proximitySearchService.findCustomersWithinARange(INTERCOM_HQ_LONGITUDE,INTERCOM_HQ_LATITUDE, customers, kilometerRange);
		
		// Sort by id
		Collections.sort(invitedCustomers, (a, b) -> a.getUser_id() < b.getUser_id() ? -1 : a.getUser_id() == b.getUser_id() ? 0 : 1);
		
		return invitedCustomers;
	}
	
//	public static void main(String[] args) {
//		
//		ApplicationContext context = new AnnotationConfigApplicationContext(CustomerInvitationGenerator.class);
//		
//		CustomerInvitationGenerator cif = context.getBean("customerInvitationGenerator", CustomerInvitationGenerator.class);
//		
//		for(Customer c : cif.generateCustomerInvitations(KILOMETRE_RANGE)){
//			System.out.println("USER ID: "+c.getUser_id()+" NAME: "+c.getName());
//		}
//	}

}
