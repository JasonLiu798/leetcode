package com.jason.leetcode;

import java.util.BitSet;
import java.util.List;

/**
 * 89 Medium
 * GrayCode
 * https://leetcode.com/problems/gray-code/
 The gray code is a binary numeral system where two successive values differ in only one bit.
 Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.
 For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
 00 - 0
 01 - 1
 11 - 3
 10 - 2
 Note:
 For a given n, a gray code sequence is not uniquely defined.
 For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.
 For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
 */
public class GrayCode {
    public static boolean log = false;

    public List<Integer> grayCode(int n) {
        /*
        BitSet bs = new BitSet();
        int nn = n;
        int cnt = 0;
//        int mask = 0x01;
        while(cnt<32){
            int bit = nn & mask;
            nn=nn>>1;
            if(bit == 1){
                bs.set(cnt,true);
            }
            bit = bit>>1;
            cnt++;
        }
        if(log)
            System.out.println(bs);
        */

        return null;
    }
    private static int[] mask = {
            0x1,0x2,0x4,0x8,0x10,0x20,0x40,0x80,0x100,0x200,0x400,0x800,0x1000,0x2000,0x4000,0x8000,0x10000,0x20000,0x40000,0x80000,0x100000,0x200000,0x400000,0x800000,0x1000000,0x2000000,0x4000000,0x8000000,0x10000000,0x20000000,0x40000000,0x80000000
    };

    public static void generateMask(){
        int mask = 1;
        for(int i=0;i<32;i++){
            System.out.print(i+" 0x" + Integer.toHexString(mask) + ",");
            mask = mask<<1;
        }
        System.out.println();
        System.out.println("end ");
    }

    public int getBitCnt(int n){
        if(n<0){
            return 32;
        }else{
            for(int i=30;i>=0;i--){
//                if(mask[i]|n){

//                }
            }
        }
        return 0;
    }

    public int setN(int n,int idx,boolean oz){

        return n;
    }

//    public BitSet

}
