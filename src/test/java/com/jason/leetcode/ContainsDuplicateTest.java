package com.jason.leetcode;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.assertEquals;

/**
 * ContainsDuplicate Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 18, 2015</pre>
 */
public class ContainsDuplicateTest {
	public static ContainsDuplicate c = new ContainsDuplicate();

	@Before
	public void before() throws Exception {
	}

	@After
	public void after() throws Exception {
	}

	/**
	 * Method: containsDuplicate(int[] nums)
	 */
	@Test
	public void testContainsDuplicate() throws Exception {
		int[] a1 = {1,2,3,4,4};
		int[] a2 = {1,2,3};
		assertEquals(true,c.containsDuplicate(a1));
		assertEquals(false,c.containsDuplicate(a2));
	}


} 
