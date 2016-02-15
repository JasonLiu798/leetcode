#!/bin/env python
#-*- coding:utf-8 -*-

'''
@questions 62. Unique Paths
@Difficulty Medium
@Pass
@link https://leetcode.com/problems/unique-paths/
@introduction
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
How many possible unique paths are there?
Above is a 3 x 7 grid. How many possible unique paths are there?

Note: m and n will be at most 100.

@res
'''

from collections import namedtuple
Point = namedtuple('Point', ['x', 'y','d'])
# p = Point(1, 2,0)  #0=init  1=right  2=down
# print p.x #1
# print p.y #2

TPM=1
TPR=2
TPD=3
TPF=4

import math

class Solution(object):
    '''
    robot needs to move a total of n + m - 2 steps, m - 1 of which should be moving downwards. So the total number of steps should be (n+m-2) choose (m-1)
    '''
    def uniquePaths(self, m, n):
        """
        :type m: int
        :type n: int
        :rtype: int
        """
        return math.factorial(m+n-2)/(math.factorial(m-1)*math.factorial(n-1))


    def bfs(self,prepath,res):
        pass
    def getPointType(self,point,w,h):
        if point.x!=w and point.y!=h: #中间部分
            return TPM
        elif point.x==w and point.y!=h: #最右边，只能往下
            return TPR
        elif point.x!=w and point.y==h:#最下边，只能往右
            return TPD
        else:
            return TP

if __name__ == '__main__':
    s=Solution()
    res=s.uniquePaths(3,10)
    print 'res',res


