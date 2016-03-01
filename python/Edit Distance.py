#!/bin/env python
#-*- coding:utf-8 -*-
'''
@questions 72. Edit Distance
@Difficulty Hard
@Pass 28.1%
@link https://leetcode.com/problems/edit-distance/
@introduction
Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

a) Insert a character
b) Delete a character
c) Replace a character
@res
'''

class Solution(object):
    def minDistance(self, word1, word2):
        """
        :type word1: str
        :type word2: str
        :rtype: int
        """

'''
S1:
len(w1)!=len(w2)
w1=ab
w2=cdf
find w1's letter in w2
add leeter to w1 match w2,replace diff letter

S2:
len(w1)==len(w2)

'''

if __name__ == '__main__':
    s=Solution()
    res=s.minDistance()
    print 'res',res





