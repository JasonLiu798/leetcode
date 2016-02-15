#!/bin/env python
#-*- coding:utf-8 -*-
'''
@questions 11. Container With Most Water
@Difficulty Medium
@Pass 33.8%
@link https://leetcode.com/problemset/algorithms/
@introduction
Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
Note: You may not slant the container.

@res
1.find max height1 and height2,calc(miss some long areas)
'''

class Solution(object):
    def maxArea(self, height):
        """
        :type height: List[int]
        :rtype: int
        """
        L, R, width, res = 0, len(height) - 1, len(height) - 1, 0
        for w in range(width, 0, -1):
            if height[L] < height[R]:
                res, L = max(res, height[L] * w), L + 1
            else:
                res, R = max(res, height[R] * w), R - 1
        return res

    def maxArea2(self, height):
        last_a = 0
        last_b = 0
        maxx= 0
        last_bb = len(height)
        for i in xrange(0,len(height)-1):
            a = height[i]
            if last_a>= a:
                continue
            last_a = a
            for j in xrange(last_bb-1, i,-1):
                b = height[j]
                if b>=a:
                    maxx = max(maxx,(j-i)*a)
                    last_b = 0
                    if b==a:
                        last_bb = j
                    else:
                        last_bb = j+1
                    break
                if b>last_b:
                    last_b = b
                    maxx = max(maxx,(j-i)*b)
        return maxx

if __name__ == '__main__':
    s=Solution()
    l=[2,3,4,3,2,5]
    res=s.maxArea(l)
    print 'res',res






