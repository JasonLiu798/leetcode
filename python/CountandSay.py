#!/bin/env python
#-*- coding:utf-8 -*-

'''
38. Count and Say
easy
https://leetcode.com/problems/count-and-say/
The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth sequence.
'''

class Solution(object):
    def countAndSay(self, n):
        """
        :type n: int
        :rtype: str
        """
        res=''
        cur=0

    def genOne(self,s):
        if s=='':
            return '1'
        idx=0
        while idx<len(s):
            nxt=idx+1
            if nxt<len(s):
                dupCnt=0
                while s[nxt]==s[idx]:
                    nxt+=1
                    dupCnt+=1
                if dupCnt>1:
                    res+=str(dupCnt)+'1'
                    idx+=dupCnt
                else:
                    res+='1'+str(s[idx])
                    idx+=1
            else:
                if s[idx:nxt]=='1':
                    res+='11'

        return res

if __name__ == '__main__':
    s = Solution()
    res = s.countAndSay(3)
