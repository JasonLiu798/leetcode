package leetcode;

import tools.CollectionTool;

import java.util.List;

/**
 * Hard
 * https://leetcode.com/problems/trapping-rain-water/
 * Trapping Rain Water
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 * For example,
 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 *
 * Created by JasonLiu on 2015/8/6.
 * AC Runtime: 400 ms
 */
public class TrappingRainWater {

	public static final int TYPELOW = -1;
	public static final int TYPEHIGH = 1;
	public static boolean isLog = false;
	/**
	 *
	 * @param height
	 * @return
	 */
	public int trap(int[] height) {
//		if(isLog)
//			CollectionTool.printArray(height);
		if(height==null||height.length<=2)
			return 0;
		int len = height.length-1;
		int sum = 0;
		int start = 0;

		if(height[1]>height[0])
			 start = findNextHighPoint(height,0);

		if(isLog)
			System.out.println("start "+start);

		for(int i=start;i<len-1;i++){
			int left = i;
			int right = findNextHighPoint(height, i);
			if(isLog)
				System.out.println(i+" right:"+right);
			if(right >0){
				sum+=calc(height,left,right);
				i=right-1;
			}else{
				break;
			}
		}
		return sum;
	}

	/**
	 * find next +-
	 * case 5,4,3,2,1
	 * 	return -1
	 * case 5,4,3,2,1,2
	 * 	return index of the last 2
	 * case 5,4,2,3,4,5,6,5
	 * 	return index of the 6
	 *
	 * if next
	 * @param arr
	 * @param now
	 * @return
	 */
	public int findNextHighPoint(int[] arr,int now){
		int res = -1;
		boolean gotHigher = false;

		if(now+2<arr.length) {
			int nowH = arr[now];
			int pre = now;
			int next = now + 1;

			if (arr[pre] <= arr[next]) {
				res= filter(TYPEHIGH, arr, pre, next);
			} else {
				//high[pre]>high[next],find next +,- or end
				int start = next;
				int nextLowHighestIdx = next;
				int tmpNextLowHighestIdx = next;

				for (int i = start; i < arr.length; i++) {
					if(isLog)
						System.out.println("i "+i);
					if (arr[i] >= nowH ) {
						//must pass the low point
						pre = i-1 ;//lower point
						next = i;
						res= filter(TYPEHIGH, arr, pre, next);
						gotHigher = true;
						break;
					} else {
						//not tall than
						pre = i-1;
						next = i;
						if(isLog)
							System.out.println("Lower,pre "+pre+",next "+next);

						int lowIdx = filter(TYPELOW,arr,pre,next);
						if(lowIdx+1<arr.length){
							pre = lowIdx;
							next = lowIdx+1;
							tmpNextLowHighestIdx = filter(TYPEHIGH,arr,pre,next);
							if( arr[tmpNextLowHighestIdx]<nowH && arr[tmpNextLowHighestIdx]>=arr[nextLowHighestIdx] ){
								nextLowHighestIdx= tmpNextLowHighestIdx;
								if(isLog)
									System.out.println("high change to "+nextLowHighestIdx);
							}else if( arr[tmpNextLowHighestIdx]>=nowH ){
								gotHigher = true;
								res = filter(TYPEHIGH, arr, tmpNextLowHighestIdx, tmpNextLowHighestIdx+1);
								break;
							}
							i=tmpNextLowHighestIdx;
						}else{
							break;
						}
					}
				}
				if(!gotHigher){
					if(isLog)
						System.out.println("nextLowHighestIdx "+nextLowHighestIdx);
					res = nextLowHighestIdx;
				}
			}
		}
		return res;
	}

	/**
	 * TYPELOW->find next lowest point
	 * TYPEHIGH->find next higher or equal point
	 * @param type
	 * @param high
	 * @param pre
	 * @param next
	 * @return
	 */
	public static int filter(int type,int[] high,int pre,int next){
		if(isLog){
			if(type == TYPELOW ){
				System.out.println("--FL,pre "+pre +",next "+next);
			}else{
				System.out.println("FH--,pre "+pre +",next "+next);
			}
		}

		if(next>high.length-1){
			System.out.println("len -1 "+( high.length-1));
			return pre;
		}
		for (int i = next; i < high.length; i++) {
			if(type == TYPELOW ){
				if (next < high.length && high[pre] > high[next]) {
					pre++;
					next++;
				} else {
					break;
				}
			}else if(type == TYPEHIGH ){
				//filter higher and equal
				if (next < high.length && high[pre] <= high[next]) {
					pre++;
					next++;
				} else {
					break;
				}
			}
		}
		if(isLog)
			if(type == TYPELOW ){
				System.out.println("--AF FL,pre "+pre +",next "+next);
			}else{
				System.out.println("AF FH--,pre "+pre +",next "+next);
			}

		return pre;
	}


	/**
	 * calc water area size
	 * @param h
	 * @param left high point
	 * @param right next high point
	 * @return
	 */
	public int calc(int[] h,int left,int right){
		int sum = 0;
		int heigh = min(h[left],h[right]);
		for(int i=left+1;i<right;i++){
			if(h[i]<=heigh)
				sum+=heigh-h[i];
		}
		return sum;
	}

	public int min(int a,int b){
		return a<=b?a:b;
	}

	public static void main(String[] args) {
		TrappingRainWater t = new TrappingRainWater();
		int[] h = {0,1,0,2,1,0,1,3,2,1,2,1};//=6
		//		   0 1 2 3 4 5 6 7 8 9 10 11
		int[] h1 = {4,2,3};//=1
		//			0 1 2
		int[] h2 = {1,5,4,3,2,1,2,2,2};//=1
		//			0 1 2 3 4 5 6 7 8
		int[] h3 = {0,7,1,4,6};
		//			0 1 2 3 4 5 6
		int[] h4 = {0,2,1,2,7,5};
		//			0 1 2 3 4 5 6
		int[] h5 = {0,5,6,4,6,1,0,0,2,7};//2+5+6+6+4=23
		//		    0 1 2 3 4 5 6 7 8 9 10 11
		int[] h6 = {5,2,1,2,1,5};
		//		    0 1 2 3 4 5
		int[] h7 = {10,2,1,4,1,3,5};
		//		     0 1 2 3 4 5 6
//		System.out.println(t.findNext(h,3));
//		System.out.println(t.calc(h3,1,4));
//		System.out.println(t.calc(h,8,10));
//		System.out.println(t.findNext(h2, 0));
//		System.out.println(t.trap(h1));
//		System.out.println(t.findPre(h2,6,1));
//		System.out.println(t.trap(h3));
//		System.out.println(t.filterHigh(h4,2,3));
//		System.out.println(t.filter(TYPEHIGH,h4,2,3));
//		System.out.println(t.filter(TYPELOW,h4,2,1));
//		System.out.println(t.filter(TYPELOW,h2,1,2));
//		System.out.println(t.findNextHighPoint(h4,0));
//		System.out.println(t.filter(TYPELOW, h1, 0, 1));
//		System.out.println(t.filter(TYPEHIGH, h1, 1, 2));
		System.out.println(t.trap(h));
	}
}
