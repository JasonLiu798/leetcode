#!/bin/env python
#-*- coding:utf-8 -*-
'''
@questions 61. Rotate List
@Difficulty Medium
@Pass
@link
@introduction
Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.

@res
'''

from myutil.List import *

# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def rotateRight(self, head, k):
        """
        :type head: ListNode
        :type k: int
        :rtype: ListNode
        """
        if head==None or head.next==None:
            return head
        if k==0:
            return head
        cur=head
        hm=[]
        i=0
        while cur!=None:
            hm.append(cur)
            cur=cur.next
            i+=1
        length=len(hm)
        if k>length:
            k=k%length
        # print 'k',k
        if k==0:
            return head
        elif k==length:
            return head
        else:#k<length
            newhead=hm[length-k]
            # newhead.next=head
            newtail=hm[length-k-1]
            newtail.next=None
            oldtail=hm[len(hm)-1]
            oldtail.next=head
            return newhead


if __name__ == '__main__':
    s=Solution()
    arr=[1,2,3,4,5]
    # arr=[1,2]
    l=initList(arr)

    res=s.rotateRight(l,10)
    res.display('res')



