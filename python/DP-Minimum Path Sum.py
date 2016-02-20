#!/bin/env python
#-*- coding:utf-8 -*-
'''
@questions 64. Minimum Path Sum
@Difficulty Medium
@Pass 34.1%
@link https://leetcode.com/problems/minimum-path-sum/
@introduction
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.
@res
DP
http://blog.csdn.net/wangjian8006/article/details/7487522

'''

import heapq


class Solution(object):
    #dp spaceO(m*n)
    def minPathSum(self, grid):
        """
        :type grid: List[List[int]]
        :rtype: int
        """
        m=len(grid)
        n=len(grid[0])
        dp=[]
        for i in range(0,m):
            tmp=[]
            for j in range(0,n):
                # print j,i,'-',grid[i][j]
                if j>0 and i>0:#middle
                    tmp.append( min(dp[i-1][j],tmp[j-1])+grid[i][j] )
                elif j>0 and i==0:#top
                    tmp.append( tmp[j-1]+grid[i][j] )
                elif j==0 and i>0:#left
                    tmp.append( dp[i-1][j]+grid[i][j] )
                else:#0,0
                    tmp.append( grid[i][j] )
            # print tmp
            dp.append(tmp)
        return dp[m-1][n-1]

    #dp O(n) https://leetcode.com/discuss/73107/python-solution-60-ms
    def minPathSum1(self, grid):
        m, n, dp = len(grid), len(grid[0]), [float('inf'), 0]
        print m,n
        dp += [float('inf')] * (n - 1)
        for i in xrange(1, m + 1):
            for j in xrange(1, n + 1):
                dp[j] = min(dp[j], dp[j - 1]) + grid[i - 1][j - 1]
                print i,j,dp
        return dp[-1]

    # Dijkstra's Algorithm https://leetcode.com/discuss/42429/python-solution-using-dijkstras-algorithm
    def minPathSum(self, grid):
        min_queue = []
        height = len(grid)
        width = len(grid[0])
        row = [None] * width
        visited = [row[:] for i in xrange(height)]
        goal = (height-1, width-1)
        top_left = (grid[0][0], (0, 0))
        heapq.heappush(min_queue, top_left)

        while min_queue:
            (cur_path_cost, coordinates) = heapq.heappop(min_queue)
            (cur_height, cur_width) = coordinates
            # Cell already visited with lower path_cost
            # ignore
            if visited[cur_height][cur_width]:
                continue
            else:
                visited[cur_height][cur_width] = True
            # Found target
            if coordinates == goal:
                return cur_path_cost
            # Go down only if there is still room to go down
            if cur_height + 1 < height:
                heapq.heappush(min_queue, (cur_path_cost + grid[cur_height+1][cur_width], (cur_height+1, cur_width)))
            # Go right
            if cur_width + 1 < width:
                heapq.heappush(min_queue, (cur_path_cost + grid[cur_height][cur_width+1], (cur_height, cur_width+1)))

if __name__ == '__main__':
    s=Solution()
    l=[
        [1,3,1,9],
        [2,5,4,3],
    ]
    res=s.minPathSum(l)
    print 'res',res







