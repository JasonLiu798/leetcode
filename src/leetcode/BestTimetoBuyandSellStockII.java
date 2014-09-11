package leetcode;

import tools.PricePoint;

/**
 * Best Time to Buy and Sell Stock II	2012-10-30	36.8%
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * 
 * @author Jason Liu
 *
 */
public class BestTimetoBuyandSellStockII {
	
	
	/**
	 * 1.clean the duplicate numbers
	 * 2.find every high point and low point
	 * 3.each high point minus low point behind
	 * 4.add them all
	 * @param prices
	 * @return
	 */
	public static int maxProfit(int[] prices) {
		
		//clean duplicate
        int[] prices_no_repeat = new int[prices.length];
        int repeat_cnt = 0;
        for(int i=0,j=0;i<prices.length;i++){
        	
        	if(prices[i]==prices[i+1]){
        		prices_no_repeat[j]=prices[i];
        		repeat_cnt++;
        	}else{
        		prices_no_repeat[j]=prices[i];
        		j++;
        	}
        }
        int act_len = prices.length - repeat_cnt;
		//length=1
        if(act_len <=1 ){
        	return 0;
        }else if(act_len ==2){//length=2
        	if(prices[0]>=prices[1]){
        		return 0;//prices[0];
        	}else{
        		return prices[1]-prices[0];
        	}
        }else{//length>3
        	/* 
             * -1 +1 lowpoint
             * +1 -1 highpoint
             */
            int m=0;
            PricePoint[] pp_ar = new PricePoint[act_len];
            //start point
            pp_ar[m]= new PricePoint( prices_no_repeat[0], prices_no_repeat[1]-prices_no_repeat[0]>0?false:true);
            m++;
            for(int i=0;i<act_len-2;i++){
            	int gap_pre =  prices_no_repeat[i+1] - prices_no_repeat[i];
            	int gap_last = prices_no_repeat[i+2] - prices_no_repeat[i+1];
            	
            	if(gap_pre < 0 && gap_last > 0 ){	//low point
            		pp_ar[m] = new PricePoint(prices_no_repeat[i+1],false);
            		m++;
            	}else if(gap_pre > 0 && gap_last < 0 ){	//high point
            		pp_ar[m] = new PricePoint(prices_no_repeat[i+1],true);
            		m++;
            	}else{
            		
            	}
            }
            int max_profit = 0;
            //end point
            pp_ar[m]= new PricePoint(prices_no_repeat[act_len-1],prices_no_repeat[act_len-1]-prices_no_repeat[act_len-2]>0?false:true);
            //add every [lp,hp]  
            for(int i=0;i<m;i++){
            	if( !pp_ar[m].is_high && pp_ar[m+1].is_high){
            		int tmp_profit = pp_ar[m+1].price-pp_ar[m].price;
            		if(tmp_profit > max_profit){
            			max_profit += tmp_profit;
            		}
            	}
            }
            return max_profit;
        }
    }
	
	public static void main(String[] args) {
		int[] prices = {4,9,11,3,1,10,8};
		//11-4+10-1=16
		int res = maxProfit(prices);
		System.out.println(res);

	}

}
