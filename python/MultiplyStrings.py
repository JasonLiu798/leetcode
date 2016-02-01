#!/bin/env python
#-*- coding:utf-8 -*-
'''
@questions 43. Multiply Strings
@Difficulty Medium
@link https://leetcode.com/problems/multiply-strings/
@Pass   22.5%
@introduction
Given two numbers represented as strings, return multiplication of the numbers as a string.
Note: The numbers can be arbitrarily large and are non-negative.
'''


class Solution(object):
    def multiply(self, num1, num2):
        """
        :type num1: str
        :type num2: str
        :rtype: str
        """
        if nums1 == '0' or nums2=='0':
            return '0'
        n1=long(num1)
        n2=long(num2)
        return str(n1*n2)


    def multiply1(num1, num2):
        product = [0] * (len(num1) + len(num2))
        pos = len(product)-1

        for n1 in reversed(num1):
            tempPos = pos
            for n2 in reversed(num2):
                product[tempPos] += int(n1) * int(n2)
                product[tempPos-1] += product[tempPos]/10
                product[tempPos] %= 10
                tempPos -= 1
            pos -= 1

        pt = 0
        while pt < len(product)-1 and product[pt] == 0:
            pt += 1

        return ''.join(map(str, product[pt:]))

if __name__ == '__main__':
    s=Solution()
    res=s.
    print res





