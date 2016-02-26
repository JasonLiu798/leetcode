#!/bin/env python
#-*- coding:utf-8 -*-
'''
@questions 73. Set Matrix Zeroes
@Difficulty Medium
@Pass 33.0%
@link https://leetcode.com/problems/set-matrix-zeroes/
@introduction
Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

click to show follow up.

Follow up:
Did you use extra space?
A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?
@res
'''

from myutil.Matrix import *
class Solution(object):
    def setZeroes(self, matrix):
        """
        :type matrix: List[List[int]]
        :rtype: void Do not return anything, modify matrix in-place instead.
        """
        w,h=len(matrix[0]),len(matrix)
        x,y=0,0
        zeros=[]
        while y<h:
            x=0
            while x<w:
                if matrix[y][x]==0:
                    zeros.append((y,x))
                x+=1
            y+=1
        for p in zeros:
            for x in range(w):#row set zero
                matrix[p[0]][x]=0
            for y in range(h):#col set zero
                matrix[y][p[1]]=0

    #O(m*n) https://leetcode.com/discuss/70466/java-python-o-1-space-11-lines-solution
    def setZeroes1(self, matrix):
        # First row has zero?
        m, n, firstRowHasZero = len(matrix), len(matrix[0]), not all(matrix[0])
        # Use first row/column as marker, scan the matrix
        for i in xrange(1, m):
            for j in xrange(n):
                if matrix[i][j] == 0:
                    matrix[0][j] = matrix[i][0] = 0
        # Set the zeros
        for i in xrange(1, m):
            for j in xrange(n - 1, -1, -1):
                if matrix[i][0] == 0 or matrix[0][j] == 0:
                    matrix[i][j] = 0
        # Set the zeros for the first row
        if firstRowHasZero:
            matrix[0] = [0] * n
    #https://leetcode.com/discuss/27909/a-python-o-m-n-solution
    def setZeroes2(self, matrix):
        zeroRows = set()
        zeroCols = set()
        for i in range(len(matrix)):
            for j in range(len(matrix[0])):
                if matrix[i][j] == 0:
                    zeroRows.add(i)
                    zeroCols.add(j)

        for row in zeroRows:
            matrix[row][:] = map(lambda x:0, matrix[row])

        for i in range(len(matrix)):
            for col in zeroCols:
                matrix[i][col] = 0

if __name__ == '__main__':
    s=Solution()
    m=[
        [1,2,3],
        [1,2,0],
        [0,2,3],
    ]
    m=[
        [0,0,0,5],
        [4,3,1,4],
        [0,1,1,4],
        [1,2,1,3],
        [0,0,1,1]
    ]
    printMatrix(m)
    res=s.setZeroes(m)
    printMatrix(m)
    print 'res',res





