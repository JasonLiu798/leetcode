package leetcode;

import tools.Reader;

/**
 * Container With Most Water
 * https://leetcode.com/problems/container-with-most-water/
 *
 Given n non-negative integers a1, a2, ..., an, where each represents ContainerWithMostWater.dat point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms ContainerWithMostWater.dat container, such that the container contains the most water.

 Note: You may not slant the container.
1 2 5 4 3 1 1
 * Created by JasonLiu on 2015/8/5.
 */
public class ContainerWithMostWater {

	public int min(int a,int b){
		return a<=b?a:b;
	}

	/**
	 * s1, two loop
	 * @param height
	 * @return
	 */
	public int maxArea(int[] height) {
		int len = height.length;
		int maxArea = 0;
		for( int i=0;i<len;i++ ){
			int left = height[i];
			for(int j=i+1;j<len;j++){
				int right = height[j];
				int wide = j-i;
				int area = wide * min(left, right);
				if(area>maxArea){
					maxArea = area;
				}
			}
		}
		return maxArea;
	}

	public static void main(String[] args) {
		long ts=System.currentTimeMillis();
//		Integer[] h = Reader.File2intArr("C:\\project\\leetcode-java\\src\\main\\resources\\ContainerWithMostWater.dat");
//		System.out.println(new ContainerWithMostWater().maxArea(h));
		System.out.println("cost "+ (System.currentTimeMillis()-ts)+" ms");
	}
}


/**
 Last executed input:

 */