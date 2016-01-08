package com.jason.tools;

import java.util.LinkedList;
import java.util.List;

public class Interval {
	     public int start;
	     public int end;
	     public Interval() { start = 0; end = 0; }
	     public Interval(int s, int e) {  start = s; end = e; }

	public static List<Interval> initList(int[] arr){
		List<Interval> l = new LinkedList<>();
		for(int i=0;i<arr.length;i+=2){
			l.add(new Interval(arr[i],arr[i+1]));
		}
		return l;
	}
	public static void printInterval(List<Interval> l ){
		for(int i=0;i<l.size();i++){
			System.out.print(l.get(i));
		}
		System.out.println();
	}

	@Override
	public String toString() {
		return "(" + start +"," + end +")";
	}
}
