#!/bin/env python
#-*- coding:utf-8 -*-
'''
@questions 59. Spiral Matrix II
@Difficulty Medium
@Pass 34.1%
@link https://leetcode.com/problems/spiral-matrix-ii/
@introduction
Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

For example,
Given n = 3,

You should return the following matrix:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
@res
'''

class Solution(object):
    #use spiral matrix I method,Your runtime beats 96.25% of pythonsubmissions
    def generateMatrix(self, n):
        """
        :type n: int
        :rtype: List[List[int]]
        """
        m=[]
        for i in range(n):
            tmp=[]
            for j in range(n):
                tmp.append(0)
            m.append(tmp)
        L,R,T,D=0,n,0,n
        x,y,num=0,0,1
        while L<R or T<D:
            # print 'L',L,'R',R,'T',T,'D',D,'x',x,'y',y
            #L->R
            if L<R:
                while x<R:
                    m[y][x]=num
                    num+=1
                    x+=1
                x-=1
                T+=1
                y+=1
            else:
                break
            #T->D
            if D>T:
                while y<D:
                    m[y][x]=num
                    num+=1
                    y+=1
                y-=1
                R-=1
                x-=1
            else:
                break
            #R->L
            if L<R:
                while x>=L:
                    m[y][x]=num
                    num+=1
                    x-=1
                x+=1
                D-=1
                y-=1
            else:
                break
            #D->T
            if D>T:
                while y>=T:
                    m[y][x]=num
                    num+=1
                    y-=1
                y+=1
                L+=1
                x+=1
            else:
                break
        return m

    '''
    https://leetcode.com/discuss/81793/python-recursive-solution-3-lines
    zip(*matrix [::-1]) :Rotate the matrix by 90 degrees (clockwise).
    '''
    def generateMatrix1(self, n):
        def gen(l, w, b):  #Generate a l*w Matrix. the begin number is b.
            return l * [[]] and [range(b, b+l)] + zip(*gen(w-1, l, b+l)[::-1])
        return gen(n, n, 1)

    '''
    use ~
    '''
    def generateMatrix2(self, n):
        if not n: return []

        G = [[1] * n for i in range(n)]
        count = 1
        for L in range(n - (n/2) ):
            for D in range(4):
                LM = (n + ~L)
                if L == LM: G[L][L] = count
                for i in range(L, LM):
                    if D == 0: G[L][i] = count
                    elif D == 1: G[i][~L] = count
                    elif D == 2: G[~L][~i] = count
                    elif D == 3: G[~i][L] = count
                    count += 1
        return G

if __name__ == '__main__':
    s=Solution()
    res=s.generateMatrix(3)
    print 'res',res



