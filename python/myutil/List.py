#!/bin/env python
#-*- coding:utf-8 -*-

class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None
    def display(self,name='default'):
        print 'LinkList\'%s\':'%name,
        node = self
        while node != None:
            print node.val,
            node = node.next
        print


def initList(lt):
    head=ListNode(0)
    cur=head
    for i in lt:
        tmp=ListNode(i)
        cur.next=tmp
        cur=cur.next
    return head.next

# l=initList([1,2,3,4,5,6])
# l.display()









