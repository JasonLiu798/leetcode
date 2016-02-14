#!/bin/env python
#-*- coding:utf-8 -*-

'''
28. Implement strStr()
easy
https://leetcode.com/problems/implement-strstr/
Implement strStr().
Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
'''

class Solution(object):
    #kmp
    def strStr(self, haystack, needle):
        if haystack==None or needle==None :
            return -1
        if len(haystack)==0 and len(needle)==0:
            return 0
        if len(needle)==0:
            return 0
        if len(haystack)==0:
            return -1
        return self.kmp_matcher(haystack,needle)

    def compute_prefix_function(self,p):
        m = len(p)
        pi = [0] * m
        k = 0
        for q in range(1, m):
            while k > 0 and p[k] != p[q]:
                k = pi[k - 1]
            if p[k] == p[q]:
                k = k + 1
            pi[q] = k
        return pi

    def kmp_matcher(self,haystack, needle):
        hlen = len(haystack)
        nlen = len(needle)
        pi = self.compute_prefix_function(needle)
        q = 0
        for i in range(hlen):
            while q > 0 and needle[q] != haystack[i]:
                q = pi[q - 1]
            if needle[q] == haystack[i]:
                q = q + 1
            if q == nlen:
                return i - nlen + 1
        return -1

    #bm
    def BoyerMooreHorspool(self,pattern, text):
        m = len(pattern)
        n = len(text)
        if m > n: return -1
        skip = []
        for k in range(256): skip.append(m)
        for k in range(m - 1): skip[ord(pattern[k])] = m - k - 1
        skip = tuple(skip)
        k = m - 1
        while k < n:
            j = m - 1; i = k
            while j >= 0 and text[i] == pattern[j]:
                j -= 1; i -= 1
            if j == -1: return i + 1
            k += skip[ord(text[k])]
        return -1


    #TLE
    def strStr1(self, haystack, needle):
        """
        :type haystack: str
        :type needle: str
        :rtype: int
        """
        res=-1
        if haystack==None or needle==None or len(haystack)<=0 or len(needle)<=0:
            return -1
        hidx=0
        while hidx<len(haystack):
            same=True
            bfHidx=hidx
            nidx=0
            while nidx<len(needle) and hidx<len(haystack):
                # print 'h',haystack[hidx],'n',needle[nidx]
                if haystack[hidx]!=needle[nidx]:
                    same=False
                    break
                else:
                    nidx+=1
                    hidx+=1
            # print 'same',same
            if same and nidx==len(needle):
                res=bfHidx
                break
            hidx=bfHidx+1
        return res


if __name__ == '__main__':
    s = Solution()
    s1='abcde'
    s2='bc'
    res=s.strStr(s1,s2)
    print res














