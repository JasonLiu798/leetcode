#!/bin/env python
#-*- coding:utf-8 -*-
'''
@Questions 48. Rotate Image
@Difficulty Medium
@Pass   33.7%
@link https://leetcode.com/problems/rotate-image/
@introduction
You are given an n x n 2D matrix representing an image.
Rotate the image by 90 degrees (clockwise).
Follow up:
Could you do this in-place?

0 1 2    [0,0~0,2]
3 4 5    [1,0~1,2]
6 7 8
=>
6 3 0
7 4 1
8 5 2

@res
rotate1,deep copy,Your runtime beats 13.25% of python submissions.
rotate2,use zip,Your runtime beats 71.81% of python submissions.
rotate3,transpose then exchange col,Your runtime beats 24.58% of python submissions.
'''

from myutil.Matrix import *
import copy

class Solution(object):

    def rotate1(self, matrix):
        """
        :type matrix: List[List[int]]
        :rtype: void Do not return anything, modify matrix in-place instead.
        """
        if matrix==None or len(matrix)<1:
            return
        height=len(matrix)
        width=len(matrix[0])
        if height==width and height==1:
            return
        # print 'w',width,'h',height
        oldMatrix=copy.deepcopy(matrix)
        if height<width:
            for i in range(width-height):
                matrix.append([])
        # printMatrix(matrix,'in')
        for oldRowIdx in range(width):
            matrix[oldRowIdx]=[]
            for resRowIdx in range(height):
                # print width-resRowIdx-1
                num=oldMatrix[height-resRowIdx-1][oldRowIdx]
                # num=oldMatrix[width-resRowIdx-1][oldRowIdx]
                matrix[oldRowIdx].append(num)
        # printMatrix(matrix,'in')

    def rotate2(self, matrix):
            """
            :type matrix: List[List[int]]
            :rtype: void Do not return anything, modify matrix in-place instead.
            """
            matrix[::] = zip(*matrix[::-1])

    def rotate(self, matrix):
        """
        :type matrix: List[List[int]]
        :rtype: void Do not return anything, modify matrix in-place instead.
        """
        n=len(matrix)
        for i in range(0,n):
            for j in range(i+1,n):
                temp=matrix[i][j]
                matrix[i][j]=matrix[j][i]
                matrix[j][i]=temp
        for i in range(0,n):
            for j in range(0,n//2):
                temp=matrix[i][j]
                matrix[i][j]=matrix[i][n-j-1]
                matrix[i][n-j-1]=temp

if __name__ == '__main__':
    s=Solution()
    m=getMatrix(3,4)
    printMatrix(m,'init')
    print printMatrix(zip(m[::-1]))
    print printMatrix(zip(*m[::-1]))
    '''

    s.rotate(m)
    printMatrix(m,'res')
    '''





