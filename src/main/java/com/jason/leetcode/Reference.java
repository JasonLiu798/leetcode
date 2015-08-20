package com.jason.leetcode;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/**
 * Created by JasonLiu on 2015/8/19.
 */
public class Reference {

	public static class Car {
		private double price;
		private String colour;

		public Car(double price, String colour){
			this.price = price;
			this.colour = colour;
		}

		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public String getColour() {
			return colour;
		}
		public void setColour(String colour) {
			this.colour = colour;
		}

		public String toString(){
			return colour +"car costs $"+price;
		}

	}
	//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
	public static void main(String[] args) {

		WeakReference<Object> wr = new WeakReference<Object>(new Object());
		int i=0;
		Object sr2 = wr;
		while(true){
			System.out.println(i);
			if(wr.get()!=null){
				i++;
				System.out.println("wr can reach "+wr.get());
			}else{
				System.out.println("null");
				break;
			}
			System.gc();
			if(i>5){
				sr2 = null;
			}
		}
	}
}
