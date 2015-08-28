package com.jason.leetcode; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After; 

/** 
* UglyNumber Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 21, 2015</pre> 
* @version 1.0 
*/ 
public class UglyNumberTest { 
private static UglyNumber u = new UglyNumber();
@Before
public void before() throws Exception {
	u.log = true;
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: isUgly(int num) 
* 
*/ 
@Test
public void testIsUgly() throws Exception {
	u.log = true;
//	for(int i=2;i<15;i++)
//		System.out.println(i+","+u.isUgly(i));
	System.out.println(u.isUgly(-2147483648));
//	int indexA = -100;
//	int indexB = -indexA;
//	System.out.println(indexB);
	//-2147483648
	//2	3 4 5 6 7 8 9 10 11 12 13 14 15
	//t t t t t f t t t  f  t  f  f  t
//	System.out.println(u.isUgly(11));
//	System.out.println(5%5);
}


} 
