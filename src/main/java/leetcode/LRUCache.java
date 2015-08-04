package leetcode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import tools.LRUnit;

/**
 * Design and implement Department Top Three Salaries.sql data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting Department Top Three Salaries.sql new item.
 * @author Jason Liu
 * Accepted	1356 ms
 */

/**
 * v4, use linked list,used item insert into first one,
 * when reach the capacity,delete the linklist's last one
 */


/**
 * v3 ,hashtable;only use value,date ;
 */
public class LRUCache{
	public HashMap<Integer, LRUnit> cacheMap;
	public int capacity;
	public LRUCache(int capacity) {
		this.capacity = capacity;
		cacheMap = new HashMap<Integer , LRUnit>();
	}
	
	public int get(int key) {
    	LRUnit lrunit =null;
    	boolean got = false;
    	lrunit = cacheMap.get(key);
    	if(lrunit!=null){
    		lrunit.flushDate();
    		return lrunit.getValue();
    	}else{
    		return -1;
    	}
    }
	
	public void set(int key, int value) {
    	LRUnit lrunit =  cacheMap.get(key);
    	if(lrunit!=null){// found ,just set new value
    		lrunit.setValue(value);
    		lrunit.flushDate();
    		return;
    	}else{// not found
    		int len = cacheMap.size();
    		if(len<capacity){// not reach capacity,new one
    			cacheMap.put(key, new LRUnit(value,System.nanoTime() ) );
    			return;
    		}else{//reach capacity,use one of the min(used_count)
    			Iterator iter = cacheMap.entrySet().iterator();
    			LRUnit tmpLrunitP = null;
    			long oldestDate = 0;
    			long tmpDate = 0; //tmpLrunitP.getUsed_date();
    			int oldestDateKey = -1;
    			int tmpKey = -1;
    			int i=0;
    			while (iter.hasNext()) {
    				Map.Entry entry = (Map.Entry) iter.next();
    				tmpKey = (int) entry.getKey();
    				tmpLrunitP = (LRUnit) entry.getValue();
    				tmpDate =  tmpLrunitP.getUsed_date();
    				
    				if(i==0){
    					oldestDate=tmpDate;
    					oldestDateKey = tmpKey;
    	    		}else{
    	    			if(  oldestDate > tmpDate  ){//tmpDate <minUsedCount ){
    	    				oldestDate=tmpDate;
        					oldestDateKey = tmpKey;
    	    			}
    	    		}
    				i++;
    			}

//System.out.println("Remove Key:"+oldestDateKey);    			
    			tmpLrunitP = cacheMap.remove(oldestDateKey);//cacheMap.get(minUsedKey);
    			tmpLrunitP.setValue(value);
    			tmpLrunitP.flushDate();
    			cacheMap.put(key,tmpLrunitP);
    			return;
    		}
    	}
    }

/**
 *v2 use hashtable
 *
public class LRUCache{
	public HashMap<Integer, LRUnit> cacheMap;
	public int capacity;
	public LRUCache(int capacity) {
		this.capacity = capacity;
		cacheMap = new HashMap<Integer , LRUnit>();
	}
	
	public int get(int key) {
    	LRUnit lrunit =null;
    	boolean got = false;
    	lrunit = cacheMap.get(key);
    	if(lrunit!=null){
    		lrunit.flushDate();
    		return lrunit.getValue();
    	}else{
    		return -1;
    	}
    }
	
	public void set(int key, int value) {
    	LRUnit lrunit =  cacheMap.get(key);
    	if(lrunit!=null){// found ,just set new value
    		lrunit.setNew(value);
    		
    		//cacheMap.put(key, lrunit);
    		//System.out.println("set new "+lrunit);
    		return;
    	}else{// not found
    		int len = cacheMap.size();
    		if(len<capacity){// not reach capacity,new one
    			cacheMap.put(key, new LRUnit(value,new Date() ) );
    			return;
    		}else{//reach capacity,use one of the min(used_count)
    			Iterator iter = cacheMap.entrySet().iterator();
    			LRUnit tmpLrunitP = null;
    			int tmpUsedCount = -1;
    			int minUsedCount = -1;
    			int minUsedKey = -1;
    			Date oldestDate = null;
    			Date tmpDate = tmpLrunitP.getUsed_date();
    			//LinkedList<Integer> minUsedCountKeys = new LinkedList();
    			int i=0;
    			while (iter.hasNext()) {
    				Map.Entry entry = (Map.Entry) iter.next();
    				int tmpKey = (int) entry.getKey();
    				tmpLrunitP = (LRUnit) entry.getValue();
    				tmpDate =  tmpLrunitP.getUsed_date();
    				
    				tmpUsedCount = tmpLrunitP.getUsed_count();
    				if(i==0){
    					minUsedCountKeys.add( tmpKey);
    	    			minUsedKey = tmpKey;
    	    			minUsedCount = tmpUsedCount;
    	    		}else{
    	    			if(tmpUsedCount<minUsedCount ){
    	    				minUsedCountKeys.clear();
    	    				minUsedKey = tmpKey;
    	    				minUsedCount=tmpUsedCount;
    	    				minUsedCountKeys.add(tmpKey);
    	    			}else if(tmpUsedCount == minUsedCount ){
    	    				minUsedCountKeys.add(tmpKey);
    	    				
    	    			}
    	    		}
    				i++;
    			}
//CollectionTool.printList(minUsedCountKeys);

    			//found the min used_date
    			int j=0;
    			//int minUsedKey = -1;
    			minUsedKey = -1;
    			int tmpMinUsedKey = -1;
    			
    			int idxMinDateKey = -1;
    			for(i=0;i<minUsedCountKeys.size();i++){
    				tmpMinUsedKey = minUsedCountKeys.get(i);
//System.out.println("Key"+tmpMinUsedKey);		
    				tmpLrunitP = cacheMap.get(tmpMinUsedKey );
    				
    				Date tmpDate = tmpLrunitP.getUsed_date();
					if(i==0){
						oldDate = tmpDate; 
						idxMinDateKey = tmpMinUsedKey;
					}else{
						if( oldDate .after( tmpDate )  ){
							oldDate = tmpDate;
							idxMinDateKey = tmpMinUsedKey;
						}
					}
    			}
System.out.println("Remove Key:"+idxMinDateKey);    			
    			tmpLrunitP = cacheMap.remove(idxMinDateKey);//cacheMap.get(minUsedKey);
    			tmpLrunitP.setNew(value);
    			cacheMap.put(key,tmpLrunitP);
    			return;
    		}
    	}
    }
	
*/


/**
 * v1
 * @author Jason Liu
 *
 *
public class LRUCache {
	
	public LinkedList<LRUnit> cacheArr;
	public int capacity;
	public LRUCache(int capacity) {
		this.capacity = capacity;
		Date now = new Date();
		cacheArr = new LinkedList();
    }
    
    public int get(int key) {
    	LRUnit lrunit =null;
    	boolean got = false;
    	for(int i=0;i<cacheArr.size();i++){
    		LRUnit tmp =cacheArr.get(i); 
    		if(tmp.getKey() == key ){
    			//if(tmp.isSetValue()){
				got=true;
				lrunit = tmp;
				break;
    			//}
    		}
    	}
    	if(got){
    		lrunit.addCount();
    		//lrunit.flushDate();
    		return lrunit.getValue();
    	}else{
    		return -1;
    	}
    }
    
    /**
     * v1 with time
     * Time Limit Exceeded
     * @param key
     * @param value
     *
    public void set(int key, int value) {
    	LRUnit lrunit =null;
    	boolean got =false;
    	int len = cacheArr.size();
    	//int minUsedCount = -1;
    	//LinkedList<Integer> minUsedCountIdxs = new LinkedList();
    	for(int i=0;i<len;i++){
    		LRUnit tmp =cacheArr.get(i);
//    		if(tmp.isSetValue()){
//    			if(i==0){
//        			minUsedCount=tmp.getUsed_count();
//        			minUsedCountIdxs.add(i);
//        		}else{
//        			if(tmp.getUsed_count()<minUsedCount ){
//        				minUsedCountIdxs.clear();
//        				minUsedCountIdxs.add(i);
//        			}else if(tmp.getUsed_count() == minUsedCount ){
//        				minUsedCountIdxs.add(i);
//        			}
//        		}
			if(tmp.getKey() == key ){
    			got=true;
    			lrunit = tmp;
    			break;
    		}
    		//}
    	}
    	
    	if(!got){
    		if(len<capacity){// not reach capacity,new one
    			lrunit = new LRUnit(key,value,new Date());
    			lrunit.setSetValue(true);
    			cacheArr.add(lrunit);
    			return ;
    		}else{//reach capacity,clean one of the min(used_count), eq=>clean min(used_date)
    			if(minUsedCountIdxs.size() ==1){//only one used_count is min
    				lrunit = new LRUnit(key, value, new Date());
    				lrunit.setSetValue(true);
    				cacheArr.set( minUsedCountIdxs.get(0),lrunit);
    				return;
    			}else if(minUsedCountIdxs.size()>1){
    				Date minDate = null;
    				int idxMinDateIdx = -1;
    				for(int i=0;i<minUsedCountIdxs.size();i++){
    					int tmpIdx = minUsedCountIdxs.get(i);
    					Date tmpDate = cacheArr.get(tmpIdx).getUsed_date();
    					if(i==0){
    						minDate = tmpDate; 
    						idxMinDateIdx=tmpIdx;
    					}else{
    						if(minDate.after( tmpDate)  ){
    							minDate = tmpDate;
    							idxMinDateIdx=tmpIdx;
    						}
    					}
        			}
					lrunit = new LRUnit(key, value, new Date());
    				lrunit.setSetValue(true);
    				cacheArr.set( minUsedCountIdxs.get(idxMinDateIdx),lrunit);
    				return ;
    			}
    			
    		}
    		
    	}else{//key exist,just set value,flush date,add used_count
    		lrunit.setValue(value);
    		lrunit.addCount();
    		lrunit.flushDate();
    		lrunit.setSetValue(true);
    		return;
    	}
    }
    */
    
