#!/bin/env python
#-*- coding:utf-8 -*-

'''
19.Remove Nth Node From End of List
https://leetcode.com/problems/remove-nth-node-from-end-of-list/
Given a linked list, remove the nth node from the end of list and return its head.

spidx=x
fpidx=2x
l=2x
tgt=2x-n
1)x<tgt,sp need stop early
x<2x-n
    x>n
2)x>tgt,
x>2x-n
    x<n
'''

import myutil.List as List

class Solution(object):
    def removeNthFromEnd(self, head, n):
        """
        :type head: ListNode
        :type n: int
        :rtype: ListNode
        """
        if head==None or (head.next==None and n==1):
            return None
        fcnt=0
        scnt=0
        length=0
        # tmpHead=List.ListNode(0)
        tmpHead=ListNode(0)
        tmpHead.next=head
        sp=tmpHead #slow pointer
        fp=tmpHead #fase pointer
        while fp!=None:
            # fp.display('fp')
            # sp.display('sp')
            if fp.next!=None:
                if fp.next.next!=None:
                    #continue
                    fp=fp.next.next
                    fcnt+=2
                    sp=sp.next
                    scnt+=1
                else:
                    scnt+=1
                    length=fcnt+1
                    break
            else:
                length=fcnt
                break
        # sp.display('after sp')
        # fp.display('after fp')
        # print 'length',length,'scnt',scnt
        if n>length or n<=0:
            return None
        else:
            if n>length/2:
                #front half part
                # print 'front','len',length,'len-n',length-n
                sp=tmpHead
                # if n!=length:
                for i in range(0,length-n):
                    sp=sp.next
            else:
                #last half part
                # print 'last scnt',scnt,'len-n',length-n
                if length % 2 ==1:
                    right=length-n+1
                else:
                    right=length-n
                for i in range(scnt,right):
                    sp=sp.next
        sp.next=sp.next.next
        return tmpHead.next

if __name__ == '__main__':
    s = Solution()
    #  7 6 5 4 3 2 1
    # l=[1,2,3,4,5,6,7]
    # l=[1,2]
    l=[1,2,3]
    # l=[1,2,3,4]
    # l=[7,5,9,9,1,2]
    ll=List.initList(l)
    ll.display('init')
    ll=s.removeNthFromEnd(ll,3)
    ll.display('result')









