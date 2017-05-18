package com.bm.intercomscreening;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.bm.intercomscreening.domain.Customer;

public class IntercomMain {

	private static final Integer KILOMETRE_RANGE =100;
	
	public static void main(String[] args) {
		
		/**
		 * Given the nested arrays below, use the ArrayFlattener.flattenArray() method to flatten
		 [
		 * 		1, 2, 3, 4
		 * 		[
		 * 			11, 22, 33, 
		 * 			[
		 * 				111, 222, 333, 444, 555, 666
		 * 			]
		 * 			44, 55, 66, 
		 * 		]
		 * 		4, 5, 6
		 * ]
		 */
		System.out.println("******************Array Flattener *********************");
		System.out.println("");
		ArrayFlattener af = new ArrayFlattener();
		
		List<Integer> flattenedList = af.flattenArray(new Object[]{1, 2, 3, 4 , new Object[]{11, 22, 33, new Object[]{111, 222, 333, 444, 555, 666}, 44, 55, 66}, 5, 6}, new ArrayList<Integer>());
		
		System.out.println(Arrays.toString(flattenedList.toArray()));
		System.out.println("");
		System.out.println("*******************************************************");
		

		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("******** Customer Invitation Generator ****************");
		System.out.println("");
		ApplicationContext context = new AnnotationConfigApplicationContext(CustomerInvitationGenerator.class);
		
		CustomerInvitationGenerator cif = context.getBean("customerInvitationGenerator", CustomerInvitationGenerator.class);
		
		for(Customer c : cif.generateCustomerInvitations(KILOMETRE_RANGE)){
			System.out.println("USER ID: "+c.getUser_id()+" NAME: "+c.getName());
		}
		
		System.out.println("");
		System.out.println("*******************************************************");

	}

}
