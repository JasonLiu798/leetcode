package com.jason.leetcode; 

import com.jason.tools.CollectionTool;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* MergeSortedArray Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 19, 2015</pre> 
* @version 1.0 
*/ 
public class MergeSortedArrayTest {
private static MergeSortedArray m = new MergeSortedArray();
@Before
public void before() throws Exception {
	m.log = true;
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: movetoend(int[] arr, int al) 
* 
*/ 
@Test
public void testMovetoend() throws Exception {

}

/** 
* 
* Method: copy2end(int[] a, int al, int[] b, int bl) 
* 
*/ 
@Test
public void testCopy2end() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getorder(int[] nums1, int m, int[] nums2, int n) 
* 
*/ 
@Test
public void testGetorder() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: merge(int[] nums1, int m, int[] nums2, int n) 
* 
*/ 
@Test
public void testMerge() throws Exception { 
	int[] a = {1,3,5,7,0,0,0,0};
	int[] b = {2,4,6,8};
	int mm = 4;
	int n=4;
	this.m.merge(a,mm,b,n);
	CollectionTool.printArray(a);
} 


} 
