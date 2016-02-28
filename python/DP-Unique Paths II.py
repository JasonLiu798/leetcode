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
from myutil.Matrix import *

class Solution(object):

    #TLE
    def uniquePathsWithObstacles(self, obstacleGrid):
        """
        :type obstacleGrid: List[List[int]]
        :rtype: int
        """
        g=obstacleGrid
        dp=[]
        x,y,w,h=0,0,len(g[0]),len(g)
        if w<=0 or h<=0:
            return 0
        if g[0][0]==1 or g[h-1][w-1]==1:
            return 0
        cnt=[1]
        reach=[False]
        self.bfs(g,x,y,w,h,cnt,reach)
        res=0
        if reach[0]:
            res=cnt[0]
        return res
    def bfs(self,g,x,y,w,h,cnt,reach):
        # print x,y
        if g[y][x]==1:
            return
        else:
            tmp=0
            if x+1<w and g[y][x+1]!=1:
                tmp+=1
                self.bfs(g,x+1,y,w,h,cnt,reach)
            if y+1<h and g[y+1][x]!=1:
                if tmp==1:
                    cnt[0]+=1
                self.bfs(g,x,y+1,w,h,cnt,reach)
            if x==w-1 and y==h-1:
                reach[0]=True
        return

    #dp https://leetcode.com/discuss/84891/python-dp-with-comments
    def uniquePathsWithObstacles1(self, obstacleGrid):
        if obstacleGrid[0][0] == 1:
            return 0
        n = len(obstacleGrid)
        m = len(obstacleGrid[0])
        dp = [[0 for i in range(m)] for i in range(n)]
        dp[0][0] = 1
        #init first row
        for j in range(1,m):
            if dp[0][j-1] == 0:
                break
            if obstacleGrid[0][j] == 0:
                dp[0][j] = 1
        #init first column
        for i in range(1,n):
            if dp[i-1][0] == 0:
                break
            if obstacleGrid[i][0] == 0:
                dp[i][0] = 1
        for i in range(1,n):
            for j in range(1,m):
                #set up when grid can surpass
                if obstacleGrid[i][j] == 0:
                    dp[i][j] = dp[i-1][j] + dp[i][j-1]
        return dp[n-1][m-1]

    #space O(1)
    def uniquePathsWithObstacles(self, obstacleGrid):
        #we can reuse the matrix to save space if the requirement does not mention not to modify the    original matrix
        m, n = len(obstacleGrid), len(obstacleGrid[0])
        #need to take care special case
        if obstacleGrid[0][0]: return 0
        obstacleGrid[0][0] = 1
        #update the first column
        for i in range(1, m):
            obstacleGrid[i][0] = 0 if obstacleGrid[i][0] else obstacleGrid[i-1][0]
        #update the first row
        for j in range(1, n):
            obstacleGrid[0][j] = 0 if obstacleGrid[0][j] else obstacleGrid[0][j-1]
        #update the rest part
        for i in range(1, m):
            for j in range(1, n):
                obstacleGrid[i][j] = 0 if obstacleGrid[i][j] else obstacleGrid[i-1][j] + obstacleGrid[i][j-1]
        return obstacleGrid[-1][-1]

if __name__ == '__main__':
    s=Solution()
    g=[
        [0,0],
        [0,1]
    ]
    g=[
        [0,0],
        [1,1],
        [0,0],
    ]
    g=[
        [0,0,0],
        [0,1,0],
        [0,0,0]
    ]
    g=[
        [0,0]
    ]
    g=[
        [0,0,0,0,0],
        [0,0,0,0,0],
        [1,1,1,0,0],
        [0,0,1,0,0]
    ]

    g=[
        [0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0],
        [0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1],
        [0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0],
        [1,1,1,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,1,0,0,0,0,0,0,0,0,1,0,0,1],
        [0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0],
        [0,0,0,1,0,1,0,0,0,0,1,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,1,0],
        [1,0,1,1,1,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0],
        [0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,1,0,0,0,1,0,1,0,0,0,0,0,0],
        [0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,1,0],
        [0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,1,0,0,0,0,0],
        [0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0],
        [1,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0,1,0,0,0,1,0,1,0,0,0,0,1],
        [0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,1,0,0,0,0,0,0,1,1,0,0,0,0,0],
        [0,1,0,1,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,0,0,0,0,0],
        [0,1,0,0,0,0,0,0,1,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,1,0,1],
        [1,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
        [0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,1,0,0,1,0,0,0,0,0,0],
        [0,0,1,0,0,0,0,0,0,0,1,0,0,1,0,0,1,0,0,0,0,0,0,1,1,0,1,0,0,0,0,1,1],
        [0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,1,1,0,1,0,1],
        [1,1,1,0,1,0,0,0,0,1,0,0,0,0,0,0,1,0,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0],
        [0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,1,1],
        [0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,1,0,0,0,1,0,0,0]]
    printMatrix(g)
    res=s.uniquePathsWithObstacles1(g)
    # printMatrix(res)
    print 'res',res



