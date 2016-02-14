#!/bin/env python
#-*- coding:utf-8 -*-
'''
@questions 50. Pow(x, n)
@Difficulty Medium
@Pass 27.8%
@link https://leetcode.com/problems/powx-n/
@introduction
Implement pow(x, n).
@res
'''

import math

class Solution(object):
    def myPow(self, x, n):
        """
        :type x: float
        :type n: int
        :rtype: float
        """
        if n==0:
            return 1
        if n==1:
            return x
        return math.pow( x,n )

    #when is too large,Memory Error
    def myPow1(self, x, n):
        if n==0:
            return 1
        if n==1:
            return x
        if n==-1:
            return 1 / x
        res = x
        for i in range(n-1):
            res*=x
        return res#math.pow( x,n )

    def myPow2(self, x, n):
        if n == 0 :
            return 1
        elif x in [0, 1] or n == 1:
            return x
        elif n == -1:
            return 1 / x
        half = self.myPow(x, n // 2 if n > 0 else (n+1) //2 )
        if n%2 == 0:
            return half * half
        elif n > 0:
            return half * half * x
        else:
            return half * half / x

    def myPow3(self, x, n):
        if n==0:
            return 1
        if n==1:
            return x
        if n==-1:
            return 1 / x
        return self.myPow2(x*x,n/2)* ([1, x][n % 2])

if __name__ == '__main__':
    s=Solution()
    # res=s.myPow(2,3)
    res=s.myPow2(0.00001,2147483647)
    print res






