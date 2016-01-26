package com.jason.tools;

public class ListNode {

	public int val;
	public ListNode next;

	public ListNode(int x) {
		val = x;
		next = null;
	}

	@Override
	public String toString() {
		return "ListNode{" +
				"val=" + val +
				", next=" + next +
				'}';
	}


	public static ListNode initTestList(int[] a){
		ListNode st = new ListNode(a[0]);
		ListNode pre = st;
		for(int i=1;i<a.length;i++){
			ListNode newNode = new ListNode(a[i]);
			pre.next = newNode;
			pre = newNode;
		}
		return st;
	}

	public static ListNode getLCLinkedListN(ListNode l ,int n){
//		ListNode res ;
		for(int i=0;i<n;i++){
			l=l.next;
		}
		return l;
	}

	public static void printLCLinkedList(ListNode n){
		System.out.println("Linked List:");
		while(n!=null){
			System.out.print(n.val + " ");
			n=n.next;
		}
		System.out.println();
	}

}
