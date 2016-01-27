#!/bin/env python
#-*- coding:utf-8 -*-

'''
24. Swap Nodes in Pairs
Difficulty: Medium
Given a linked list, swap every two adjacent nodes and return its head.

For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.

Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
'''

# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

from myutil.List import *

class Solution(object):
    def swapPairs(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        if head==None or head.next==None:
            return head
        o,e=self.separate(head)
        # o.display()
        # e.display()
        res=self.merge(e,o)
        return res.next

    def separate(self,l):
        resOddHead=ListNode(0)
        curOdd=resOddHead
        resEvenHead=ListNode(0)
        curEven=resEvenHead
        cur=l
        idx=1
        while cur!=None:
            if idx%2 == 1:
                curOdd.next=cur
                curOdd=curOdd.next
            else:
                curEven.next=cur
                curEven=curEven.next
            cur=cur.next
            idx+=1
        curOdd.next=None
        curEven.next=None
        return resOddHead,resEvenHead

    def merge(self,l1,l2):
        resHead=ListNode(0)
        cur=resHead
        l1=l1.next
        l2=l2.next
        while l1!=None and l2!=None:
            cur.next=l1
            cur=cur.next
            l1=l1.next
            cur.next=l2
            l2=l2.next
            cur=cur.next
        while l1!=None:
            cur.next=l1
            cur=cur.next
            l1=l1.next
        while l2!=None:
            cur.next=l2
            cur=cur.next
            l2=l2.next
        return resHead

if __name__ == '__main__':
    s=Solution()
    l=[1,2,3,4,5,6,7]
    l=[1,2]
    ll=initList(l)
    res=s.swapPairs(ll)
    res.display()
    '''
    o,e=s.separate(ll)
    o.display()
    e.display()
    res=s.merge(e,o)
    res.display()
    '''
