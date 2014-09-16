package leetcode;

import tools.ListNode;

/**
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 * For example,
 * Given 1->1->2, return 1->2.
 * Given 1->1->2->3->3, return 1->2->3.
 * @author Jason Liu
 * 164 / 164 test cases passed.
 * Status: Accepted
 * Runtime: 520 ms
 */
public class DeleteDuplicate {

	public ListNode deleteDuplicates(ListNode head) {
		if (head == null) {
			return null;
		}
		if (head.next == null) {
			return head;
		}
		ListNode itr = head;
		ListNode itrNext = head;
		ListNode itrPrev = head;
		// {1,1}
		// {1,1,1,1}
		//1 1 2
		while (itr.next != null) {
			itrNext = itr.next;
			itrPrev = itr;
			boolean gotEnd = false;
			while (itr.val == itrNext.val) {//itr point to the next neq node
				if (itr.next != null) {
					itr = itr.next;
					itr.next = itrNext.next;
					if (itr.next != null) {
						itrNext = itr.next;
					}else{
						gotEnd = true;
						break;
					}
				}
			}
			if(gotEnd){
				itrPrev.next = null;
				break;
			}else{//itr point to last same number
				itr = itrNext;//itr.next;
				itrPrev.next = itr;
			}
		}
		return head;
	}
	public static void printLinkedList(ListNode head){
		ListNode itr = head;
		while(itr!=null){
			System.out.print(itr.val+",");
			itr = itr.next;
		}
		System.out.println("");
	}
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		
		ListNode a = new ListNode(1);
		head.next = a;
		ListNode b = new ListNode(1);
		a.next = b;
		ListNode c = new ListNode(1);
		b.next = c;
		new DeleteDuplicate().deleteDuplicates(head);
		printLinkedList(head);
	}

}
