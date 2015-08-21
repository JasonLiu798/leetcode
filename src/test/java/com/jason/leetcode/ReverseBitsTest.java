package com.jason.leetcode; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After; 

/** 
* ReverseBits Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 21, 2015</pre> 
* @version 1.0 
*/ 
public class ReverseBitsTest { 

@Before
public void before() throws Exception {
	r.log = false;
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: reverseBits(int n) 
* 
*/

public static ReverseBits r = new ReverseBits();
@Test
public void testReverseBits() throws Exception {
//	System.out.println(Integer.toBinaryString(0x1));
//	System.out.println(Integer.toBinaryString(r.reverseBits(0x1)));
	//1000	0000	0000	0000	0000	0000	0000	0000
	System.out.println(Integer.toBinaryString(0x2));
	System.out.println(Integer.toBinaryString(r.reverseBits(0x2)));
//	100	0000	0000	0000	0000	0000	0000	0000
}


} 
