#!/bin/env python
#-*- coding:utf-8 -*-

class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None
    def display(self,name='default'):
        print 'List',name,':',
        node = self
        while node != None:
            print node.val,
            node = node.next
        print

def printList(l):
    print 'List:'
    for i in range(len(l)):
        print l[i],
    print
