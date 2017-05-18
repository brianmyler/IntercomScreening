package com.bm.intercomscreening.services.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bm.intercomscreening.domain.Customer;
import com.bm.intercomscreening.services.FileService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FileServiceImpl implements FileService{

	public List<Customer> readFileFromResourcesAndParse(String fileName) throws IOException{
		
		if(fileName==null || fileName.equals("")){
			return null;
		}
		
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        ObjectMapper mapper = new ObjectMapper();
        List<Customer> customers = new ArrayList<Customer>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
            	customers.add(mapper.readValue(line, Customer.class));
            }
        }
        
		return customers;
	}

}
