#!/bin/env python
#-*- coding:utf-8 -*-
'''
@questions 63. Unique Paths II
@Difficulty Medium
@Pass 29.0%
@link https://leetcode.com/problems/unique-paths-ii/
@introduction
Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

For example,
There is one obstacle in the middle of a 3x3 grid as illustrated below.

[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
The total number of unique paths is 2.

Note: m and n will be at most 100.
@res
'''


class Solution(object):
    def uniquePathsWithObstacles(self, obstacleGrid):
        """
        :type obstacleGrid: List[List[int]]
        :rtype: int
        """
        g=obstacleGrid
        dp=[]
        x,y,w,h=0,0,len(g[0]),len(g)
        init=0
        while y<h:
            tmp=[]
            while x<w:
                x+=1
                cnt=0
                if x<w:

                tmp.append(max(g[y][x],))
            dp.append(tmp)
            y+=1
            for n in m:
                print n
        print g


if __name__ == '__main__':
    s=Solution()
    g=[
        [0,0,0],
        [0,1,0],
        [0,0,0]
    ]
    res=s.uniquePathsWithObstacles(g)
    print 'res',res