    /**
     * v2 no 
     * @param key
     * @param value
     *
    public void set(int key, int value) {
    	LRUnit lrunit =null;
    	boolean got =false;
    	int len = cacheArr.size();
    	int minUsedCount = -1;
    	int minUsedIdx = -1;
    	for(int i=0;i<len;i++){
    		LRUnit tmp =cacheArr.get(i);
    		int tmpCnt = tmp.getUsed_count();
    		if(i==0){
    			minUsedCount=tmpCnt;
    			minUsedIdx = i;
    		}else{
    			if(tmpCnt<minUsedCount ){
    				minUsedIdx = i;
    			}
    		}
			if(tmp.getKey() == key ){
    			got=true;
    			lrunit = tmp;
    			break;
    		}
    	}
    	
    	if(!got){
    		if(len<capacity){// not reach capacity,new one
    			lrunit = new LRUnit(key,value);//,new Date());
    			//lrunit.setSetValue(true);
    			cacheArr.add(lrunit);
    			return;
    		}else{//reach capacity,use one of the min(used_count)
    			lrunit  = cacheArr.get(minUsedIdx);
    			lrunit.setNew(key, value);
    			return ;
    		}
    	}
    }
    */
	/**
	 * v1 use arraylist
	 *
    public void printCache(){
    	System.out.println("----Cache---");
    	Iterator<LRUnit> itr =  cacheArr.iterator();
    	while ( itr.hasNext() ){
    		System.out.println(itr.next());
    	}
    }
    */
	
