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
	
	/**
	 * Tool function
	 * @param l
	 */
	public static void printList(List l){
		for(int i=0;i<l.size();i++){
			System.out.print(l.get(i)+",");
		}
		System.out.println("");
	}
	
	/**
	 * v3 swap method
	 * 
	 * new lists(oldlists);
	 * swap(lists[idx+1],lists[0]~lists[idx-1])
	 * 
	 * Accepted	476 ms	java
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
			//add first
			List<Integer> first = new LinkedList();
			for(int i=0;i<num.length;i++){
				first.add(i,num[i]);
			}
			res.add(first);
			List<Integer> tmpListP = null;
			List<Integer> tmpNewListP = null;
			//Iterator<List<Integer>> itr = null;//res.iterator();
			//swap list
			for(int idx=1;idx<num.length;idx++){
				int resLen = res.size();
				for(int resItr=0;resItr<resLen;resItr++){
					tmpListP = res.get(resItr);
					for(int tmpListItr=0;tmpListItr<idx;tmpListItr++){//swap 0~idx,last
						tmpNewListP = new LinkedList(tmpListP);
						int tmp = tmpNewListP.get(tmpListItr);
						tmpNewListP.set(tmpListItr,tmpNewListP.get(idx));
						tmpNewListP.set(idx,tmp);
						res.add(res.size(),tmpNewListP);
					}
//					System.out.println("====AF SWAP====");
//					for(int i=0;i<res.size();i++){
//						printList(res.get(i));
//					}
					
				}
				
			}
		}
		return res;
	}
	

	boolean [] isUsed;
    int numLength;
    ArrayList<ArrayList<Integer>> output;
    ArrayList <Integer> al;
    /**
     * Recursive
     * https://oj.leetcode.com/discuss/5367/recursive-java-solution
     * @param num
     * @return
     */
    public ArrayList<ArrayList<Integer>> permute_r(int[] num) {
        numLength = num.length;
        al = new ArrayList <Integer>();
        output = new ArrayList<ArrayList<Integer>>();
        isUsed = new boolean[num.length];
        doPermutation(0, num);
        return output;
    }
    public void doPermutation(int index, int[] num) {
        // base case
        if (index == numLength) {
            output.add((ArrayList<Integer>)al.clone());
            return;
        }
        for (int i = 0; i < numLength; i++) {
            if (!isUsed[i]) {
                al.add(num[i]);
                isUsed[i] = true;
                doPermutation(index + 1, num);
                isUsed[i] = false;
                al.remove(index);
            }
        }
    }
	
	
	
	/**
	 * Deprecated,too many duplications,must clean duplication
	 * v2
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
	 *
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
			 *
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
	
	
	List<List<Integer>> res = new LinkedList();
	
	
	
	public static void permute_(List<Integer> L){
		
	}
	
	
	
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
	*/
	
//	/**
//	 * v1
//	 *	fix 0~n-2,swap n-1,n,output 1
//	 * fix 0~
//	 * 
//	 * @param num
//	 * @return
//	 */
//	public List<List<Integer>> permute_fix(int[] num) {
//		return null;
//	}
	
	/*
	 * stick some
	1s 2 3
	1s 3 2
	
	3 2s 1
		3s 1 2
	2 1 3s
		2s 3 1
		
	1s 
		2s 3 4
		2s 4 3
		
		4 3s 2
			4s 2 3
		4 2 3s
			4s 3 2
-------
	1 2 3
	1 3 2
	
	
			
	*/	
	/*
	List<Integer> save;//=  new LinkedList();
	List<Boolean> visit = new  LinkedList();
	List<List<Integer>> v = new LinkedList();
	public List<List<Integer>> permute(int[] num) {
		v.clear();
	    int size = num.length;
	    save =  new LinkedList();
	    for(int i=0;i<size;i++){
	    	visit.add(false);
	    	save.add(0);
	    }
	    
//	    visit.resize(size);
//	    save.resize(size);

	    //fill(visit.begin(), visit.end(), false);
	    
	    dfs(num, 0, size,save);
	    return v;
	}
	
	void dfs(int[] num, int now, int size,List<Integer> sv){
		save =  new LinkedList(sv);
//		for(int i=0;i<size;i++){
//	    	visit.add(false);
//	    	save.add(0);
//	    }
	    if(now == size){
	    	
	        v.add(save);
	        return;
	    }
	    
	    for(int i = 0; i < size; i++){
	    	boolean vb;
	    	printList(visit);
	        if(!(vb = visit.get(i)) ){
	            visit.set(i, true);
	            save.set(now, num[i]);
	            
	            dfs(num, now + 1, size,save);
	            visit.set(i, false);
	        }
	    }
	}
	*/
	
	public static void main(String[] args) {
		int[] a={1,2,3,4};
		List<List<Integer>> res = new Permutations().permute(a);
		//List<ArrayList<Integer>> res = new Permutations().permute_r(a);
		System.out.println("++++++RES:");
		for(int m=0;m<res.size();m++){
			printList(res.get(m));
		}
		
		
//test find same		
//		List<List<Integer>> sameres = findSame(res);
//		System.out.println("++++++SAME RES:");
//		for(int m=0;m<sameres.size();m++){
//			printList(sameres.get(m));
//		}
//test remove same		
//		Iterator<List<Integer>> itr = sameres.iterator();
//		while(itr.hasNext()){
//			res.remove(itr.next());
//		}
//		
//		System.out.println("++++++AF RES:");
//		for(int m=0;m<res.size();m++){
//			printList(res.get(m));
//		}

		
	}

}
