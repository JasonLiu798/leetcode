package leetcode;

import tools.CollectionTool;
import tools.Interval;

import java.util.*;

/**
 * Hard
 * Insert Interval
 * https://leetcode.com/problems/insert-interval/
 *
 Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

 You may assume that the intervals were initially sorted according to their start times.

 Example 1:
 Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

 Example 2:
 Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

 This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].

 AC Runtime: 452 ms
 */
public class InsertInterval {
//	private static final boolean log = true;
	private static final boolean log = false;

	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
		if(intervals==null ){
			intervals = new LinkedList<>();
		}
		if(intervals.size()!=0) {
			Iterator<Interval> it = intervals.iterator();
			while (it.hasNext()) {
				Interval get = it.next();
				Interval res = merge(get, newInterval);
				if (res != null) {
					newInterval.start = res.start;
					newInterval.end = res.end;
					it.remove();
				}
			}

			if (log) {
				System.out.println("AF");
			}
			boolean add = false;
			if(intervals.size()==1){
				if(intervals.get(0).start>newInterval.start){
					if(log)
						System.out.println("add l");
					intervals.add(0,newInterval);
				}else{
					if(log)
						System.out.println("add 0");
					intervals.add(newInterval);
				}
			}else{
				for (int i = 0; i < intervals.size()-1; i++) {
					if( newInterval.end  < intervals.get(i).start ){
						if(log)
							System.out.println("aaa" );
						add = true;
						intervals.add(i, newInterval);
						break;
					}else if( newInterval.start > intervals.get(i).end ){
						if( intervals.get(i+1).start > newInterval.end ){
							intervals.add(i+1, newInterval);
							add = true;
							break;
						}
					}
				}
				if(!add){
					if(log)
						System.out.println("add last "+newInterval );
					intervals.add(newInterval);
				}
			}
		}
		if(intervals.size() == 0){
			intervals.add(newInterval);
		}
		return  intervals;
	}

	/**
	 *
	 * @param i1
	 * @param i2
	 * @return
	 */
	public Interval merge(Interval i1,Interval i2){

		Interval res = new Interval();
		/**
		 * 				st1  ed1
		 * st2  ed2
		 * 							st2  ed2
		 *
		 */
		if( ( i2.end <i1.start )|| (i1.end<i2.start) ){
			return null;
		}
		/**
		 * 			st1  	ed1
		 * 		st2  	ed2
		 * 		st2				ed2
		 */
		else if( i2.start <= i1.start ){
			res.start = i2.start;
			res.end = max(i1.end,i2.end);
		}
		/**
		 * 	st1 	ed1
		 * 		st2		ed2
		 */
		else if( i2.start>i1.start){
			res.start = i1.start;
			res.end = max(i1.end,i2.end);
		}
		if(log) {
			System.out.println("I1 " + i1 + ",I2 " + i2+"="+res);

		}
		return res;
	}

	public int max(int a,int b){
		return a>=b?a:b;
	}

	public static void main(String[] args) {
		int [] arr = {1,2,3,5,6,7,8,10,12,16};
//		int [] arr = {1,3,6,9};
//		int [] arr = {1,5};
//		int [] arr = {2,6,7,9};
		List<Interval> l = Interval.initList(arr);
		CollectionTool.printList(l);
		InsertInterval i = new InsertInterval();
//		List<Interval> res = i.insert(l,new Interval(4,9));
		List<Interval> res = i.insert(l,new Interval(6,8));
//		List<Interval> res = i.insert(l,new Interval(0,0));
//		List<Interval> res = i.insert(l,new Interval(15,18));
		CollectionTool.printList(res);

	}
}
