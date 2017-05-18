package com.bm.intercomscreening.services;

import java.io.IOException;
import java.util.List;

import com.bm.intercomscreening.domain.Customer;

public interface FileService {

	List<Customer> readFileFromResourcesAndParse(String fileName)  throws IOException;
	
}
