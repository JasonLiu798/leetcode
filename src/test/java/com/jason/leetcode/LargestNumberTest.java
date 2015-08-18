package com.jason.leetcode;

import com.jason.tools.CollectionTool;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * LargestNumber Tester.
 *
 * @author <Authors name>
 * @version 1.0
 */
public class LargestNumberTest {
	private static LargestNumber l = new LargestNumber();

	@Before
	public void before() throws Exception {
		l.log = true;
	}

	@After
	public void after() throws Exception {
	}

	/**
	 * Method: largestNumber(int[] nums)
	 */
	@Test
	public void testbucketSort() throws Exception {

	}

	/**
	 * Method: getHeadNum(int x)
	 */
	@Test
	public void testGetHeadNum() throws Exception {
//		System.out.println(l.getNum(10));
//		System.out.println(l.getNum(210));
//		System.out.println(l.getNum(3210));

		int[] a1 = {3, 30, 34, 5, 9};
		int[] a2 = {1};
		int[] a3 = {1,1};
		int[] a11 = {1,1,1};
		int[] a4 = {199,21};
		int[] a5 = {1,2,399,300,1024,1999,32};
		int[] a6 =  {13,138};//138 13
		int[] a14 = {13,132};//132 13 13132
		int[] a13 = {13,131};//12 121
		int[] a15 = {13,133};//123 12
		int[] a16 = {13,130};//12 120

		int[] a7 = {12,128,125};
		int[] a8 = {12,128,125,32,343,5};
		int[] a9 = {12,128,125,135,15,19,151,152};
		int[] a10 =	{12,128,125,35,15,9,351,152};
		int[] a12 =	{12,128,125,12,15,9,351,152};

//		int[] a11 =	{12,128,12,35,15,9,351,152};
//		System.out.println(l.largestNumber(a));

//		assertEquals("9534330",l.largestNumber(a1));
//		assertEquals("21199",l.largestNumber(a4));
//		assertEquals("12812",l.largestNumber(a6));
//		assertEquals("11",l.largestNumber(a3));
//		assertEquals("1",l.largestNumber(a2));
		assertEquals("12121",l.largestNumber(a13));
//		assertEquals("399323002119991024",l.largestNumber(a5));


//		List ll = l.getNums(a10);
//		System.out.println("init");
//		CollectionTool.printList(ll);
//		System.out.println("-----------");
//		//bucketSort(List<Num> l,int idx,List<Num> res){
//		List res = new LinkedList();
//		l.bucketSort(ll,-1,res);
//		System.out.println("res");
//		CollectionTool.printList(res);

//		System.out.println(l.largestNumber(a1));
	}


} 
