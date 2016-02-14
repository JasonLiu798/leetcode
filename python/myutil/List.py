#!/bin/env python
#-*- coding:utf-8 -*-

class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None
    def display(self,name='dft'):
        print 'LinkList\'%s\':'%name,
        sp = self
        fp = self
        while sp != None :
            print sp.val,
            sp = sp.next
            if fp!=None and fp.next!=None:
                fp = fp.next.next
            if sp==fp:
                if fp!=None:
                    print fp.val
                break
        print


def initList(lt):
    head=ListNode(0)
    cur=head
    for i in lt:
        tmp=ListNode(i)
        cur.next=tmp
        cur=cur.next
    return head.next

'''
l=initList([1,2,3,4,5,6])
l.next.next.next.next = l.next
l.display()
'''