	/**
	 * v2 use map
	 */
    public void printCacheMap(){
    	Iterator iter = cacheMap.entrySet().iterator();
    	LRUnit lp = null;
    	System.out.println("Cache----");
    	while(iter.hasNext()){
    		Map.Entry entry = (Map.Entry) iter.next();
    		int tmpKey = (int) entry.getKey();
    		lp = (LRUnit) entry.getValue();
    		System.out.println( "Key:"+tmpKey +","+lp);
    	}
    	
		
    }
	public static void main(String[] args) {
		

//Last executed input:	1,[set(2,1),get(2),set(3,2),get(2),get(3)]
//		c.set(2,1);
//		c.printCacheMap();
//		System.out.println( c.get(2));
//		c.printCacheMap();
//		c.set(3,2);
//		c.printCacheMap();
//		System.out.println( c.get(2));
//		c.printCacheMap();
//		System.out.println( c.get(3));
//		c.printCacheMap();
				

		
		
		//2,[set(2,1),set(2,2),get(2),set(1,1),set(4,1),get(2)]
		/*
		LRUCache c = new LRUCache(2);
		c.set(2,1);
		c.set(2,2);
		System.out.println( c.get(2));
		c.printCacheMap();
		c.set(1,1);
		c.printCacheMap();
		c.set(4,1);
		c.printCacheMap();
		System.out.println( c.get(2));
		*/
		
		//2,[set(2,1),set(1,1),get(2),set(4,1),get(1),get(2)]
		//Output:	[1,1,-1]
		//Expected:	[1,-1,1]
		/*
		LRUCache c = new LRUCache(2);
		c.set(2,1);
		c.set(1,1);
		c.printCacheMap();
		
		System.out.println("get 2 " +c.get(2));
		System.out.println("afget2:");
		c.printCacheMap();
		System.out.println("set 4,1");
		c.set(4,1);
		c.printCacheMap();
		
		System.out.println("get 1 "+ c.get(1));
		c.printCacheMap();
		c.set(4,1);
		c.printCacheMap();
		System.out.println("get 2 "+ c.get(2));
		*/
		
		//2,[set(2,1),set(1,1),set(2,3),set(4,1),get(1),get(2)]
		//Output:	[1,-1]
		//Expected:	[-1,3]
		LRUCache c = new LRUCache(2);
		c.set(2,1);
		c.set(1,1);
		c.printCacheMap();
		c.set(2,3);
		c.set(4,1);
		
		System.out.println("get 1: " +c.get(1));
		System.out.println("afget1:");
		c.printCacheMap();
		System.out.println("get 2: " +c.get(2));
		System.out.println("afget2:");
		c.printCacheMap();
		
		
		
//		//4 3 4 
//		//2 3 1 4 2
//		c.set(4, 4);
//		c.set(3, 3);
//		c.get(4);
//		
//		c.printCacheMap();
//		c.set(2, 2);
//		c.printCacheMap();
//		c.get(3);
//		c.printCacheMap();
//		c.set(1, 1);
//		c.printCacheMap();
//		c.get(4);//, 4);
//		c.printCacheMap();
//		c.set(2,2);//, 2);
//		c.printCacheMap();
		
	}

}
