package com.jason.leetcode;

import com.jason.tools.ListNode;

/**
 * M2
 * 20.3%
 * Add Two Numbers
 * https://leetcode.com/problems/add-two-numbers/
 * AC Runtime: 480 ms
 */
public class AddTwoNumbers {


	public static boolean log = false;
	public static void print(ListNode n ){
		ListNode.printLCLinkedList(n);
	}
	/**
	 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
	 * Output: 7 -> 0 -> 8
	 *
	 * and correspond node to l1
	 * //reverse the l1
	 * process the carry
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		if(l1==null || l2==null){
			return null;
		}
		ListNode cur1 = l1;
		ListNode cur2 = l2;
		ListNode last1 = null;
		ListNode last2 = null;
		while(cur1!=null&&cur2!=null){
			cur1.val=cur1.val+cur2.val;
			if(cur1.next==null){
				last1 = cur1;
			}
			if(cur2.next == null){
				last2 = cur2;
			}
			cur1 = cur1.next;
			cur2 = cur2.next;
		}
		if(log){
			System.out.println("after add ");
			print(l1);
		}
		if(l1.next==null && l2.next ==null ){
			//same length,only one node
			if(l1.val>=10){
				l1.val = l1.val-10;
				l1.next = new ListNode(1);
			}
		}else if( cur1==null && cur2!=null) {
			//list2 longer than list1,merge to list1
			last1.next = cur2;
		}

			cur1 = l1;
			boolean carry = false;
			ListNode last = null;
			while(cur1!=null){
				if(log)
					System.out.println(cur1);
				if(carry){
					cur1.val = cur1.val+1;
					carry = false;
				}
				if(cur1.val>=10){
					cur1.val = cur1.val - 10;
					carry= true;
				}
				if(cur1.next==null){
					last =  cur1;
				}
				cur1 = cur1.next;
			}
			if(carry){
				last.next = new ListNode(1);
			}

		return l1;
		/*
		ListNode pre = cur1;
		ListNode now = cur1.next;
		ListNode next = null;
		while(now!=null){
			next = now.next;
			now.next = pre;
			pre = now;
			now = next;
		}*/
	}

}
