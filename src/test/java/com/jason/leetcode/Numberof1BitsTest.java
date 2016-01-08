package com.jason.leetcode; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After; 

/** 
* Numberof1Bits Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 20, 2015</pre> 
* @version 1.0 
*/ 
public class Numberof1BitsTest { 
public static Numberof1Bits n = new Numberof1Bits();
@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: hammingWeight(int n) 
* 
*/ 
@Test
public void testHammingWeight() throws Exception {
	//  2147483648 (10000000000000000000000000000000)
//	System.out.println(n.hammingWeight(1));
	System.out.println(n.hammingWeight(2));
	System.out.println(n.hammingWeight(3));
	System.out.println(n.hammingWeight(-3));
//	System.out.println(Integer.MAX_VALUE);//2147483647
//	System.out.println(Integer.MIN_VALUE);//-2147483648
	System.out.println(n.hammingWeight(-2147483648));
}


} 
