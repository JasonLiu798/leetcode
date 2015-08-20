package com.jason.leetcode;

import com.jason.tools.CollectionTool;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by JasonLiu on 2015/8/19.
 */
public class MergeSortedArray {

	public static boolean log = false;
	public static void printa(int[] l){
		CollectionTool.printArray(l);
	}

	public void movetoend(int[] arr,int al){
		int len = arr.length;
		for(int i=len-1,j=al-1;i>=0;i--,j--){
			arr[i]=arr[j];
		}
	}

	public void copy2end(int[] a,int al,int[] b,int bl){
		for(int i=al,j=0;j<bl;j++,i++){
			a[i]=b[j];
		}
	}


	public int getorder(int[] nums1,int m,int[] nums2,int n){
		int order = 0;
		if(nums1[0]>nums1[m-1]){
			order = -1;//desc
		}else if(nums1[0]==nums1[m-1]){
			if(nums2[0]>nums2[m-1]){
				order = -1;//desc
			}else if(nums1[0]==nums1[m-1]){
				order = 0;//all same
			}else{
				order = 1;
			}
		}else{
			order = 1;//asc
		}

		return order;
	}



	public void merge(int[] nums1, int m, int[] nums2, int n) {

		if(m<1 && n>1){
			for(int i=0;i<nums2.length;i++){
				nums1[i] = nums2[i];
			}
			return;
		}else if(m>1 && n<1){
			return;
		}else if(m>1 && n>1){
			if(log){
				System.out.println("start");
				printa(nums1);
				printa(nums2);
			}

			//check order
			int order = getorder(nums1,m,nums2,n);
			if(order==0){
				//copy arr2 to arr1 end
				System.arraycopy(nums2, 0, nums1, m, nums2.length);
				return;
			}
			if(log)
				System.out.println("order "+order);
			if(order>0){//asc
				if(nums1[0]>nums2[n-1]){
					//nums2 all < nums1
					//movetoend
					System.arraycopy(nums1, 0, nums1, nums1.length - m, m);
					//copy s2 in front
					System.arraycopy(nums2,0,nums1,0,nums2.length);
				}else if(nums1[n-1]<nums2[0]){
					//nums1 all < nums2
					//copy arr2 to arr1 end
					System.arraycopy(nums2, 0, nums1, m, nums2.length);
				}else{
					System.arraycopy(nums1,0,nums1,nums1.length-m,m);
					if(log){
						System.out.println("asc,after s1 move to end");
						printa(nums1);
					}
					int s1idx = nums1.length-m;
					int s2idx = 0;
					int end = Math.min(m,n);
					int ridx = 0;
					for(;s1idx<end||s2idx<end;){
						int n1 = nums1[s1idx];
						int n2 = nums2[s2idx];
						if(n1>n2){
							nums1[ridx]=n1;
							s1idx++;
							ridx++;
						}else if(n1==n2){
							nums1[ridx]=n1;
							s1idx++;ridx++;
							nums1[ridx]=n2;
							s2idx++;
							ridx++;
						}else if(n1<n2){
							nums1[ridx]=n2;
							s2idx++;
							ridx++;
						}
					}
					if(s1idx<m-1){
						if(log){
							System.out.println("num1 got left,s1idx"+s1idx+",ridx "+ridx+",len:"+(m-s1idx));
							printa(nums1);
						}
						//copy num1 left
						System.arraycopy(nums1,s1idx,nums1,ridx,m-s1idx);
					}else if(s2idx<n-1){
						if(log){
							System.out.println("num2 got left,s2idx"+s2idx+",ridx "+ridx+",len:"+(n-s2idx));
							printa(nums1);
						}
						//copy num2 left
						System.arraycopy(nums2,s2idx,nums1,ridx,n-s2idx);
					}
				}
			}else{//desc
				if(log)
					System.out.println("desc");
			}
			/*
			if(nums1[0]>nums2[n-1]){
				//nums2 all < nums1
				movetoend(nums1, m);
				System.arraycopy(nums2,0,nums1,0,nums2.length);
			}else if(nums1[n-1]<nums2[0]){
				//copy arr2 to arr1 end
				System.arraycopy(nums2, 0, nums1, m, nums2.length);
//				copy2end(nums1,m,nums2,n);
			}else{

				System.arraycopy(nums1,0,nums1,nums1.length-m,m);
				if(log){
					System.out.println("after move");
					printa(nums1);
				}
//				movetoend(nums1,m);
				int s1idx = nums1.length-m;
				int s2idx = 0;
				int end = Math.min(m,n);
				int ridx = 0;
				for(;s1idx<end||s2idx<end;){
					int n1 = nums1[s1idx];
					int n2 = nums2[s2idx];
					if(n1>n2){
						nums1[ridx]=n1;
						s1idx++;
						ridx++;
					}else if(n1==n2){
						nums1[ridx]=n1;
						s1idx++;ridx++;
						nums1[ridx]=n2;
						s2idx++;
						ridx++;
					}else if(n1<n2){
						nums1[ridx]=n2;
						s2idx++;
						ridx++;
					}
				}
				if(s1idx<m-1){
					if(log){
						System.out.println("num1 got left,s1idx"+s1idx+",ridx "+ridx+",len:"+(m-s1idx));
						printa(nums1);
					}
					//copy num1 left
					System.arraycopy(nums1,s1idx,nums1,ridx,m-s1idx);
				}else if(s2idx<n-1){
					if(log){
						System.out.println("num2 got left,s2idx"+s2idx+",ridx "+ridx+",len:"+(n-s2idx));
						printa(nums1);
					}
					//copy num2 left
					System.arraycopy(nums2,s2idx,nums1,ridx,n-s2idx);
				}
			}*/
		}
	}


}
