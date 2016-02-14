package com.jason.leetcode;

import com.jason.tools.ListNode;

/**
 * ReverseLinkedList
 * https://leetcode.com/problems/reverse-linked-list/
 * Runtime: 348 ms
 */
public class ReverseLinkedList {


	public static ListNode newhead;
	public ListNode reverseList(ListNode head) {
		if(head==null||head.next==null){
			return head;
		}
		ListNode pre = head;
		ListNode now = head.next;
		head.next = null;
		recu(pre, now);
		return newhead;
	}

	public static void recu(ListNode pre, ListNode now ) {
//		System.out.println("pre "+pre+"|||| now "+now);
		if (now != null) {
			ListNode next = now.next;
			now.next = pre;
			recu(now, next);
		} else {
			newhead = pre;
			return;
		}
	}


}
