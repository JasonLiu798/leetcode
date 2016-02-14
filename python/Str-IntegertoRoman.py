#!/bin/env python
#-*- coding:utf-8 -*-
'''
12. Integer to Roman
Difficulty: Medium
Input is guaranteed to be within the range from 1 to 3999.
https://leetcode.com/problems/integer-to-roman/

1.相同数字连写 III
2.小的数字在大的数字的右边，所表示的数等于这些数字相加得到的数，如 Ⅷ=8、Ⅻ=12
3.小的数字(限于I X C)在大的数字的左边，所表示的数等于大数减小数得到的数，如 Ⅳ=4、Ⅸ=9
4.在一个数的上面画一条横线，表示这个数增值 1,000 倍，如=5000
'''

class Solution(object):
    NUM={1:'I',5:'V',10:'X',50:'L',100:'C',500:'D',1000:'M'}
    def intToRoman(self, num):
        """
        :type num: int
        :rtype: str
        """
        snum=str(num)
        multiple=1
        for i in range(len(snum)-1):
            multiple=multiple*10
        # print 'multiple',multiple
        res=''
        for i in snum:
            res+=self.singleInt(int(i),multiple)
            multiple/=10
        return res

    def singleInt(self,num,multiple):
        res=''
        oneStr=self.NUM[multiple]
        if num >=1 and num <=3: #I,II,III;X XX XXX ,
            for i in range(1,num+1):
                res+=oneStr
        elif num>=4 and num<=8: #IV,  XL,       V,VI,VII,VIII, #IX
            halfStr=self.NUM[multiple*5]
            if num==4:
                res += oneStr+halfStr
            elif num==5:
                res += halfStr
            else:
                res+=halfStr
                for i in range(1,num-4):
                    res+=oneStr
        elif num==9:
            nxtMultiple=self.NUM[multiple*10]
            res+=oneStr+nxtMultiple
        return res

if __name__ == '__main__':
    s = Solution()
    print s.intToRoman(123)
    print s.intToRoman(2954)
    # for i in range(1,10):
        # print s.singleInt(i,100)





