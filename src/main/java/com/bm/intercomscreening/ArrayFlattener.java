package com.bm.intercomscreening;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Brian Myler
 *
 * Main class used to execute Array Flattening code
 */

public class ArrayFlattener {

	public List<Integer> flattenArray(Object[] multiTypeArray, List<Integer> flattennedList){
		if(multiTypeArray==null || flattennedList==null){
			return null;
		}
		
		for(Object currentIndex : multiTypeArray){
			if(currentIndex instanceof Integer){
				flattennedList.add((Integer)currentIndex);
			}else if (currentIndex instanceof Object[]) {
				// In the event of heavy nesting, recursion may exhaust the call stack, if thats important, an iterative solution may be required
				flattenArray((Object[]) currentIndex, flattennedList);
            } else {
                throw new IllegalArgumentException("The flattenArray method currently only supports the following types: Object[], Integer");
            }
		}
		
		return flattennedList;
	}
	
	
}
