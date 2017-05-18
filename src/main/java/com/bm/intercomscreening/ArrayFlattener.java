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
	public static void main(String[] args) {
		
		ArrayFlattener af = new ArrayFlattener();
		
		List<Integer> flattenedList = af.flattenArray(new Object[]{1, 2, 3, 4 , new Object[]{11, 22, 33, new Object[]{111, 222, 333, 444, 555, 666}, 44, 55, 66}, 5, 6}, new ArrayList<Integer>());
		
		System.out.println(Arrays.toString(flattenedList.toArray()));
	}

}
