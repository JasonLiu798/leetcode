package leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a collection of numbers, return all possible permutations.
 * For example,
 * [1,2,3] have the following permutations:
 * [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
 * 
 * @author Jason Liu
 *
 */
public class Permutations {
	
//	/**
//	 * fix 0~n-2,swap n-1,n,output 1
//	 * fix 0~
//	 * 
//	 * @param num
//	 * @return
//	 */
//	public List<List<Integer>> permute_fix(int[] num) {
//		return null;
//	}
	
	
	
	/**
	 * 1.get one
	 * 		no got: goto end
	 * 		got: goto2
	 * 2.check every gap
	 * 		no: goto1
	 * 		got: 
	 * 			copy old list,insert into one gap,goto 2
	 * return lists
	 * @param num
	 * @return
	 */
	public static List<List<Integer>> permute(int[] num) {
		List<List<Integer>> res =  new ArrayList();
		
		if(num.length<=0){
			return res;
		}else if(num.length==1){
			List tmp = new ArrayList();
			tmp.add(num[0]);
			res.add(tmp);
			return res;
		}else{
			/**
			 * 1.get one
			 * 		no got: goto end
			 * 		got: goto2
			 * 2.check every gap
			 * 		no: goto1
			 * 		got: 
			 * 			copy old list,insert into one gap,goto 2
			 */
			//Iterator<List<Integer>> itr = res.iterator();
			List<Integer> tmpListp = null;
			List<Integer> tmpListBFInsertp = null;//new LinkedList(tmpListp);
			
			int insertNum = 0;
			for(int i=0;i<num.length;i++){
				List<List<Integer>> tmpRes =  new ArrayList();
				int got=num[i];
				System.out.println("--------Start:"+got);
				tmpListp = new LinkedList();
				tmpListp.add(got);
				tmpRes.add(tmpListp);//add first
				int resLen = tmpRes.size();
				
				for(int j=0;j<resLen;j++){
					tmpListp = tmpRes.get(j);
System.out.print("get list");					
printList(tmpListp);					
					
					int times = tmpListp.size()+1;
					for(int insertNumIdx=0;insertNumIdx<num.length;insertNumIdx++){
						insertNum=num[insertNumIdx];
						times = tmpListp.size()+1;
						tmpListBFInsertp = new LinkedList(tmpListp);
						if(insertNum!=got){
System.out.println("GAPS:"+times);
							for(int k=0;k< times;k++){  //k = gap's index
								if(k==0){
System.out.println(k+",Frist:"+insertNum);
									tmpListp.add(k, insertNum );//first use list in the res
									//res.add(tmpListp);
									if(tmpListp.size()==num.length){
										res.add(tmpListp);
									}
									tmpRes.add(tmpRes.size(), tmpListp);
printList(tmpListp);				
								}else if(k>0){
System.out.println(k+",AF F:"+insertNum);
									tmpListp= new LinkedList(tmpListBFInsertp);
									tmpListp.add(k,insertNum);
									
									if(tmpListp.size()==num.length){
										res.add(tmpListp);
									}else{
										tmpRes.add(tmpRes.size(), tmpListp);
									}
printList(tmpListp);
								}
							}
						}
					}
					
				}					
				
			}
		}
		
		return res;
	}
	
	public static void printList(List l){
		for(int i=0;i<l.size();i++){
			System.out.print(l.get(i)+",");
		}
		System.out.println("");
	}
//			List tmp = new ArrayList();
//			tmp.add(num[0]);
//			tmp.add(num[1]);
//			res.add(tmp);
//			tmp = new ArrayList();
//			tmp.add(num[1]);
//			tmp.add(num[0]);
//		}else{
//			boolean[] isFix=new boolean[num.length];
//			for( boolean itr:isFix){
//				itr=true;
//			}
//			List<List<Integer>> res = new ArrayList();
//			
//			for(int i=0;i<num.length;i++){
//				
//				for(int j=num.length-1;j>num.length-2;j++){
//					
//				}
//			}
//		}
		
	public static List<List<Integer>> findSame(List<List<Integer>> ls){
		List<List<Integer>> res = new LinkedList();
		List<Integer> L1 = null;
		List<Integer> L2 = null;
		for(int i=0;i<ls.size();i++){
			L1=ls.get(i);
			for(int j=i+1;j<ls.size();j++){
				L2=ls.get(j);
				
				boolean isSame=true;
				for(int k=0;k<L2.size();k++){
					if(L1.get(k)!=L2.get(k)){
						isSame=false;
						break;
					}
				}
				if(isSame){
					res.add(L1);
				}
			}
		}
		
		return res;
	}

	
//	public static List<List<Integer>> deleteSame(List<List<Integer>> ls,List){
//		
//	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a={1,2,3,4};
		List<List<Integer>> res = permute(a);
		System.out.println("++++++RES:");
		for(int m=0;m<res.size();m++){
			printList(res.get(m));
		}
		List<List<Integer>> sameres = findSame(res);
		System.out.println("++++++SAME RES:");
		for(int m=0;m<sameres.size();m++){
			printList(sameres.get(m));
		}
		
		Iterator<List<Integer>> itr = sameres.iterator();
		while(itr.hasNext()){
			res.remove(itr.next());
		}
		
		System.out.println("++++++AF RES:");
		for(int m=0;m<res.size();m++){
			printList(res.get(m));
		}
		
		
		
		/*
		//12 3//end
		1 3 2
	//3 12
		//13 2//end 
		1 2 3
	//2 13
		//21 3//end
		2 3 1
		3 21
		//23 1//end
		2 1 3
	//1 23
	 * 
	 */
	}

}
