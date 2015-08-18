package com.jason.leetcode;

import com.jason.tools.CollectionTool;

import java.util.*;

/**
 * Medium
 * Largest Number
 * https://leetcode.com/problems/largest-number/
 *
 */
public class LargestNumber {

	public static void print(List l){
		CollectionTool.printList(l);
	}
	public static boolean log = false;


	public static class Num{
		public int val;
		public int digit;
		public int headidx;
		public char[] arr;
		public char head;
//		public String head;

		@Override
		public String toString() {
			return "Num{" +
					"val=" + val +
					", digit=" + digit +
					", headidx=" + headidx +
					", arr=" + Arrays.toString(arr) +
					", head=" + head +
					'}';
		}
	}

	public static Comparator headCharD = new Comparator<Num>() {
		public int compare(Num o1, Num o2) {
//			return o2.arr[o2.headidx] - o1.arr[o1.headidx];
//			return -o2.head.compareTo(o1.head);
			return o2.head-o1.head;
		}
	};


	public static Comparator headDESC = new Comparator<Num>() {
//			@Override
//			public int compare(Character o1, Character o2) {
//				return o2-o1;
//			}

		public int compare(Num o1, Num o2) {
//			return o2.arr[o2.headidx] - o1.arr[o1.headidx];
//			return -o2.head.compareTo(o1.head);
			return -1;
		}
	};

	public static int maxIdx =0;

	public static List<Num> getNums(int[] nums){
		List<Num> l = new LinkedList<>();
//		int maxDigit = 0;
		for(int i=0;i<nums.length;i++){
			Num tmp = getNum(nums[i]);
			if(tmp.digit> maxIdx){
				maxIdx = tmp.digit;
			}
			l.add(tmp);
		}
		return l;
	}


	public String largestNumber(int[] nums) {
		if (nums.length <= 0) {
			return "";
		}
		if (nums.length == 1) {
			return nums[0] + "";
		}
		List<Num> lres = new LinkedList();
		List l = getNums(nums);
		bucketSort(l, -1, lres);
		StringBuffer res = new StringBuffer();
		StringBuffer res2 = null;
		if(log)
			res2=new StringBuffer();
		for(int i=0;i<lres.size();i++){
			res.append(lres.get(i).val);
			if(log)
				res2.append(lres.get(i).val+" ");
		}
		if(log)
			System.out.println(res2);
		return res.toString();
	}


	public static void bucketSort(List<Num> l,int idx,List<Num> res){//int maxidx,List<Num> res){
		if(log){
			print(l);
		}
		idx++;
		TreeMap<Character,List> bucket = new TreeMap();
		for(int i=0;i<l.size();i++){
			Num n = l.get(i);
			if( idx>=n.digit){
				n.head = '0';
			}else{
				if(n.arr[idx]=='0'){
					n.head = '0'-1;
				}else{
					n.head = n.arr[idx];
				}
			}
			List tmpl =  bucket.get(n.head);
			if(tmpl==null){
				tmpl = new LinkedList();
			}
			tmpl.add(n);
			bucket.put(n.head , tmpl);
		}
//		System.out.println("");
		System.out.println("after "+bucket);
		while(bucket.size()>0){
			List ll = (List) bucket.pollLastEntry().getValue();
			if(log){
				System.out.println("poll");
				print(ll);
			}
			if(ll.size()==1 || idx == maxIdx){
				for(int i=0;i<ll.size();i++){
					res.add((Num)ll.get(i));
				}
			}else{
				bucketSort(ll,idx,res);
			}
		}
		if(idx == maxIdx){
			return;
		}
	}



	public static Num getNum(int x){
		Num res= new Num();
		res.arr = String.valueOf(x).toCharArray();
		int digit = 1;
		res.val = x;
		res.headidx = 0;
		if(x<10) {
			res.digit = digit;
			res.head = (char)x;
		}else{
			while(x>0){
				x=x/10;
				digit++;
				if(x<10){
//					res.headidx = digit;
					break;
				}
			}
			res.digit = digit;
			res.head = res.arr[res.headidx];
		}
		return res;
	}




