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

def printC(c,name='dft'):
    if isinstance(c,dict):
        printDict(c,name)
    elif isinstance(c,list) or isinstance(c,tuple):
        printList(l,name)

def printList(l,name):
    if isinstance(l,list):
        print 'List %s :[' % name ,
    else:
        print 'Tuple %s :(' % name ,
    cnt=0
    for i in range(len(l)):
        print '[%s]:%s' % (cnt,l[i]) ,
        cnt+=1
    if isinstance(l,list):
        print ']'
    else:
        print ')'

def printDict(d,name):
    print 'Dict',name,':{',
    cnt=0
    for i in range(len(l)):
        print '[%s]:%s' % (cnt,l[i]) ,
        cnt+=1
    print '}'


# l=[1,2,3]
# l=(1,2,3)
# printC(l)









