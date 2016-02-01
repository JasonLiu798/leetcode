#!/bin/env python
#-*- coding:utf-8 -*-


def getMatrix(w=3,h=3):
    '''
     return type matrix: List[List[int]]
    '''
    cnt=0
    res=[]
    for i in range(h):
        line=[]
        for j in range(w):
            line.append(cnt)
            cnt+=1
        res.append(line)
    return res

def printMatrix(m,name='dft'):
    print 'Matrix %s:' %name
    for line in m:
        print line

# print printMatrix(getMatrix())
