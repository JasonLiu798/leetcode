#!/bin/env python
#-*- coding:utf-8 -*-
'''
@questions 54. Spiral Matrix
@Difficulty Medium
@Pass 22.0%
@link
@introduction
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
For example,
Given the following matrix:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
You should return [1,2,3,6,9,8,7,4,5].
@res
'''

def printM(matrix):
    for i in matrix:
        print i

class Solution(object):
    def spiralOrder(self, matrix):
        """
        :type matrix: List[List[int]]
        :rtype: List[int]
        """
        if len(matrix)==0 or len(matrix[0])==0:
            return []
        # printM(matrix)
        m=matrix
        L,R,T,D=0,len(matrix[0]),0,len(matrix)
        x,y,res=0,0,[]
        while L<R or T<D:
            # print 'L',L,'R',R,'T',T,'D',D,'x',x,'y',y
            #L->R
            if L<R:
                while x<R:
                    res.append(m[y][x])
                    x+=1
                x-=1
                # R-=1
                T+=1
                y+=1
            else:
                break
            #T->D
            if D>T:
                while y<D:
                    res.append(m[y][x])
                    y+=1
                y-=1
                # D-=1
                R-=1
                x-=1
            else:
                break
            #R->L
            if L<R:
                while x>=L:
                    res.append(m[y][x])
                    x-=1
                x+=1
                D-=1
                y-=1
            else:
                break
            #D->T
            if D>T:
                while y>=T:
                    res.append(m[y][x])
                    y-=1
                y+=1
                # T+=1
                L+=1
                x+=1
            else:
                break
        return res

if __name__ == '__main__':
    s=Solution()
    m=[
        [ 1, 2, 3 ],
        [ 4, 5, 6 ],
        [ 7, 8, 9 ],
        [10,11,12 ]
    ]
    m=[
        [ 1, 2, 3 ],
        [ 4, 5, 6 ],
        [ 7, 8, 9 ]
    ]
    res=s.spiralOrder(m)
    print 'res',res



