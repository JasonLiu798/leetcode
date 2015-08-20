package com.jason.leetcode;

import com.jason.tools.ListNode;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * ReverseLinkedList Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 20, 2015</pre>
 */
public class ReverseLinkedListTest {
	public static ReverseLinkedList r = new ReverseLinkedList();

	@Before
	public void before() throws Exception {
	}

	@After
	public void after() throws Exception {
	}

	/**
	 * Method: reverseList(ListNode head)
	 */
	@Test
	public void testReverseList() throws Exception {
		int [] arr = {1,2,3,4,5};
		ListNode root = ListNode.initTestList(arr);
		ListNode.printLCLinkedList(root);
		ListNode res = r.reverseList(root);
		ListNode.printLCLinkedList(res);
	}

	/**
	 * Method: recu(ListNode l)
	 */
	@Test
	public void testRecu() throws Exception {
//TODO: Test goes here... 
	}


} 
