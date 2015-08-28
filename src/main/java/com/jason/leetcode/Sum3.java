package com.jason.leetcode;

import java.util.*;

/**
 * Medium
 * 3 Sum
 * https://leetcode.com/problems/3sum/
 *
 */
public class Sum3 {


    /**
     * add each two nums,then find the third num
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        HashMap<Integer,Set<Container>> hm = new HashMap<>();
        int len = nums.length;
        for(int i=0;i<len;i++){
            if(i+1<len) {
                for (int j = i + 1; j < len; j++) {
                    int third = -(nums[i]+nums[j]);
                    hm.get(third);

                    Container c = new Container(i,j,third);
                    hm.put(third,c);
                }
            }else{
                break;
            }
        }
        return null;
    }

    public static class Container{
        int indexA;
        int indexB;
        int indexC;
        int third;
        public Container(){
        }
        public Container(int a,int b,int third){
            this.indexA = a;
            this.indexB = b;
            this.third = third;
        }

        @Override
        public String toString() {
            return "Container{" +
                    "indexA=" + indexA +
                    ", indexB=" + indexB +
                    ", indexC=" + indexC +
                    ", third=" + third +
                    '}';
        }
    }

    /**
     * three nest loops
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        if(nums ==null){
            return res;
        }
        int len = nums.length;
        if(len <=1){
            return res;
        }
        List<Set<Integer>> sets = new LinkedList<>();
        int a,b,c;
        for(int i=0;i<len;i++){
            a= nums[i];
            if(i+1<len) {
                for (int j = i+1; j < len; j++) {
                    b = nums[j];
                    if(j+1<len) {
                        for (int k = j+1; k < len; k++) {
                            c = nums[k];
                            if(a+b+c == 0){
                                Set<Integer> tmp = new HashSet<>();
                                tmp.add(a);
                                tmp.add(b);
                                tmp.add(c);
                                if(!sets.contains(tmp)){
                                    sets.add(tmp);
                                    List<Integer> tmpl = new LinkedList<>();
                                    tmpl.add(a);tmpl.add(b);tmpl.add(c);
                                    res.add(tmpl);
                                }

                            }
                        }
                    }
                }
            }
        }
        return res;
    }


}
