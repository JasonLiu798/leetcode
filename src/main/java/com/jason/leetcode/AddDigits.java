package com.jason.leetcode;

/**
 * Easy
 * Add Digits
 * https://leetcode.com/problems/add-digits/
 * AC Runtime: 304 ms
 */
public class AddDigits {

    public int addDigits(int num) {
        sum=0;
        add(num);
        return sum;
    }

    public static int sum;
    public void add(int num){
        char[] arr = String.valueOf(num).toCharArray();
        int tmpsum=0;
        for(int i=0;i<arr.length;i++){
           tmpsum += (int)(arr[i]-'0');
        }
        if(tmpsum>9){
            add(tmpsum);
        }else{
            sum = tmpsum;
        }
    }

}
