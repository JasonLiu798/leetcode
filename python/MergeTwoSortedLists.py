#!/bin/env python
#-*- coding:utf-8 -*-

'''
21. Merge Two Sorted Lists
https://leetcode.com/problems/merge-two-sorted-lists/

'''

import myutil.List as List

# Definition for singly-linked list.
'''
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None
'''

class Solution(object):
    # log=True
    log=False
    def mergeTwoLists(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        if l1 == None:
            return l2
        if l2 == None:
            return l1
        #increase or decrease
        d=self.getDirection(l1)
        if d==0:
            d=self.getDirection(l2)
        if d==0:
            #all equal,or only have one element
            if l1.val < l2.val:
                self.append2end(l1,l2)
                return l1
            else:
                self.append2end(l2,l1)
                return l2
        if self.log:
            print 'direction',d
        res = ListNode(0)  #self.getOne(l1,l2,d)
        # res = List.ListNode(0)  #self.getOne(l1,l2,d)
        cur = res
        while l1!=None and l2!=None:
            if self.log:
                l1.display('l1')
                l2.display('l2')
            if d>0: #increase
                if l1.val<=l2.val:
                    cur.next = l1
                    l1=l1.next
                else:
                    cur.next = l2
                    l2=l2.next
            cur = cur.next
        if l1!=None:
            cur.next = l1
        elif l2!=None:
            cur.next = l2
        '''
            tmp = self.getOne(l1,l2,d)
            cur.next = tmp
            cur=cur.next

        if l2!=None:
            cur.name = cur2
        '''
        return res.next
    def append2end(self,frontl,endl):
        cur1 = frontl
        while cur1.next!= None:
            cur1=cur1.next
        cur1.next=endl
    def getDirection(self,l):
        '''
        l not null
        return
            0 unkonwn
            1 increase
            -1 decrease
        '''
        if l.next == None:
            return 0
        res = 0
        # filter same val
        while l.next!=None and l.next.val==l.val:
            l = l.next
        # l.display()
        if l.next != None:
            if l.next.val > l.val:
                res= 1
            else:
                res= -1
        return res


if __name__ == '__main__':
    l1 = List.ListNode(2)
    # l1.next = List.ListNode(5)
    # l1.next.next = List.ListNode(9)
    l1.display('l1')

    l2 = List.ListNode(1)
    # l2.next = List.ListNode(5)
    # l2.next.next = List.ListNode(7)
    l2.display('l2')

    s = Solution()
    res = s.mergeTwoLists(l1,l2)
    res.display('res')
    # print s.getDirection(l1)
    # res = s.mergeTwoLists(l1,l2)




