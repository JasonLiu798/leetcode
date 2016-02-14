package com.jason.leetcode; 

import com.jason.tools.ListNode;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* AddTwoNumbers Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 28, 2015</pre> 
* @version 1.0 
*/ 
public class AddTwoNumbersTest { 

@Before
public void before() throws Exception { 
    a.log = true;
}

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: addTwoNumbers(ListNode l1, ListNode l2) 
* 
*/
public static AddTwoNumbers a = new AddTwoNumbers();
@Test
public void testCase0() throws Exception {
    int[] a1 = {2,4,3};
    int[] a2 = {5,6,4};
    ListNode n1 = ListNode.initTestList(a1);
    ListNode n2 = ListNode.initTestList(a2);
    ListNode res = a.addTwoNumbers(n1, n2);
    ListNode.printLCLinkedList(res);
}

    @Test
    public void testCase1() throws Exception {
        int[] a1 = {9};
        int[] a2 = {9};
        ListNode n1 = ListNode.initTestList(a1);
        ListNode n2 = ListNode.initTestList(a2);
        ListNode res = a.addTwoNumbers(n1, n2);
        ListNode.printLCLinkedList(res);
    }

    @Test
    public void testCase3() throws Exception {
        int[] a1 = {1,2,3,4,5};
        int[] a2 = {3,4,5};
        //res       4 6 8 4 5
        ListNode n1 = ListNode.initTestList(a1);
        ListNode n2 = ListNode.initTestList(a2);
        ListNode res = a.addTwoNumbers(n1, n2);
        ListNode.printLCLinkedList(res);
    }

    @Test
    public void testCase4() throws Exception {
        int[] a1 = {1,2,3,4,5};
        int[] a2 = {3,4,5};
        //res       4 6 8 4 5
        ListNode n1 = ListNode.initTestList(a2);
        ListNode n2 = ListNode.initTestList(a1);
        ListNode res = a.addTwoNumbers(n1, n2);
        ListNode.printLCLinkedList(res);
    }


}

