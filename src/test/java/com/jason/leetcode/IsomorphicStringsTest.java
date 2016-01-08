package com.jason.leetcode;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * IsomorphicStrings Tester.
 *
 * @author <Authors name>
 */
public class IsomorphicStringsTest {

	@Before
	public void before() throws Exception {
		i.log = false;
	}

	@After
	public void after() throws Exception {
	}

	public static IsomorphicStrings i = new IsomorphicStrings();

	@Test
	public void testIsIsomorphic() throws Exception {
		System.out.println(i.isIsomorphic("abab","baba"));
	}


} 
