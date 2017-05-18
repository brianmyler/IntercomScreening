package com.bm.intercomscreening;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.bm.intercomscreening.ArrayFlattener;

/**
 * @author brian myler
 *
 */
public class ArrayFlattenerTest {

	ArrayFlattener arrayFlattener;
	
	
	/**
	 * Set up code that instantiates ArrayFlattener
	 */
	@Before
	public void setUp() {
		arrayFlattener = new ArrayFlattener();
	}
	
	
	/**
	 * Test to verify that null will be returned if null is passed in for both arguments
	 */
	@Test
	public void testNullForBothArgs(){
        Assert.assertNull(
                "Verify that null will be returned if null is passed in for both arguments",
                arrayFlattener.flattenArray(null, null)
        );
    }
	
	
	/**
	 * Test to verify that null will be returned if null is passed into first argument
	 */
	@Test
	public void testNullForFirstArgs(){
        Assert.assertNull(
                "Verify that null will be returned if null is passed in for first argument",
                arrayFlattener.flattenArray(null, new ArrayList<Integer>())
        );
    }
	
	
	/**
	 * Test to verify that null will be returned if null is passed in for second argument
	 */
	@Test
	public void testNullForLastArgs(){
        Assert.assertNull(
                "Verify that null will be returned if null is passed in for second argument",
                arrayFlattener.flattenArray(new Object[]{1, 2 ,3, new Object[]{11, 22, 33, new Object[]{111, 222, 333}}}, null)
        );
    }
	
	
	/**
	 * Test IllegalArgumentException if a non Integer/Object[] type is passed in
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testIllegalArgumentExceptionThrownIfNotSupportedType() {
		Assert.assertNull(
                "Verify that null will be returned if null is passed in for second argument",
                arrayFlattener.flattenArray(new Object[]{1, 2 ,"dsfsd", new Object[]{22, 44, 66, new Object[]{222, 444, 666}}}, new ArrayList<Integer>())
        );
	}
	
	
	/**
	 * Test basic array with no nesting
	 * [
	 * 		1, 2 ,3, 4, 5, 6
	 * ]
	 */
	@Test
	public void testNonNestedArray() {
		
		List<Integer> actual = arrayFlattener.flattenArray(new Object[]{1, 2 ,3, 4, 5, 6}, new ArrayList<Integer>());
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6);

        assertThat(actual, is(expected));
	}
	
	/**
	 * Test array with 1 level of nesting
	 * [
	 * 		1, 2, 3, 
	 * 		[
	 * 			11, 22, 33, 44, 55, 66, 
	 * 		]
	 * 		4, 5, 6
	 * ]
	 */
	@Test
	public void testOneLevelNestedArray() {
		  
		List<Integer> actual = arrayFlattener.flattenArray(new Object[]{1, 2 ,3, new Object[]{11, 22, 33, 44, 55, 66}, 4, 5, 6}, new ArrayList<Integer>());
        List<Integer> expected = Arrays.asList(1, 2, 3, 11, 22, 33, 44, 55, 66, 4, 5, 6);

        assertThat(actual, is(expected));
	}
	
	/**
	 * Test array with 3 levels of nesting
	 * [
	 * 		1, 2, 3, 
	 * 		[
	 * 			11, 22, 
	 * 			[
	 * 				111, 222, 333, 444, 555, 
	 * 				[
	 * 					1111, 2222, 3333, 4444, 5555, 6666, 
	 * 				]
	 * 				666, 
	 * 			]
	 * 			33, 44, 55, 66
	 * 		], 
	 * 		4, 5, 6
	 * ]
	 */
	@Test
	public void testThreeLevelNestedArray() {
		
		List<Integer> actual = arrayFlattener.flattenArray(new Object[]{1, 2 ,3, new Object[]{11, 22, new Object[]{111, 222, 333, 444, 555, new Object[]{1111, 2222, 3333, 4444, 5555, 6666}, 666}, 33, 44, 55, 66}, 4, 5, 6}, new ArrayList<Integer>());
        List<Integer> expected = Arrays.asList(1, 2, 3, 11, 22, 111, 222, 333, 444, 555, 1111, 2222, 3333, 4444, 5555, 6666, 666, 33, 44, 55, 66, 4, 5, 6);

        assertThat(actual, is(expected));
	}

}
