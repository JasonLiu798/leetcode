package com.jason.leetcode; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;

import static org.junit.Assert.assertEquals;

/** 
* AddBinary Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 19, 2015</pre> 
* @version 1.0 
*/ 
public class AddBinaryTest { 
private static AddBinary a = new AddBinary();
@Before
public void before() throws Exception {
	a.log = true;
} 

@After
public void after() throws Exception { 
} 


	@Test
	public void testAddBinary() throws Exception {
//	String res = a.addBinary("110","11101");//100011
//	System.out.println(res);
		assertEquals("100011",a.addBinary("110","11101"));
		assertEquals("1010000010010011011001000001010111100",a.addBinary("101000001001001101100100000101011110", "101000001001001101100100000101011110"));
		assertEquals("101",a.addBinary("100","1"));
		assertEquals("100",a.addBinary("100","0000"));
// 101000001001001101100100000101011110
// 101000001001001101100100000101011110
//1010000010010011011001000001010111100
//	Last executed input:
//	"10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101", "1101010010111011

//	java.lang.NumberFormatException: For input string: "10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101"
//	Last executed input:
//	"10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101", "110101001011101
}


} 
