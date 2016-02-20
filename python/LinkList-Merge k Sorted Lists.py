#!/bin/env python
#-*- coding:utf-8 -*-
'''
@questions 23. Merge k Sorted Lists
@Difficulty Hard
@Pass 22.7%
@link https://leetcode.com/problems/merge-k-sorted-lists/
@introduction
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
@res
'''

# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

from myutil.List import *
import Queue

'''
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None
'''

def nodeCmp(n1,n2):
    return cmp(n1.val, n2.val)

class Solution(object):
    '''
    :type lists: List[ListNode]
    :rtype: ListNode
    add all node to a list,sort list,traverse list
    '''
    def mergeKLists(self, lists):
        ml=[]
        for l in lists:
            while l!=None:
                ml.append(l)
                l=l.next
        ml=sorted(ml,nodeCmp)
        res=ListNode(0)
        cur=res
        for item in ml:
            cur.next=item
            cur=cur.next
            # print item.val,
        cur.next=None
        return res.next

    #use merge2list,https://leetcode.com/discuss/82564/132-ms-python-using-divide-and-conquer-and-merge-sorted-lists
    def mergeKLists2(self, lists):
        """
        :type lists: List[ListNode]
        :rtype: ListNode
        """
        #merge every two list
        m = len(lists)
        if m == 0:
            return []
        while m > 1:
            ans_lis = []
            for i in range(0,m,2):
                if i+1 < m:
                    ans_lis.append(self.mergeTwoLists(lists[i],lists[i+1]))
                else:
                    ans_lis.append(lists[i])
            lists = ans_lis
            m = len(lists)
        if m > 0:
            return lists[0]
    def mergeTwoLists(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        #determine the head
        if l1 and l2:
            if l1.val <= l2.val:
                head = l1
                l1 = l1.next
            else:
                head = l2
                l2 = l2.next
        elif l1:
            return l1
        elif l2:
            return l2
        else:
            return None
        point = head
        while l1 and l2:
            if l1.val <= l2.val:
                point.next = l1
                l1 = l1.next
            else:
                point.next = l2
                l2 = l2.next
            point = point.next
        #now, iterate through rest
        if l1:
            point.next = l1
        if l2:
            point.next = l2
        return head
    '''
    user min heap or Queue.PriorityQueue() q.put(ListNode(3)) q.qsize()>0: print q.get()
    https://leetcode.com/discuss/82624/o-k-space-9-line-python-solution-min-heap-108ms
    '''
    def mergeKLists3(self, lists):
        minHeap, head = [(node.val, node) for node in lists if node], ListNode(0)
        heapq.heapify(minHeap)
        p = head
        while minHeap:
            _, node = heapq.heappop(minHeap)
            if node.next: heapq.heappush(minHeap, (node.next.val, node.next))
            p.next, node.next = node, None
            p = p.next
        return head.next


if __name__ == '__main__':
    s=Solution()
    l1=[1,2,3,4,5]
    l2=[3,4,5,6,7]
    ll1=initList(l1)
    ll2=initList(l2)
    tl=[]
    tl.append(ll1)
    tl.append(ll2)
    res=s.mergeKLists(tl)
    res.display()
    # print 'res',res