	/*
		public String largestNumber(int[] nums) {
			if (nums.length <= 0) {
				return "";
			}
			if (nums.length == 1) {
				return nums[0] + "";
			}

			StringBuffer res = new StringBuffer();

			Comparator headDESC = new Comparator<Num>() {
	//			@Override
	//			public int compare(Character o1, Character o2) {
	//				return o2-o1;
	//			}

				public int compare(Num o1, Num o2) {
					return o2.arr[o2.headidx] - o1.arr[o1.headidx];
				}
			};

			Comparator digASC = new Comparator<Num>() {
				@Override
				public int compare(Num o1, Num o2) {
					return o1.digit - o2.digit;
				}
			};

		//key:num of digit
		// value:list of same digit
		TreeMap<Integer, PriorityQueue<Num>> digitMap = new TreeMap<>();
//		TreeMap<Character,PriorityQueue<Num>> headMap = new TreeMap<>();//headDESC);
		TreeMap<Character, List<Num>> headMap = new TreeMap<>();//headDESC);


		// key:head
		// value:list of same head
		for (int i = 0; i < nums.length; i++) {
			Num tmp = getNum(nums[i]);
//			PriorityQueue tmpq = digitMap.get(tmp.digit);
//			if(tmpq==null){
//				tmpq = new PriorityQueue<>(nums.length, headcmp);
//				tmpq.add(tmp);
//				digitMap.put( tmp.digit, tmpq);
//			}else{
//				tmpq.add(tmp);
//			}
//			PriorityQueue tmph = headMap.get(tmp.arr[tmp.headidx]);
			List tmph = headMap.get(tmp.head);
			if (tmph == null) {
//				tmph = new PriorityQueue<>(nums.length,headDESC);
				tmph = new LinkedList<>();
				tmph.add(tmp);
				headMap.put(tmp.head, tmph);
			} else {
				tmph.add(tmp);
			}
		}

		if (log) {
			System.out.println(headMap);
		}

		StringBuffer res2 = null;
		if (log) {
			res2 = new StringBuffer();
		}
	}

		/*
		while (headMap.size() > 0) {
//			PriorityQueue q = headMap.pollLastEntry().getValue();
			List q = headMap.pollLastEntry().getValue();
			if (log)
				System.out.println(q);
			if (q.size() > 1) {
				int i = 0;
				while (q.size() > 0) {
					Num n = (Num) q.get(i);
//					if(n.headidx == n.digit-1){

//					res.append(n.val);
//					if(log)
//						res2.append(n.val+"-");
//					}else{
					if (n.headidx == n.digit - 1) {
						n.head = '0';
					} else {
						n.head = n.arr[n.head + 1];
					}
					n.headidx++;
//					q.offer(n);
//					}
				}
			}
		}
		*/


//				Num n = (Num) q.poll();
//				if(q.size()>0) {
//					Num n2 = (Num) q.poll();
//				}else{
//					res.
//				}
//				if( n.arr[n.headidx] == n2.arr[n2.headidx] ){
//
//				}
//
//
//				// head equal
//				while(n.arr[n.headidx] == n2.arr[n2.headidx]){
//
//				}
//			}else{
//				res.append( ((Num)q.poll()).val );
//			}




//		while(digitMap.size()>0){
//			PriorityQueue<Num> q = digitMap.pollFirstEntry().getValue();
//			while(q.size()>0)
//				res.append(q.poll().val);
//		}


		/**
		 * sort by head->List<Num>
		 *     	if(headidx == length)
		 *     		res.append
		 * 		headidx change to second digtal
		 *
		 *
//		return "";
		return res.toString();
	}
*/


//	public int getNextHead(Num n ){
//		int i= 1;
//		while(i<n.headidx){
//
//		}
//		return
//	}





}
