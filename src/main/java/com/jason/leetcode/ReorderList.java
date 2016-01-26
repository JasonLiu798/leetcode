package com.jason.leetcode;

import com.jason.tools.ListNode;
import sun.reflect.generics.tree.Tree;

import java.util.HashMap;

/**
 * M143
 * 21.0%
 * Reorder List
 * https://leetcode.com/problems/reorder-list/
 * Runtime: 468 ms
 */
public class ReorderList {

	public static boolean log = false;
	/**
	 * main:
	 * hashMap record all nodes	T(n)
	 * the traverse the map recreate the LinkedList	T(n)
	 * T(2n)=>O(n)
	 * space 2n
	 * @param head
	 */
	public void reorderList(ListNode head) {
		if(head==null||head.next==null||head.next.next==null){
			return;
		}
		HashMap<Integer,ListNode> hm = new HashMap<>();
		ListNode cur = head;
		int i=0;
		while(cur!=null){
			hm.put(i,cur);
			cur=cur.next;
			i++;
		}
		int cnt = i;

		ListNode n1=null;
		ListNode n2 = null;
		
		/**
		 * 			   0 1 2 3 4 5
		 * int [] indexA = {1,2,3,4,5,6};
		 */
		int len = -1;
		if(cnt%2==0){
			len = cnt/2;
		}else{
			len = cnt/2+1;
		}

		if(log){
			System.out.println("len "+len+",cnt "+cnt);
		}
		for(i=0;i<len;i++){
			if(i!=cnt-1-i) {
				n1 = hm.get(i);
				n2 = hm.get(cnt - 1 - i);
				if (i != 0){
					cur.next = n1;
				}
				n1.next = n2;
				cur = n2;
				if(i==len-1){
					n2.next = null;
				}
			}else{
				n1 = hm.get(i);
				n1.next = null;
				cur.next = n1;
			}
		}
	}


	/**
	 * main:qucik slow pointer,find the middle node(start node of the latter part) T(n/2)
	 * reverse the latter part T(m) m=n/2
	 * merge front and part T(n)
	 * T(n/2+n/2+n)=T(3/2n)=O(n)
	 * space O(1)
	 * @param head
	 */
	public void reorderList1(ListNode head) {
		return;
	}

	/**
	 * recusive
	 * https://leetcode.com/discuss/54419/solution-using-recursion
	 */
}
