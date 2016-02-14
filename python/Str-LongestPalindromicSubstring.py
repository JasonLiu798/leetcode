#!/bin/env python
#-*- coding:utf-8 -*-

'''
5. Longest Palindromic Substring
Difficulty: Medium
Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
https://leetcode.com/problems/longest-palindromic-substring/

s1:
    one letter check Palindromic O(n/2)
    all leeter check Palindromic n*O(n) ~= O(n^2)
    Time Limit Exceeded
s2:
    Manacher O(n)
    http://blog.csdn.net/ggggiqnypgjg/article/details/6645824/
    http://www.cnblogs.com/biyeymyhjob/archive/2012/10/04/2711527.html
'''

#import myutil.List as List
import logging

log = logging.getLogger('mylogger')
log.setLevel(logging.DEBUG)
# log.setLevel(logging.DEBUG)
ch = logging.StreamHandler()
ch.setLevel(logging.DEBUG)
ch.setFormatter(logging.Formatter('%(message)s'))
log.addHandler(ch)
class Solution(object):
    def longestPalindrome(self, s):
        # Transform S into T.
        # For example, S = "abba", T = "^#a#b#b#a#$".
        # ^ and $ signs are sentinels appended to each end to avoid bounds checking
        T = '#'.join('^{}$'.format(s))
        n = len(T)
        P = [0] * n
        C = R = 0
        for i in range (1, n-1):
            P[i] = (R > i) and min(R - i, P[2*C - i]) # equals to i' = C - (i-C)
            # Attempt to expand palindrome centered at i
            while T[i + 1 + P[i]] == T[i - 1 - P[i]]:
                P[i] += 1
            # If palindrome centered at i expand past R,
            # adjust center based on expanded palindrome.
            if i + P[i] > R:
                C, R = i, i + P[i]
        # Find the maximum element in P.
        maxLen, centerIndex = max((n, i) for i, n in enumerate(P))
        return s[(centerIndex  - maxLen)//2: (centerIndex  + maxLen)//2]

    def longestPalindrome1(self, s):
        res = ""
        for i in xrange(len(s)):
            # odd case, like "aba"
            tmp = self.helper(s, i, i)
            if len(tmp) > len(res):
                res = tmp
            # even case, like "abba"
            tmp = self.helper(s, i, i+1)
            if len(tmp) > len(res):
                res = tmp
        return res
    def helper(self, s, l, r):
        while l >= 0 and r < len(s) and s[l] == s[r]:
            l -= 1; r += 1
        return s[l+1:r]

    def longestPalindrome12(self, s):
        """
        :type s: str
        :rtype: str
        """
        if s==None:
            return None
        if len(s)<=1:
            return s
        idx=0
        res=''
        for w in s:
            log.debug('idx:%s,w:%s'% (idx,w))
            i=0
            for i in range(1,min(idx,len(s)-1-idx)+1):
                log.debug('idx:'+str(idx)+',i:'+str(i)+','+str(idx+i)+s[idx+i]+str(idx-i)+s[idx-i])
                if s[idx+i] != s[idx-i]:
                    i-=1
                    break
            log.debug('after i:%s'%i)
            if len(res)<1+i*2:
                res=s[idx-i:idx+i+1]
            log.debug('----str:%s-----'%res)
            idx+=1
        return res

if __name__ == '__main__':
    s=Solution()
    #   01234567
    # st='abcbabbb'
    # st='bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb'
    st='bb'
    print len(st)
    res= s.longestPalindrome(st)
    print 'result:',res,'len',len(res)

