#!/bin/env python
#-*- coding:utf-8 -*-

'''
@questions 25. Reverse Nodes in k-Group
@Difficulty hard
@Pass   26.7%
@link https://leetcode.com/problemset/algorithms/
@introduction
Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5

@res Your runtime beats 63.93% of python submissions.
'''

from myutil.List import *

# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):


    def reverseKGroup(self, head, k):
        """
        :type head: ListNode
        :type k: int
        :rtype: ListNode
        """
        if head==None or head.next==None or k<=1:
            return head
        tmpHead=ListNode(0)
        cur=tmpHead
        cur.next=head
        cnt=0
        l=cur
        lasttail=tmpHead
        first=True
        while cur!=None:
            # print 'cnt',cnt,'cur.val',cur.val,'mod',cnt%k
            if first:
                first=False
                cur=cur.next
            else:
                if cnt%k==1:
                    l=cur
                    cur=cur.next
                elif cnt %k ==0 :#or cur.next==None:
                    r=cur
                    leftnodes=cur.next
                    cur.next=None #取得一个跟其他不关联的list
                    newhead,newtail=self.reverseList(l)
                    lasttail.next=newhead
                    lasttail=newtail
                    cur=leftnodes
                    newtail.next=cur #接上后续节点
                    # newhead.display('new')
                    # if cur!=None:
                        # cur.display('cur')
                else:
                    cur=cur.next
            cnt+=1
        return tmpHead.next

    #return neahead,newtail
    def reverseList(self,head) :
        if head==None or head.next==None:
            return head
        pre = head
        now = head.next
        head.next = None
        newhead=[ListNode(0)]
        self.recu(newhead,pre, now)
        return newhead[0],head
    #recursive reverse linked list
    def recu(self,newhead,pre,now):
        if now != None:
            nextNode = now.next
            now.next = pre
            self.recu(newhead,now, nextNode)
        else :
            newhead[0] = pre
            return

if __name__ == '__main__':
    s=Solution()
    # l=[1,2,3,4,5]
    l=[1,2]
    ll=initList(l)
    # print id(ll),type(ll)
    # print 'before',id(l)
    # parm=[l]
    # print 'before',id(parm)
    # s.change(parm)
    # print 'out',id(parm)
    # parm[0].display('init')
    # '''
    # newHead,oldHead=s.reverseList(ll)
    # newHead.display('newhead')
    # oldHead.display('oldhead')
    res=s.reverseKGroup(ll,4)
    if res!=None:
        res.display('res')
    # print 'OH',oldHead.next.val
    # '''


