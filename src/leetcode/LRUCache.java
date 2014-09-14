package leetcode;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import tools.ArrayTool;
import tools.LRUnit;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 * @author Jason Liu
 * Accepted	1356 ms
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
//ArrayTool.printList(minUsedCountKeys);		

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
		

//、、、、//c.set(883,1946);c.set(1154,1018);c.get(1003);c.get(156);c.get(1148);c.get(1044);c.set(311,2875);c.get(485);c.get(702);c.get(665);c.set(573,3262);c.get(1392);c.get(344);c.get(244);c.get(1195);c.set(1428,438);c.get(156);c.set(254,298);c.get(630);c.set(748,1430);c.get(107);c.get(714);c.set(192,187);c.get(222);c.get(897);c.set(1324,2215);c.get(7);c.set(1098,1366);c.get(380);c.get(1012);c.get(502);c.set(240,2572);c.set(946,1883);c.get(52);c.set(412,475);c.set(707,704);c.set(875,526);c.get(1044);c.get(1388);c.get(730);c.get(870);c.get(925);c.set(1261,1015);c.set(249,2980);c.get(1312);c.set(1298,423);c.set(575,1776);c.set(1054,277);c.get(858);c.get(810);c.set(128,2545);c.get(805);c.set(1291,2214);c.get(450);c.get(555);c.set(1185,1687);c.set(300,2696);c.set(1209,648);c.set(592,1795);c.set(726,127);c.set(981,2259);c.get(268);c.get(806);c.get(1053);c.get(482);c.get(184);c.set(941,2556);c.set(943,2824);c.set(137,936);c.set(210,2740);c.set(1163,783);c.get(1136);c.get(267);c.set(1240,839);c.get(1364);c.set(1258,1316);c.get(793);c.get(1281);c.set(98,2625);c.set(1007,1543);c.get(570);c.set(1042,2165);c.get(1128);c.set(1247,3163);c.set(1421,525);c.set(1200,911);c.set(580,2259);c.set(236,3082);c.set(779,581);c.set(1206,1335);c.get(641);c.set(36,2330);c.set(850,733);c.set(264,2323);c.set(783,1094);c.set(1179,650);c.get(605);c.get(1151);c.set(54,2294);c.get(786);c.get(964);c.set(77,2461);c.set(509,3042);c.set(1331,2363);c.get(88);c.get(211);c.get(797);c.get(864);c.get(796);c.get(337);c.set(507,1045);c.get(113);c.set(714,335);c.get(1347);c.set(987,1792);c.set(1069,350);c.get(993);c.get(668);c.set(497,2455);c.set(711,2165);c.set(186,2959);c.get(412);c.set(378,975);c.set(43,846);c.set(22,2167);c.set(298,2743);c.get(827);c.set(1194,2230);c.get(590);c.get(1304);c.get(520);c.set(556,2616);c.get(67);c.set(1422,838);c.get(919);c.get(942);c.set(1378,1558);c.get(15);c.set(1368,1308);c.get(744);c.get(519);c.get(869);c.get(1136);c.get(1357);c.get(436);c.get(1392);c.get(983);c.get(1368);c.set(1390,375);c.set(314,3147);c.set(1310,376);c.set(501,1350);c.set(530,1673);c.set(613,3151);c.get(1424);c.set(489,115);c.get(173);c.get(1099);c.set(1417,993);c.get(1394);c.get(728);c.get(198);c.get(961);c.get(500);c.get(1149);c.set(871,792);c.set(136,2696);c.get(267);c.get(570);c.set(315,530);c.get(254);c.set(281,1294);c.get(1087);c.get(798);c.set(1025,2705);c.get(1335);c.get(853);c.set(125,2338);c.set(376,751);c.set(974,416);c.get(570);c.set(1185,2777);c.set(598,3303);c.set(617,2604);c.get(1326);c.set(1200,2961);c.get(1105);c.get(254);c.set(141,1509);c.set(239,1845);c.get(342);c.get(801);c.get(1410);c.get(1048);c.get(376);c.get(1060);c.get(500);c.set(1284,2732);c.get(1337);c.get(1258);c.set(345,1033);c.get(1093);c.set(532,2105);c.set(1431,2721);c.set(939,447);c.set(275,76);c.get(411);c.get(438);c.get(733);c.set(1186,1364);c.get(103);c.get(957);c.get(1074);c.set(1319,1445);c.get(1419);c.set(453,2456);c.get(80);c.get(1392);c.get(851);c.set(754,1403);c.get(672);c.set(1000,551);c.get(202);c.get(371);c.get(391);c.get(843);c.set(77,2994);c.get(1040);c.get(1300);c.set(731,1019);c.set(8,2446);c.get(446);c.set(724,3245);c.get(1032);c.set(366,2030);c.get(628);c.get(291);c.set(331,1402);c.set(1122,2054);c.set(678,555);c.set(54,1304);c.set(559,3073);c.get(304);c.set(134,1400);c.set(910,1442);c.set(325,129);c.set(417,3251);c.get(146);c.set(223,439);c.set(1009,3013);c.get(294);c.set(718,1885);c.set(1229,679);c.get(184);c.set(305,235);c.set(1174,1768);c.set(62,1342);c.set(317,550);c.get(688);c.set(493,2044);c.set(1289,2001);c.get(1074);c.set(541,1457);c.set(1336,190);c.get(1305);c.get(1170);c.set(1141,396);c.get(1369);c.set(820,102);c.set(1417,769);c.get(102);c.get(1036);c.get(525);c.get(323);c.get(441);c.set(847,2625);c.set(362,77);c.set(822,2108);c.get(414);c.set(1156,2310);c.get(174);c.set(1131,70);c.get(188);c.set(692,319);c.set(941,2986);c.get(383);c.get(688);c.get(1177);c.get(737);c.set(815,1655);c.get(43);c.set(965,2113);c.set(703,2628);c.get(1152);c.get(343);c.set(258,3019);c.get(125);c.get(887);c.get(452);c.set(579,1692);c.get(1428);c.get(273);c.get(1275);c.set(383,1574);c.set(198,1526);c.get(530);c.get(784);c.set(555,868);c.get(796);c.get(1375);c.get(450);c.set(804,1435);c.get(1137);c.set(611,2556);c.set(310,3154);c.set(558,1024);c.set(974,94);c.get(149);c.get(674);c.get(931);c.set(1181,2632);c.get(1174);c.set(1313,693);c.set(124,1772);c.set(1156,1284);c.get(1280);c.set(294,1742);c.set(1048,3248);c.set(434,339);c.set(921,1015);c.set(808,3086);c.get(804);c.get(882);c.get(1155);c.get(1342);c.set(337,2268);c.set(1062,2258);c.get(1025);c.get(908);c.get(1242);c.set(1042,1632);c.set(625,1952);c.set(24,1248);c.set(776,2253);c.get(548);c.set(1294,1732);c.set(1369,2198);c.set(807,1677);c.set(206,607);c.get(536);c.set(766,3205);c.get(1232);c.get(1132);c.set(1192,2477);c.set(534,1684);c.set(1204,1662);c.set(477,1526);c.get(951);c.get(1303);c.set(352,3043);c.get(818);c.set(1220,963);c.set(867,73);c.set(555,1603);c.get(202);c.get(1386);c.set(1196,2302);c.get(1276);c.set(247,1804);c.get(1375);c.get(1198);c.get(504);c.get(1245);c.get(275);c.set(937,1973);c.get(1078);c.get(26);c.get(1019);c.set(1209,407);c.set(312,2948);c.get(61);c.get(679);c.set(132,3100);c.set(1174,61);c.set(1269,824);c.set(371,1995);c.get(902);c.set(856,1952);c.get(222);c.get(437);c.get(1273);c.set(1080,1245);c.set(1329,2760);c.set(159,1426);c.set(1429,206);c.set(869,1409);c.get(803);c.set(1171,3164);c.get(1218);c.get(1313);c.set(411,2887);c.set(264,746);c.set(418,952);c.get(249);c.get(170);c.get(814);c.set(434,2949);c.set(1301,265);c.get(238);c.get(1419);c.set(396,1441);c.get(150);c.set(659,1339);c.set(608,990);c.set(1396,1434);c.get(431);c.set(524,501);c.get(53);c.get(461);c.set(723,2752);c.get(854);c.set(192,2955);c.get(562);c.set(1033,2052);c.get(839);c.set(513,942);c.get(302);c.set(47,945);c.get(310);c.set(954,2967);c.set(621,2196);c.get(1261);c.set(1320,1764);c.get(1166);c.set(1374,1862);c.set(72,1570);c.get(325);c.set(1106,870);c.set(350,4);c.set(100,376);c.set(166,498);c.get(1389);c.set(1292,1531);c.set(4,1994);c.set(1301,1787);c.set(1345,2416);c.set(1196,1926);c.get(542);c.set(712,2008);c.set(885,1176);c.set(392,834);c.set(1351,2975);c.set(1175,142);c.set(857,470);c.get(833);c.get(1190);c.set(929,246);c.get(15);c.set(1427,2165);c.set(1014,2220);c.get(1034);c.set(1393,866);c.set(194,2754);c.get(245);c.set(86,574);c.get(1364);c.get(194);c.set(1366,3163);c.get(598);c.set(1359,1644);c.get(958);c.set(380,1609);c.set(1283,2715);c.set(585,3023);c.get(1034);c.get(105);c.get(818);c.get(161);c.get(140);c.set(1167,1432);c.set(903,2078);c.get(1244);c.set(430,202);c.get(1164);c.get(1218);c.get(980);c.get(730);c.get(847);c.set(1339,681);c.set(117,3173);c.set(554,2456);c.set(1155,1385);c.get(993);c.get(603);c.set(581,396);c.set(409,2655);c.set(386,2574);c.set(233,802);c.get(823);c.set(21,267);c.set(1230,1614);c.set(299,959);c.set(267,2986);c.set(1122,1364);c.get(806);c.get(1099);c.set(434,3255);c.get(1116);c.set(793,889);c.set(35,2691);c.set(1296,1158);c.set(1206,2908);c.set(60,1481);c.set(704,3061);c.set(1431,2259);c.get(597);c.set(958,1019);c.get(352);c.set(1207,3169);c.get(282);c.set(846,2609);c.set(156,1207);c.set(1414,3219);c.get(312);c.set(59,1004);c.set(500,570);c.set(117,1749);c.set(873,230);c.get(911);c.set(860,3133);c.set(189,1444);c.get(689);c.get(963);c.get(1043);c.set(679,286);c.set(696,1897);c.set(1034,1216);c.set(1166,3130);c.set(914,2006);c.get(836);c.get(828);c.get(1278);c.set(434,673);c.get(827);c.set(297,1276);c.set(643,1018);c.set(1167,978);c.get(666);c.set(524,3083);c.set(1425,9);c.set(412,2286);c.get(959);c.set(1377,374);c.set(306,1559);c.set(1158,1332);c.set(150,1939);c.get(1137);c.get(39);c.set(1271,3275);c.set(595,2528);c.set(28,792);c.get(1312);c.set(1366,1029);c.get(1410);c.get(904);c.set(968,2435);c.get(513);c.get(644);c.set(277,2354);c.get(1078);c.get(309);c.get(234);c.set(120,637);c.get(634);c.set(438,2452);c.set(796,670);c.get(567);c.set(763,1984);c.set(850,767);c.set(1166,260);c.set(119,435);c.set(919,3090);c.set(13,2032);c.set(804,2602);c.set(954,2762);c.set(298,2728);c.set(370,1184);c.set(976,378);c.set(1424,58);c.set(1100,3163);c.get(452);c.set(19,1153);c.set(86,2010);c.set(807,2928);c.set(679,2643);c.set(1417,1740);c.get(1218);c.set(1050,949);c.set(916,429);c.set(410,983);c.get(1151);c.get(1234);c.set(226,1320);c.set(874,49);c.set(745,1311);c.set(1370,201);c.get(1404);c.get(348);c.get(820);c.set(1149,30);c.get(1149);c.get(1244);c.set(289,635);c.set(535,1766);c.get(408);c.set(393,583);c.set(191,1146);c.set(563,2037);c.set(396,926);c.set(410,417);c.set(1277,172);c.set(1023,249);c.get(2);c.set(961,3089);c.set(219,1568);c.get(1180);c.set(1385,39);c.set(1313,297);c.set(142,2108);c.set(1291,2565);c.set(122,223);c.get(564);c.set(706,2421);c.get(745);c.set(434,1538);c.set(590,5);c.set(1067,1168);c.get(1268);c.get(1083);c.get(667);c.set(495,1559);c.set(648,1546);c.set(1022,1630);c.set(1081,1377);c.set(636,975);c.set(933,2242);c.get(1312);c.set(335,589);c.set(88,167);c.get(1336);c.get(26);c.set(705,706);c.get(871);c.set(1327,328);c.set(958,1783);c.set(477,179);c.set(1293,2585);c.get(581);c.set(672,1614);c.get(874);c.set(162,1905);c.get(709);c.get(746);c.set(145,1180);c.set(1079,2084);c.get(802);c.set(1377,668);c.set(1148,2090);c.set(1161,1402);c.get(781);c.set(768,2229);c.get(224);c.get(799);c.get(432);c.get(782);c.set(229,1311);c.get(1269);c.set(475,807);c.set(1346,1243);c.set(1249,1644);c.set(572,2481);c.get(328);c.get(567);c.get(1270);c.get(1427);c.get(972);c.set(530,2880);c.get(1327);c.set(939,475);c.set(1359,653);c.set(694,687);c.get(140);c.set(858,1422);c.get(162);c.get(310);c.get(264);c.set(1242,11);c.set(969,440);c.get(572);c.set(669,1902);c.get(59);c.set(129,629);c.get(109);c.get(47);c.set(287,24);c.set(1120,1252);c.get(1169);c.get(991);c.set(447,2695);c.get(369);c.set(1085,690);c.get(585);c.get(447);c.set(399,1463);c.set(738,1473);c.set(950,2144);c.set(1325,749);c.get(1255);c.get(1226);c.set(447,970);c.set(61,459);c.set(882,1682);c.get(1410);c.get(1099);c.set(496,473);c.set(835,2010);c.get(660);c.get(11);c.get(547);c.get(181);c.set(1209,3030);c.get(990);c.set(301,871);c.set(182,2207);c.set(464,2288);c.set(684,2736);c.get(484);c.set(465,1376);c.get(1031);c.set(1087,348);c.get(128);c.get(236);c.set(1057,1409);c.get(1201);c.set(487,635);c.get(1221);c.set(370,2507);c.set(325,2465);c.get(227);c.get(295);c.set(1223,1815);c.get(1165);c.get(1256);c.get(581);c.set(489,2675);c.set(68,2615);c.set(888,579);c.get(683);c.get(955);c.set(1083,2996);c.set(1248,2751);c.get(969);c.get(1111);c.set(361,1431);c.get(736);c.set(825,3160);c.set(86,2176);c.get(214);c.set(960,1769);c.set(1215,1699);c.set(621,2440);c.set(1132,2865);c.get(251);c.get(149);c.set(968,2180);c.get(397);c.get(359);c.set(801,2415);c.set(1370,2514);c.get(194);c.set(1124,77);c.get(769);c.set(459,1846);c.get(1221);c.get(44);c.set(1320,550);c.set(1124,1671);c.get(1352);c.get(1016);c.set(871,3218);c.set(375,1541);c.get(664);c.set(1416,528);c.set(990,963);c.set(1022,2351);c.set(89,2424);c.set(373,736);c.set(889,3044);c.set(594,1462);c.set(720,2870);c.get(880);c.set(444,3010);c.get(433);c.get(399);c.get(574);c.set(685,2891);c.get(827);c.get(430);c.get(1082);c.get(349);c.get(1240);c.set(30,1480);c.set(1028,2470);c.get(384);c.get(859);c.set(1217,2180);c.set(863,3078);c.get(748);c.get(808);c.get(970);c.set(422,703);c.get(732);c.get(1104);c.get(1041);c.set(143,630);c.get(871);c.set(301,3200);c.set(582,387);c.set(29,739);c.get(1075);c.get(5);c.get(547);c.set(994,318);c.set(1308,387);c.get(1247);c.get(309);c.get(174);c.set(1215,661);c.set(1227,640);c.set(579,661);c.get(1221);c.set(2,313);c.get(723);c.set(58,433);c.get(1018);c.get(68);c.get(1389);c.get(683);c.get(974);c.get(865);c.set(1414,1179);c.set(259,1919);c.get(322);c.set(1354,556);c.set(1260,2875);c.get(413);c.get(1112);c.set(705,3239);c.get(543);c.get(815);c.get(203);c.get(859);c.get(917);c.get(1052);c.get(1031);c.set(981,1986);c.set(792,2239);c.get(1354);c.set(788,2895);c.get(498);c.get(1292);c.set(1304,3130);c.get(994);c.get(791);c.get(405);c.get(1288);c.get(1120);c.get(704);c.set(508,2437);c.get(942);c.set(604,2550);c.get(411);c.set(1338,76);c.get(160);c.get(752);c.set(305,1014);c.set(1054,2126);c.get(581);c.set(260,2825);c.set(534,1202);c.get(442);c.set(800,2474);c.get(59);c.get(1357);c.set(1091,1454);c.set(306,132);c.get(82);c.set(384,3210);c.set(1251,1873);c.get(1297);c.get(1117);c.set(986,2996);c.set(713,341);c.set(999,776);c.set(1061,377);c.get(1059);c.set(344,1460);c.set(1118,2345);c.set(962,1434);c.get(537);c.get(1267);c.set(1334,332);c.get(1162);c.set(828,3053);c.set(1251,2211);c.set(1162,824);c.get(1062);c.set(81,398);c.get(444);c.set(805,3186);c.set(667,976);c.set(732,2324);c.get(842);c.get(26);c.get(1375);c.set(654,3223);c.set(767,66);c.get(1314);c.get(510);c.set(1333,2952);c.get(27);c.get(50);c.set(989,1785);c.set(1184,1859);c.get(1390);c.get(643);c.set(860,515);c.get(692);c.get(183);c.set(802,2404);c.get(1333);c.get(939);c.get(973);c.get(1267);c.set(1321,351);c.set(786,1617);c.get(804);c.get(887);c.set(784,1787);c.get(46);c.set(1263,2440);c.set(543,1316);c.get(388);c.set(1213,2032);c.set(1109,2750);c.get(1371);c.get(211);c.set(864,1882);c.set(974,2062);c.set(1035,989);c.set(155,3244);c.get(894);c.get(665);c.get(1039);c.get(998);c.set(1099,2432);c.get(281);c.set(959,1957);c.set(412,1644);c.set(787,543);c.set(1093,3129);c.get(1049);c.get(440);c.get(1262);c.get(49);c.set(294,801);c.get(8);c.set(471,2157);c.get(1185);c.get(1264);c.set(1024,2314);c.set(739,788);c.get(258);c.set(319,1202);c.set(179,1673);c.set(236,2563);c.set(225,1249);c.get(99);c.set(1017,2177);c.get(362);c.get(1015);c.get(297);c.set(564,2038);c.get(842);c.set(743,72);c.get(1265);c.set(90,1663);c.get(1322);c.get(1145);c.set(1135,2348);c.set(1093,1250);c.set(589,526);c.set(1306,3042);c.get(190);c.get(9);c.set(253,2705);c.get(329);c.set(949,2703);c.set(70,1266);c.set(752,1762);c.set(252,339);c.get(1373);c.get(1330);c.get(433);c.get(1034);c.set(390,1127);c.set(862,2182);c.get(505);c.set(461,2349);c.set(633,2014);c.set(74,2359);c.get(1117);c.set(979,1204);c.set(976,1538);c.set(448,1146);c.set(330,1094);c.get(1048);c.set(1270,1740);c.set(891,222);c.set(443,2671);c.get(200);c.get(801);c.set(1174,2663);c.set(313,2934);c.set(716,266);c.set(559,725);c.get(1279);c.set(1203,1645);c.get(1091);c.set(97,2166);c.get(982);c.set(778,1946);c.get(1067);c.get(424);c.set(111,1274);c.set(643,2404);c.set(1372,1632);c.set(1030,589);c.set(1177,2990);c.set(767,542);c.get(1143);c.set(1228,1448);c.set(1112,1672);c.set(838,2188);c.set(1178,1914);c.get(481);c.set(859,349);c.set(11,2051);c.get(1191);c.set(1086,903);c.set(278,3154);c.set(61,3133);c.get(1045);c.get(282);c.set(701,2738);c.set(1342,3112);c.get(328);c.get(1379);c.set(717,2467);c.get(507);c.set(695,1372);c.set(1254,116);c.get(289);c.get(599);c.get(1254);c.set(524,965);c.set(843,1203);c.set(55,2976);c.set(51,1760);c.get(801);c.get(1418);c.set(140,21);c.get(1032);c.get(1418);c.set(1360,130);c.set(1123,170);c.get(1373);c.set(1126,983);c.get(400);c.get(1276);c.get(791);c.get(462);c.set(10,2930);c.set(1251,670);c.get(881);c.get(409);c.set(364,2498);c.set(166,265);c.set(351,2542);c.set(739,722);c.set(1260,3218);c.set(179,854);c.get(229);c.get(642);c.set(651,693);c.get(544);c.set(854,297);c.set(189,1582);c.set(455,847);c.get(1180);c.set(137,775);c.set(1143,1685);c.set(51,3220);c.set(590,1525);c.set(1281,1640);c.set(495,961);c.get(1308);c.get(963);c.set(949,2261);c.get(906);c.get(1414);c.set(360,2302);c.set(1025,2142);c.set(326,920);c.get(225);c.set(90,1013);c.get(883);c.set(1118,666);c.set(1292,46);c.get(416);c.get(816);c.set(1408,1732);c.get(635);c.set(847,1948);c.get(1267);c.set(1361,300);c.get(256);c.set(324,1525);c.get(1147);c.get(299);c.set(282,2018);c.set(779,344);c.set(1322,1691);c.set(23,2017);c.set(1050,1240);c.set(408,2194);c.get(779);c.set(158,3180);c.get(1024);c.get(1127);c.get(600);c.set(1067,3095);c.get(297);c.set(1339,897);c.set(1294,880);c.get(543);c.set(280,949);c.set(515,1464);c.get(126);c.set(410,3012);c.get(305);c.set(140,1035);c.get(886);c.set(412,1146);c.get(610);c.get(672);c.set(1114,1461);c.get(1231);c.set(1101,823);c.get(1050);c.get(248);c.set(658,2214);c.get(1038);c.get(822);c.set(704,1177);c.get(880);c.get(330);c.get(1023);c.set(824,1720);c.get(943);c.get(772);c.set(1130,2362);c.get(1197);c.set(414,3240);c.set(1189,140);c.get(814);c.get(172);c.set(749,3109);c.set(251,1339);c.set(724,242);c.set(841,2214);c.get(1026);c.set(548,3255);c.get(676);c.get(1154);c.get(1286);c.get(146);c.set(841,2807);c.set(53,1743);c.set(351,673);c.set(1316,2769);c.get(1370);c.set(9,2074);c.set(1141,3297);c.get(979);c.set(1173,2268);c.get(370);c.set(601,2331);c.set(1055,133);c.set(1298,3238);c.get(608);c.get(718);c.get(398);c.set(87,813);c.set(1114,2938);c.set(567,2601);c.set(186,137);c.get(1089);c.set(697,394);c.get(18);c.set(1334,1612);c.set(784,1289);c.set(675,2145);c.set(882,2372);c.set(662,1376);c.set(1380,130);c.get(540);c.set(1408,1524);c.set(618,2906);c.get(171);c.set(759,529);c.set(636,1219);c.set(296,2345);c.set(917,2246);c.set(160,738);c.get(883);c.set(1148,1945);c.set(935,2674);c.get(1087);c.set(848,1596);c.set(1259,1651);c.get(254);c.set(961,1902);c.get(168);c.get(95);c.get(771);c.get(103);c.set(88,3228);c.set(667,1064);c.get(1083);c.set(1431,1609);c.get(727);c.set(1241,1789);c.set(974,1467);c.set(617,2695);c.get(139);c.set(190,2105);c.set(939,1485);c.set(898,950);c.get(378);c.set(118,621);c.get(1065);c.get(695);c.get(978);c.set(127,871);c.get(539);c.set(1115,70);c.get(983);c.get(955);c.set(311,2200);c.set(742,668);c.get(176);c.set(1034,974);c.set(1343,124);c.set(786,615);c.get(42);c.set(342,586);c.set(458,2688);c.get(865);c.set(43,1901);c.get(16);c.get(112);c.set(859,1655);c.get(1394);c.get(1031);c.get(1017);c.get(132);c.get(509);c.get(838);c.set(1267,261);c.set(984,616);c.set(1304,1580);c.set(1143,2388);c.set(994,137);c.get(318);c.get(1159);c.set(223,593);c.get(1109);c.get(1400);c.get(511);c.set(803,3062);c.set(140,590);c.set(479,2487);c.set(581,1191);c.get(1083);c.set(149,543);c.set(876,2648);c.get(1396);c.set(190,1552);c.set(1404,1037);c.get(73);c.set(1344,2123);c.set(1174,1051);c.set(388,73);c.set(1391,1175);c.get(406);c.set(569,2994);c.get(1325);c.get(489);c.set(664,2283);c.set(1141,363);c.get(1118);c.set(432,141);c.set(1278,2200);c.set(1295,498);c.get(95);c.set(113,2432);c.set(24,939);c.set(600,2069);c.set(36,2092);c.set(1222,635);c.get(533);c.get(1156);c.set(503,1775);c.set(165,2727);c.get(1429);c.set(1291,942);c.get(1363);c.set(869,3288);c.get(188);c.get(1207);c.set(381,389);c.get(290);c.get(1075);c.set(1000,834);c.get(1018);c.get(741);c.set(894,3205);c.set(568,2699);c.set(201,288);c.set(777,3189);c.set(1265,1187);c.set(613,187);c.get(199);c.set(407,1757);c.get(29);c.get(1077);c.set(439,2502);c.set(378,2930);c.set(1164,577);c.get(24);c.get(1107);c.set(162,426);c.set(1415,1492);c.set(576,2743);c.get(118);c.set(227,1608);c.set(1258,3053);c.get(1211);c.get(284);c.set(827,2780);c.get(768);c.get(1333);c.set(349,490);c.set(1402,2849);c.set(1321,2965);c.set(43,2516);c.set(334,639);c.get(485);c.get(677);c.get(594);c.get(402);c.set(242,3033);c.set(1186,2947);c.set(368,340);c.get(802);c.get(5);c.get(79);c.set(1059,891);c.set(1286,1252);c.set(248,660);c.get(545);c.set(1202,1682);c.set(444,1771);c.set(399,1429);c.get(59);c.set(240,2457);c.set(155,1920);c.set(879,1696);c.get(236);c.get(59);c.set(1296,1622);c.set(453,3239);c.set(879,145);c.set(668,1775);c.set(498,107);c.get(506);c.get(620);c.set(1040,2055);c.set(1117,2738);c.set(706,612);c.get(379);c.set(1277,3181);c.set(471,1772);c.set(878,642);c.get(1270);c.set(605,1501);c.set(618,3007);c.set(659,1473);c.set(1194,851);c.get(1205);c.set(299,1033);c.set(710,2984);c.set(734,1730);c.get(1377);c.set(693,3223);c.set(713,1623);c.set(756,2957);c.set(1065,1195);c.get(1205);c.get(62);c.set(456,1941);c.set(473,302);c.set(238,1159);c.get(178);c.get(1068);c.set(357,2985);c.set(439,2681);c.set(926,3222);c.get(1228);c.get(734);c.set(1041,3219);c.set(981,2556);c.set(675,200);c.set(104,2559);c.set(597,1590);c.set(167,2918);c.get(883);c.get(489);c.get(492);c.set(289,221);c.set(224,2075);c.set(1215,278);c.get(197);c.get(17);c.get(491);c.get(281);c.set(1074,673);c.set(1280,3284);c.get(749);c.get(391);c.get(38);c.set(206,2215);c.get(20);c.set(1212,2816);c.set(167,2829);c.set(1190,259);c.get(560);c.get(216);c.get(637);c.get(1102);c.set(1,1661);c.get(359);c.get(319);c.set(842,2667);c.set(981,1204);c.set(938,417);c.set(352,2795);c.set(1030,969);c.get(401);c.get(1263);c.get(595);c.get(538);c.get(941);c.get(580);c.set(501,1552);c.set(909,2193);c.set(1012,550);c.set(1077,895);c.set(642,2615);c.set(90,2610);c.set(554,2686);c.get(1336);c.set(968,2692);c.set(208,1748);c.set(216,768);c.get(582);c.set(1145,390);c.set(1020,1783);c.set(222,2002);c.get(105);c.set(1365,2665);c.get(647);c.set(806,83);c.set(1027,209);c.set(859,683);c.set(598,1394);c.set(929,1324);c.get(1361);c.set(826,1072);c.set(750,3066);c.get(1344);c.set(1172,1021);c.set(734,235);c.set(410,3235);c.set(1248,1121);c.get(238);c.get(694);c.get(679);c.get(2);c.set(1130,1249);c.get(507);c.get(1191);c.set(636,2804);c.set(711,1143);c.set(927,3051);c.get(408);c.set(1306,580);c.set(251,900);c.set(404,668);c.set(114,787);c.set(403,2151);c.set(1035,286);c.get(996);c.get(738);c.get(882);c.get(283);c.set(490,2684);c.set(1197,1460);c.set(435,1086);c.set(385,2552);c.get(748);c.set(1304,3179);c.get(466);c.set(808,1410);c.set(858,2991);c.set(222,1415);c.set(1120,2765);c.set(1050,58);c.get(1404);c.get(1200);c.set(846,268);c.get(768);c.set(1244,2103);c.set(391,711);c.set(514,2485);c.get(1249);c.set(209,1579);c.get(231);c.set(852,1685);c.get(932);c.get(1056);c.set(1419,2334);c.get(563);c.get(150);c.set(983,1325);c.set(151,1386);c.get(1292);c.set(562,2457);c.get(619);c.get(1160);c.set(1329,227);c.get(1212);c.set(333,3015);c.get(861);c.set(149,2315);c.get(39);c.get(1006);c.set(42,1732);c.get(338);c.set(1039,2491);c.set(419,767);c.set(1411,3193);c.get(824);c.set(712,2851);c.set(651,900);c.get(678);c.set(1005,382);c.get(1009);c.set(84,224);c.set(52,1507);c.set(1118,597);c.set(826,1144);c.get(1066);c.set(731,3057);c.set(1175,2357);c.get(684);c.set(679,1021);c.set(1415,2210);c.get(1188);c.set(729,2370);c.set(931,162);c.set(987,1536);c.set(105,396);c.get(1409);c.get(372);c.set(317,3006);c.get(594);c.get(139);c.get(648);c.set(986,1580);c.set(638,2529);c.get(1203);c.set(948,327);c.set(223,565);c.get(1314);c.get(29);c.set(795,1574);c.set(727,3296);c.set(751,100);c.get(863);c.get(1308);c.set(667,2686);c.set(715,2254);c.set(226,1458);c.set(19,2132);c.set(902,2063);c.set(1364,658);c.get(647);c.set(1306,2715);c.get(1362);c.set(602,198);c.get(1047);c.set(83,799);c.get(444);c.set(1105,1859);c.get(239);c.set(1312,20);c.get(1166);c.set(1212,965);c.get(1219);c.set(32,942);c.get(729);c.set(352,2107);c.set(261,1750);c.get(633);c.get(843);c.get(176);c.get(780);c.get(1308);c.get(1142);c.set(1029,357);c.set(357,663);c.set(582,1602);c.get(911);c.set(260,2695);c.get(671);c.get(512);c.set(622,3073);c.set(1236,1550);c.get(991);c.get(390);c.get(444);c.get(1397);c.set(423,300);c.set(1101,2504);c.set(1207,2916);c.get(896);c.get(360);c.set(586,173);c.set(363,458);c.set(349,1867);c.get(1358);c.set(1296,2781);c.get(348);c.set(567,188);c.get(1431);c.set(475,1899);c.set(717,1740);c.set(540,43);c.set(1042,1527);c.set(1073,441);c.get(785);c.get(1317);c.set(79,2766);c.set(672,986);c.get(313);c.set(735,2958);c.set(1417,351);c.get(1012);c.get(251);c.get(343);c.set(749,1339);c.set(108,580);c.get(312);c.get(445);c.set(1185,503);c.get(82);c.set(429,731);c.set(1206,785);c.set(324,794);c.get(1231);c.get(107);c.set(499,2734);c.get(929);c.get(1338);c.set(762,504);c.get(379);c.set(35,1601);c.set(235,1626);c.set(1403,1704);c.set(1161,2343);c.set(545,1073);c.set(1422,906);c.set(1075,2342);c.get(40);c.set(141,2835);c.get(295);c.set(492,96);c.set(794,3110);c.get(1185);c.get(576);c.get(429);c.set(186,1474);c.get(1031);c.set(1068,862);c.set(563,1553);c.set(65,1614);c.set(1164,3004);c.set(354,2775);c.get(263);c.get(641);c.get(180);c.set(523,2027);c.set(1160,2767);c.get(312);c.set(86,340);c.set(751,146);c.get(110);c.set(421,1784);c.set(408,2636);c.set(227,2736);c.set(813,38);c.set(1043,3102);c.set(1062,1775);c.set(1225,2815);c.set(1192,1896);c.set(954,1862);c.set(550,1830);c.set(628,2164);c.get(1419);c.set(875,2261);c.set(792,407);c.set(753,2565);c.set(942,1365);c.get(1103);c.get(963);c.set(1115,2934);c.get(814);c.set(904,269);c.get(1268);c.get(758);c.get(220);c.set(357,1604);c.get(275);c.set(969,2947);c.set(408,2969);c.set(499,1989);c.set(1168,314);c.get(1203);c.set(260,2524);c.set(754,788);c.set(1186,1442);c.get(1209);c.set(860,1289);c.set(277,2594);c.set(727,146);c.get(1349);c.set(916,2305);c.set(836,3130);c.get(198);c.get(1112);c.set(1118,2385);c.set(19,2763);c.set(791,1315);c.get(497);c.get(1336);c.get(851);c.get(213);c.get(802);c.set(139,912);c.get(1340);c.set(1221,2418);c.set(197,2282);c.get(194);c.set(1406,3139);c.set(1011,1225);c.set(206,1202);c.set(1362,1605);c.get(294);c.get(430);c.get(1370);c.set(408,3219);c.get(1157);c.get(412);c.set(221,3036);c.set(116,2827);c.get(26);c.set(414,2574);c.get(252);c.get(9);c.set(846,2216);c.get(862);c.set(383,1094);c.get(1375);c.get(1345);c.set(700,1547);c.set(126,2948);c.get(216);c.get(65);c.get(1320);c.get(1123);c.set(663,1529);c.set(308,848);c.get(501);c.set(1238,864);c.set(1165,1008);c.get(1292);c.get(488);c.get(1394);c.get(63);c.set(254,424);c.set(637,1583);c.set(920,2069);c.set(1230,1574);c.set(314,1624);c.set(1155,2309);c.set(1111,2626);c.set(1156,2467);c.set(1097,1352);c.set(996,1194);c.set(1220,1941);c.set(1274,1803);c.set(230,348);c.get(816);c.set(165,3276);c.get(715);c.set(1256,1439);c.get(327);c.set(548,428);c.set(874,3050);c.get(848);c.set(270,2739);c.get(1207);c.set(670,3199);c.set(902,2023);c.set(414,1897);c.get(885);c.get(1198);c.set(806,2989);c.set(52,3169);c.get(80);c.set(689,1660);c.get(1180);c.set(1139,3300);c.set(356,223);c.get(12);c.set(1344,2856);c.set(410,561);c.get(187);c.get(1301);c.set(617,131);c.get(168);c.set(12,2915);c.set(179,521);c.set(1152,869);c.get(500);c.get(674);c.set(990,957);c.set(1276,2483);c.set(406,2985);c.set(1062,3078);c.get(189);c.get(745);c.get(645);c.set(182,1154);c.get(479);c.set(1419,1479);c.get(101);c.set(465,1878);c.set(1187,3096);c.set(47,361);c.get(299);c.get(853);c.set(1186,441);c.set(805,600);c.set(172,1885);c.set(1373,1394);c.get(921);c.set(797,3030);c.get(627);c.set(531,2429);c.get(934);c.get(1121);c.get(584);c.set(368,542);c.set(841,3284);c.get(652);c.get(266);c.set(60,2623);c.get(428);c.set(899,2510);c.set(431,3017);c.set(1046,2806);c.get(836);c.get(751);c.get(1111);c.get(744);c.set(932,1108);c.set(1320,1982);c.set(453,679);c.set(1351,2627);c.set(619,1370);c.set(820,1582);c.set(677,1510);c.set(1159,1921);c.get(1315);c.set(1348,2248);c.get(342);c.set(414,379);c.get(890);c.set(846,2661);c.set(1191,3075);c.get(1196);c.get(364);c.get(699);c.set(251,1300);c.get(737);c.set(345,43);c.get(1032);c.set(200,2661);c.get(215);c.set(1357,2212);c.get(435);c.set(1111,1681);c.set(828,155);c.set(685,1585);c.get(1029);c.get(386);c.set(761,199);c.set(545,2889);c.set(1418,2488);c.get(607);c.get(1071);c.set(561,1733);c.get(220);c.set(1381,2079);c.set(382,771);c.get(531);c.set(548,663);c.get(266);c.set(103,1875);c.get(330);c.set(814,2080);c.get(592);c.set(159,1683);c.get(1426);c.get(402);c.get(1409);c.set(181,1657);c.set(856,720);c.get(170);c.get(1325);c.get(647);c.set(291,2289);c.set(559,100);c.get(1227);c.set(1060,383);c.get(531);c.get(758);c.get(79);c.get(966);c.set(1159,2077);c.set(2,842);c.set(1349,2461);c.get(1207);c.get(388);c.get(1042);c.set(478,218);c.set(1205,2756);c.set(1086,934);c.set(1019,538);c.get(87);c.get(1336);c.set(947,516);c.set(1336,2027);c.set(820,2271);c.set(351,2330);c.set(980,592);c.get(590);c.set(306,2332);c.get(305);c.set(1085,2881);c.get(1232);c.get(904);c.get(1118);c.get(215);c.set(1227,1960);c.get(1315);c.set(1338,2553);c.set(1128,2466);c.get(17);c.get(706);c.set(920,2500);c.set(433,1900);c.set(1066,811);c.get(596);c.set(737,1119);c.set(1004,1715);c.get(253);c.get(866);c.get(483);c.get(1292);c.get(307);c.get(12);c.set(203,2801);c.get(1301);c.set(1361,1588);c.set(1150,749);c.get(1254);c.set(1162,703);c.set(308,2850);c.get(1278);c.set(1405,3088);c.get(1383);c.set(1177,3263);c.set(900,1906);c.set(939,269);c.get(716);c.set(1172,2913);c.set(1057,1875);c.set(34,444);c.get(530);c.set(1002,1222);c.get(747);c.get(575);c.get(470);c.set(356,2513);c.set(1183,2239);c.set(1347,1301);c.get(1326);c.get(1172);c.get(85);c.set(977,1021);c.get(312);c.get(260);c.set(465,2718);c.set(1190,728);c.get(160);c.set(773,2570);c.set(1201,1201);c.get(1408);c.set(228,2447);c.get(544);c.get(1318);c.get(1340);c.get(854);c.get(1137);c.set(829,2848);c.set(925,2367);c.set(23,932);c.get(1094);c.get(1098);c.set(977,2758);c.set(389,1427);c.get(610);c.get(862);c.set(1114,1979);c.set(196,3082);c.set(567,1144);c.set(855,2438);c.set(1158,1565);c.set(1153,2692);c.get(385);c.set(885,1964);c.get(936);c.get(423);c.set(138,1139);c.get(1288);c.set(745,1307);c.get(524);c.get(750);c.get(910);c.get(1048);c.set(125,2342);c.get(268);c.set(946,1364);c.get(628);c.set(853,137);c.set(1170,1682);c.get(487);c.set(1144,915);c.get(685);c.get(1039);c.set(1240,2489);c.set(1326,758);c.set(307,3019);c.get(1215);c.get(489);c.set(426,107);c.get(1020);c.set(950,2629);c.get(1208);c.set(1077,129);c.set(1275,1598);c.get(655);c.set(60,2425);c.set(1016,1041);c.set(201,2143);c.set(1010,239);c.get(608);c.get(705);c.get(37);c.set(595,1693);c.set(1232,659);c.get(915);c.set(1263,884);c.set(769,1439);c.get(812);c.get(885);c.set(1118,2746);c.set(839,33);c.set(298,1031);c.set(641,2113);c.set(1298,2587);c.get(603);c.get(352);c.get(206);c.set(979,1477);c.set(819,2711);c.set(47,705);c.get(562);c.get(1260);c.get(543);c.set(1400,3160);c.get(379);c.set(993,661);c.get(314);c.set(986,381);c.set(171,1627);c.get(457);c.set(1176,1445);c.get(380);c.get(1216);c.set(685,2074);c.set(162,1793);c.set(1416,3229);c.set(1211,2552);c.get(190);c.set(620,1195);c.get(1255);c.set(1041,415);c.get(173);c.get(927);c.get(603);c.get(1130);c.get(733);c.set(708,2723);c.set(1292,2878);c.set(737,1187);c.get(918);c.set(1256,205);c.get(743);c.set(166,864);c.get(1321);c.get(1343);c.get(155);c.set(861,2468);c.set(347,315);c.get(401);c.get(497);c.set(926,1870);c.set(804,1933);c.set(339,929);c.get(1069);c.set(1293,959);c.get(1044);c.get(220);c.set(803,1587);c.get(627);c.set(1234,2663);c.set(1033,1162);c.get(533);c.get(730);c.get(178);c.get(472);c.get(204);c.get(619);c.set(401,2754);c.get(532);c.set(387,794);c.set(770,1112);c.set(766,2743);c.get(188);c.get(110);c.set(119,217);c.set(295,1145);c.set(25,2618);c.get(561);c.set(981,2039);c.set(406,2170);c.set(524,3139);c.set(852,994);c.set(909,1571);c.get(1365);c.get(908);c.get(527);c.get(1110);c.set(203,2073);c.set(273,1156);c.get(167);c.get(256);c.set(630,51);c.set(1047,484);c.set(922,3195);c.get(486);c.set(615,2140);c.set(581,516);c.set(867,2654);c.set(295,700);c.set(338,2982);c.get(684);c.set(947,2083);c.set(1307,1809);c.get(1348);c.get(1258);c.set(53,1538);c.get(1181);c.set(163,1459);c.set(1345,198);c.get(239);c.set(886,1283);c.set(639,1551);c.set(1349,1138);c.get(1310);c.set(296,1684);c.get(850);c.get(28);c.get(575);c.set(1422,1362);c.get(173);c.get(1168);c.get(1046);c.get(986);c.get(67);c.get(1072);c.get(192);c.set(376,1740);c.set(467,1879);c.get(1186);c.set(867,1971);c.set(751,2170);c.get(612);c.set(1225,518);c.set(1274,2771);c.set(1077,371);c.set(494,2538);c.get(524);c.set(561,2364);c.get(1367);c.set(605,846);c.set(722,1280);c.get(1334);c.get(653);c.get(1112);c.set(2,1193);c.get(258);c.set(1166,3051);c.set(1299,2152);c.get(1070);c.get(1068);c.get(1119);c.get(1274);c.get(328);c.set(865,2837);c.get(237);c.get(1325);c.set(1098,2781);c.set(980,2261);c.set(1186,814);c.get(752);c.set(218,1140);c.set(1372,1592);c.get(412);c.set(637,2768);c.set(381,724);c.get(1323);c.get(1096);c.set(440,2451);c.set(532,948);c.set(194,1164);c.get(1229);c.get(1341);c.get(181);c.get(1234);c.get(1136);c.set(101,3071);c.set(1307,2573);c.get(259);c.set(957,581);c.set(358,2128);c.get(1341);c.set(937,3000);c.get(941);c.get(474);c.get(622);c.get(1028);c.get(55);c.get(495);c.get(571);c.set(751,2066);c.get(333);c.set(1264,44);c.set(195,1681);c.get(1205);c.set(518,1203);c.set(254,916);c.get(222);c.get(351);c.set(1355,3190);c.set(1423,1188);c.set(241,2122);c.get(461);c.get(907);c.set(257,3289);c.get(156);c.set(22,1685);c.get(908);c.get(1186);c.get(479);c.set(490,822);c.set(990,3085);c.set(828,2416);c.set(1336,974);c.set(1233,2679);c.get(684);c.set(1371,2781);c.set(673,1909);c.get(241);c.set(351,68);c.set(558,2878);c.set(330,3165);c.set(591,823);c.get(519);c.get(1300);c.set(21,1696);c.set(199,486);c.get(1080);c.get(1202);c.get(1362);c.set(465,2734);c.set(602,2421);c.set(270,2390);c.set(870,1387);c.set(1307,1028);c.set(487,860);c.get(621);c.get(399);c.set(361,537);c.set(154,1812);c.get(1185);c.set(150,2115);c.get(965);c.get(1038);c.set(1145,2512);c.get(1180);c.get(151);c.get(26);c.set(1400,893);c.get(1235);c.set(1270,1722);c.set(472,1812);c.get(1070);c.get(827);c.get(1158);c.set(1089,2962);c.get(808);c.set(493,1179);c.set(295,2965);c.get(659);c.set(190,2038);c.get(1284);c.get(511);c.set(258,904);c.get(783);c.set(1149,498);c.get(968);c.set(664,1660);c.get(715);c.get(15);c.get(556);c.set(112,1418);c.get(531);c.set(228,147);c.set(184,2534);c.set(686,2943);c.get(1316);c.set(768,2069);c.set(588,3260);c.get(291);c.set(171,2742);c.set(415,1616);c.set(81,1308);c.set(1304,2798);c.set(1053,2835);c.get(1159);c.get(734);c.set(635,1892);c.get(131);c.set(62,916);c.set(173,1377);c.get(24);c.set(1009,335);c.get(1397);c.get(1250);c.set(869,912);c.set(969,1307);c.get(1092);c.set(1273,296);c.get(194);c.set(300,1463);c.set(765,1505);c.set(1405,2833);c.get(979);c.get(1193);c.get(710);c.set(678,1553);c.get(865);c.set(455,1203);c.set(617,1292);c.get(455);c.get(893);c.set(672,2898);c.set(692,2859);c.set(1170,2114);c.set(610,180);c.set(792,999);c.get(1378);c.set(770,1138);c.set(961,1083);c.get(663);c.get(1148);c.get(1204);c.get(291);c.set(1360,1750);c.set(837,88);c.set(1363,465);c.set(592,2383);c.get(464);c.set(34,2157);c.get(849);c.get(472);c.set(421,399);c.set(944,2067);c.set(802,2252);c.get(666);c.set(442,3246);c.set(805,1506);c.get(205);c.set(608,1477);c.get(154);c.get(1294);c.set(370,843);c.set(913,1918);c.get(866);c.set(19,2778);c.set(1234,3131);c.set(1211,2152);c.set(1201,570);c.set(1414,214);c.get(346);c.set(119,654);c.get(572);c.set(641,1568);c.set(1336,1951);c.get(234);c.get(347);c.set(551,2471);c.get(727);c.get(752);c.get(1404);c.set(287,813);c.get(1015);c.get(374);c.set(127,2326);c.set(1224,3115);c.set(208,2222);c.set(1134,3207);c.get(999);c.set(1233,1773);c.set(1277,1087);c.get(756);c.set(1064,2220);c.set(1289,22);c.get(1090);c.set(977,2318);c.get(480);c.set(1339,59);c.set(390,1816);c.set(335,2002);c.get(1214);c.set(988,1908);c.get(1091);c.get(127);c.get(145);c.get(1169);c.get(646);c.set(168,456);c.set(963,2482);c.get(1359);c.set(1052,279);c.set(720,2492);c.set(1288,2114);c.set(1306,613);c.set(1154,908);c.get(1388);c.get(771);c.set(853,1888);c.set(1105,110);c.set(1333,2552);c.get(379);c.set(1349,1385);c.get(550);c.set(811,3103);c.set(87,3022);c.set(960,2919);c.set(801,566);c.set(32,1242);c.set(1075,974);c.set(1239,1505);c.set(1363,1657);c.get(559);c.set(439,2172);c.get(341);c.set(432,1952);c.set(906,251);c.set(917,1905);c.get(545);c.set(1350,522);c.set(1073,885);c.get(1070);c.set(337,2124);c.set(1130,1415);c.set(977,731);c.set(969,260);c.get(70);c.get(1323);c.get(787);c.set(1382,1213);c.set(346,946);c.set(1403,2338);c.set(753,1252);c.set(1084,2509);c.get(1106);c.get(524);c.set(381,1484);c.set(677,810);c.get(1374);c.set(787,2550);c.set(138,2445);c.set(451,1315);c.get(102);c.set(727,2928);c.get(345);c.get(892);c.set(46,1093);c.get(998);c.get(731);c.set(608,1130);c.get(550);c.set(1283,1477);c.set(783,825);c.get(1144);c.get(197);c.set(638,90);c.set(1034,1218);c.set(1031,1541);c.get(1011);c.get(164);c.set(365,1502);c.set(143,1186);c.set(671,2345);c.set(1337,239);c.get(1271);c.set(1329,133);c.get(1009);c.set(1236,366);c.get(674);c.set(114,2226);c.get(35);c.get(929);c.set(457,351);c.get(81);c.set(1367,2232);c.set(1188,425);c.set(1345,147);c.set(202,2772);c.get(383);c.get(977);c.get(1255);c.set(1093,1705);c.get(780);c.set(1090,1747);c.set(556,2559);c.set(1069,2243);c.get(1011);c.set(1206,498);c.get(264);c.get(1037);c.get(722);c.get(1102);c.set(555,1377);c.set(671,1994);c.set(430,3042);c.set(1003,1073);c.set(885,1190);c.set(1333,1847);c.set(652,801);c.get(1379);c.set(624,301);c.get(628);c.set(1371,379);c.get(1009);c.set(478,1658);c.get(808);c.get(668);c.set(1081,2237);c.set(1418,727);c.get(160);c.get(635);c.set(1313,2054);c.get(603);c.set(426,1539);c.get(556);c.set(1165,1540);c.set(865,636);c.set(734,928);c.set(810,572);c.set(1083,2462);c.set(1358,2079);c.set(2,236);c.set(1110,862);c.get(293);c.set(1117,2087);c.set(27,2680);c.set(342,465);c.get(880);c.set(1427,3233);c.set(196,891);c.set(854,623);c.get(1413);c.set(437,2488);c.set(245,2953);c.get(171);c.get(1182);c.get(451);c.get(923);c.set(992,1059);c.set(213,3006);c.set(283,772);c.get(235);c.get(744);c.set(33,417);c.set(1350,13);c.set(1164,2146);c.set(646,1493);c.get(1348);c.set(990,568);c.get(479);c.get(1009);c.set(1351,1341);c.set(27,1472);c.set(389,2394);c.get(1384);c.set(981,2978);c.set(311,2523);c.set(1046,3273);c.set(1285,1054);c.get(449);c.set(906,365);c.set(911,2027);c.get(548);c.set(1051,435);c.get(1038);c.set(1065,1971);c.set(1008,2792);c.set(171,2727);c.set(862,2680);c.set(519,822);c.set(779,717);c.get(110);c.set(488,1598);c.set(882,1272);c.set(1282,875);c.set(696,828);c.set(1178,2078);c.set(1110,776);c.set(1364,1092);c.get(298);c.get(579);c.set(933,360);c.set(552,599);c.set(522,82);c.set(540,774);c.get(259);c.get(716);c.set(225,107);c.set(567,1245);c.get(112);c.set(685,2917);c.get(79);c.get(618);c.get(2);c.set(380,836);c.set(16,2365);c.set(307,846);c.set(978,1872);c.set(145,557);c.set(1207,2796);c.set(961,2362);c.set(331,162);c.set(1377,2426);c.get(167);c.set(532,3137);c.set(1209,1491);c.get(583);c.get(1373);c.set(333,3192);c.set(168,66);c.set(757,2170);c.set(302,3262);c.get(591);c.get(350);c.get(1027);c.set(1012,1704);c.set(1114,1663);c.set(949,1570);c.get(1405);c.get(1105);c.set(786,2206);c.get(487);c.set(840,277);c.set(539,1029);c.get(344);c.set(150,1440);c.get(1010);c.get(664);c.get(31);c.get(534);c.set(706,1875);c.set(1257,2090);c.get(679);c.set(54,2164);c.get(1027);c.set(1148,3257);c.get(1062);c.get(1212);c.set(589,1266);c.set(414,1272);c.get(8);c.set(405,422);c.set(1172,597);c.get(939);c.set(631,472);c.set(570,2320);c.get(986);c.set(946,1625);c.get(2);c.set(1059,2740);c.get(673);c.set(242,2664);c.set(829,2607);c.set(522,2707);c.set(1305,3238);c.set(852,2238);c.set(656,3220);c.set(1396,1391);c.set(275,2655);c.get(169);c.set(72,1443);c.get(994);c.get(414);c.get(287);c.set(886,2877);c.get(1385);c.get(457);c.get(459);c.get(49);c.set(1401,754);c.get(561);c.get(832);c.get(296);c.set(1346,1225);c.set(1251,1628);c.set(1094,1446);c.get(216);c.get(122);c.get(231);c.get(437);c.get(959);c.get(1294);c.set(1355,2771);c.set(1319,1169);c.set(700,702);c.get(1062);c.get(528);c.set(1109,1889);c.get(899);c.get(493);c.get(1060);c.get(497);c.set(487,2098);c.set(713,1000);c.get(700);c.set(594,816);c.set(462,1715);c.get(287);c.set(385,2147);c.get(1287);c.get(725);c.get(1020);c.set(558,2847);c.set(1099,727);c.set(1336,2107);c.get(643);c.set(208,3224);c.get(489);c.get(124);c.set(145,2293);c.get(669);c.set(258,702);c.get(1013);c.get(441);c.get(317);c.get(585);c.set(1300,2548);c.get(1125);c.get(1116);c.get(1137);c.get(63);c.get(117);c.get(1073);c.set(545,479);c.set(1371,484);c.set(466,3225);c.set(1307,455);c.get(661);c.set(975,3234);c.get(212);c.set(1317,588);c.set(658,229);c.get(575);c.get(1353);c.set(892,2499);c.get(309);c.set(1315,2197);c.get(1371);c.get(461);c.get(148);c.set(847,1946);c.set(873,1237);c.set(297,174);c.get(177);c.set(62,1802);c.get(1137);c.set(1044,1274);c.set(1149,669);c.get(1430);c.get(708);c.set(373,2294);c.get(690);c.get(638);c.set(284,3021);c.get(789);c.set(1363,1678);c.get(797);c.set(731,1420);c.get(660);c.set(1201,183);c.set(1210,1461);c.set(760,1894);c.get(1130);c.set(1017,960);c.get(1159);c.get(1239);c.get(620);c.set(954,782);c.set(712,416);c.set(518,1050);c.set(301,280);c.set(537,2907);c.get(1286);c.get(1232);c.get(420);c.set(1342,1147);c.set(614,218);c.get(709);c.get(929);c.set(216,1852);c.set(1319,21);c.get(955);c.set(698,1480);c.get(552);c.get(623);c.set(1426,2823);c.set(247,809);c.get(14);c.get(1025);c.get(1267);c.set(1222,2588);c.set(535,2074);c.get(173);c.set(722,2601);c.get(319);c.get(465);c.get(889);c.get(1199);c.set(1323,2642);c.get(1250);c.get(79);c.set(1352,552);c.get(149);c.set(1392,2529);c.set(351,358);c.get(1403);c.set(1060,1241);c.get(157);c.get(1344);c.get(1431);c.get(387);c.get(991);c.set(267,1535);c.get(1298);c.get(1140);c.get(1239);c.get(23);c.set(1186,699);c.set(1372,733);c.set(1194,426);c.get(766);c.set(1083,2318);c.set(1152,917);c.set(10,821);c.set(830,557);c.set(827,533);c.get(959);c.get(1027);c.set(558,2996);c.get(422);c.get(862);c.get(1042);c.set(208,3175);c.get(96);c.get(914);c.get(55);c.set(541,338);c.get(1305);c.get(672);c.get(763);c.get(178);c.set(136,1717);c.set(1118,40);c.set(1332,2790);c.set(678,33);c.get(1168);c.get(425);c.get(571);c.get(622);c.get(680);c.set(324,2816);c.get(141);c.set(730,2829);c.get(990);c.get(559);c.get(1232);c.get(909);c.set(643,2481);c.get(409);c.set(630,1656);c.set(282,1834);c.set(761,1920);c.set(546,2852);c.get(291);c.get(1062);c.set(327,1419);c.get(248);c.get(618);c.set(961,2146);c.get(1029);c.set(2,242);c.get(1313);c.set(1052,574);c.get(1141);c.set(985,1452);c.get(482);c.set(1126,926);c.set(663,356);c.get(899);c.set(10,2233);c.get(571);c.get(737);c.set(413,1170);c.set(979,2880);c.get(1020);c.set(179,1845);c.get(1349);c.set(685,2597);c.get(941);c.get(461);c.get(1227);c.set(125,3259);c.get(1305);c.get(1341);c.get(401);c.set(1100,1318);c.get(890);c.set(416,2907);c.get(1062);c.set(779,2620);c.get(701);c.set(352,2903);c.set(744,399);c.set(78,465);c.set(79,1788);c.get(121);c.get(461);c.set(1088,2749);c.get(553);c.set(1362,911);c.set(720,1869);c.get(1088);c.get(992);c.get(799);c.set(1008,1631);c.set(614,2142);c.get(472);c.set(436,2319);c.set(1228,3193);c.get(1324);c.get(319);c.set(1227,2430);c.set(6,3129);c.get(727);c.get(415);c.get(477);c.set(24,3061);c.set(677,1807);c.set(1094,2786);c.set(22,1449);c.set(7,1432);c.set(143,652);c.set(187,1950);c.get(1119);c.set(24,2482);c.get(581);c.get(860);c.set(201,2130);c.get(275);c.set(1019,104);c.set(785,2435);c.set(395,2640);c.get(1131);c.set(941,1414);c.set(675,3262);c.get(1077);c.set(735,1664);c.set(69,1820);c.set(59,1782);c.set(1097,577);c.set(441,2901);c.set(360,518);c.set(943,1168);c.get(1236);c.get(1360);c.set(1231,160);c.set(598,402);c.get(258);c.get(127);c.get(10);c.get(1032);c.set(1279,1364);c.set(155,2141);c.set(1110,356);c.set(1327,2412);c.set(71,1697);c.get(1067);c.get(30);c.set(857,1063);c.get(1052);c.get(103);c.set(803,2030);c.set(929,1346);c.set(586,1343);c.get(827);c.set(1160,2541);c.set(1005,421);c.set(503,2964);c.set(531,2938);c.get(1018);c.set(1086,3127);c.get(482);c.get(603);c.set(910,804);c.set(376,1279);c.get(832);c.get(1280);c.get(179);c.get(239);c.get(1413);c.get(146);c.get(76);c.set(600,1322);c.get(857);c.set(234,1009);c.set(870,402);c.get(1033);c.set(244,1900);c.set(688,2861);c.set(439,403);c.set(1271,1080);c.get(1236);c.set(144,63);c.get(209);c.set(1019,3277);c.get(640);c.set(1052,746);c.get(1247);c.get(1115);c.get(907);c.get(521);c.set(503,508);c.set(1300,1546);c.get(857);c.set(1215,2642);c.get(722);c.get(164);c.get(79);c.get(37);c.set(223,1705);c.set(510,603);c.get(900);c.get(89);c.set(124,417);c.get(598);c.set(403,3256);c.get(415);c.get(1291);c.get(1256);c.set(787,2726);c.set(1002,1453);c.set(379,44);c.set(226,2863);c.get(658);c.set(367,786);c.set(594,1356);c.set(1266,2967);c.get(456);c.set(119,377);c.set(481,855);c.get(1349);c.set(369,1005);c.set(1345,1734);c.get(163);c.get(661);c.set(1268,2426);c.set(834,3238);c.get(957);c.get(337);c.set(379,1565);c.set(974,1589);c.get(354);c.get(496);c.set(393,3075);c.get(762);c.set(1400,2862);c.set(257,2649);c.get(227);c.set(590,334);c.set(266,2158);c.get(274);c.get(996);c.get(1054);c.get(741);c.get(41);c.set(1355,302);c.set(346,666);c.get(1126);c.set(475,1232);c.get(740);c.get(900);c.set(1324,785);c.set(124,548);c.get(1049);c.set(1201,1324);c.get(544);c.get(1146);c.set(822,1917);c.set(159,3292);c.set(961,2791);c.get(682);c.set(507,1700);c.get(1182);c.get(1318);c.set(285,2286);c.set(1140,45);c.get(15);c.set(922,238);c.get(516);c.set(453,615);c.get(161);c.get(722);c.get(87);c.set(223,1995);c.set(1055,1059);c.set(1157,1186);c.set(175,2207);c.set(774,26);c.set(1351,1392);c.get(947);c.get(979);c.get(240);c.get(928);c.get(289);c.set(1393,1248);c.set(304,2989);c.set(908,1659);c.get(85);c.set(550,2647);c.set(706,1335);c.set(1129,434);c.set(326,1682);c.set(610,2330);c.get(1383);c.get(452);c.set(746,3263);c.get(1337);c.set(962,1018);c.set(243,932);c.set(91,1897);c.set(22,2449);c.set(313,2482);c.set(739,436);c.set(418,1675);c.set(417,1820);c.set(983,2152);c.set(866,2639);c.get(575);c.get(344);c.get(1127);c.set(266,2073);c.set(969,727);c.set(122,1913);c.set(1035,624);c.set(98,3164);c.get(588);c.get(523);c.set(954,2825);c.get(358);c.set(18,3014);c.get(456);c.set(266,2946);c.set(786,1070);c.set(841,3093);c.set(422,1944);c.set(62,1606);c.get(692);c.get(159);c.set(1300,382);c.get(824);c.get(16);c.set(142,2458);c.get(1038);c.set(529,1291);c.set(244,1486);c.set(573,705);c.set(128,1000);c.set(552,2538);c.set(633,2469);c.set(70,2850);c.set(1248,3104);c.set(1295,730);c.set(1338,1754);c.set(660,355);c.set(815,661);c.get(303);c.get(109);c.get(439);c.get(1032);c.set(979,2383);c.get(1048);c.set(262,3002);c.set(269,1235);c.get(716);c.get(784);c.set(1307,3089);c.set(520,1573);c.set(1425,3146);c.get(180);c.set(876,1823);c.get(1194);c.set(851,1596);c.get(746);c.get(869);c.set(62,3298);c.set(1415,1505);c.set(440,2956);c.set(901,3181);c.get(1343);c.set(783,2894);c.set(481,1894);c.set(1295,1759);c.set(25,240);c.set(67,1642);c.set(938,1464);c.set(343,2795);c.get(346);c.set(494,656);c.get(366);c.set(1388,612);c.set(564,1826);c.get(811);c.get(872);c.set(1182,742);c.get(968);c.get(699);c.get(576);c.get(956);c.get(480);c.set(1040,2428);c.get(1310);c.set(1215,1208);c.set(1296,3066);c.set(507,1863);c.get(192);c.get(399);c.set(1196,1284);c.get(36);c.set(752,119);c.set(365,521);c.get(1431);c.get(752);c.set(564,1373);c.set(178,1758);c.get(441);c.get(169);c.set(587,1414);c.set(495,112);c.set(230,2947);c.set(337,3143);c.set(967,2009);c.set(586,1484);c.set(1315,3263);c.get(79);c.set(191,65);c.get(469);c.get(986);c.set(1184,339);c.get(825);c.get(1119);c.set(177,995);c.set(1129,3092);c.get(409);c.set(378,2423);c.set(1257,1533);c.set(917,2559);c.set(143,129);c.get(1339);c.set(951,1185);c.get(2);c.set(111,2296);c.get(178);c.set(884,1236);c.get(565);c.get(1328);c.set(1218,369);c.set(1108,3053);c.get(468);c.set(280,911);c.get(1017);c.set(52,1390);c.get(1177);c.set(1298,573);c.set(1372,2299);c.set(74,1930);c.get(308);c.get(1297);c.set(1233,2619);c.set(357,1149);c.set(519,2446);c.get(493);c.get(1176);c.set(759,1412);c.set(1248,2702);c.set(495,1564);c.get(1362);c.get(535);c.set(604,2993);c.set(1395,339);c.get(1346);c.get(1281);c.get(161);c.set(687,1231);c.set(257,2702);c.set(299,1175);c.get(1099);c.get(1080);c.set(484,2856);c.set(279,2578);c.get(1379);c.set(621,392);c.get(68);c.set(329,735);c.get(418);c.get(976);c.set(984,918);c.get(589);c.set(401,1200);c.set(422,188);c.get(1281);c.set(770,1023);c.set(254,1704);c.get(162);c.set(1175,1585);c.set(1274,2884);c.set(814,879);c.get(866);c.get(755);c.get(720);c.set(560,935);c.get(533);c.set(540,1275);c.get(1373);c.set(950,3165);c.get(871);c.get(1054);c.set(1197,2930);c.get(929);c.get(1177);c.set(1144,2465);c.set(988,467);c.get(1062);c.get(1244);c.set(25,2906);c.get(1152);c.set(1140,1839);c.get(1373);c.set(1254,2216);c.set(660,2812);c.get(655);c.set(753,847);c.set(544,3010);c.get(978);c.set(360,2068);c.set(781,1914);c.get(46);c.get(960);c.set(21,452);c.get(1050);c.set(259,2214);c.set(903,2120);c.set(344,2543);c.set(602,1861);c.set(459,3235);c.set(815,1484);c.set(1424,92);c.set(840,1219);c.get(196);c.set(11,225);c.set(66,533);c.set(1220,1127);c.get(764);c.get(28);c.set(296,1407);c.set(202,182);c.get(352);c.get(931);c.set(766,1818);c.set(559,1281);c.set(792,2907);c.set(85,2513);c.set(376,1726);c.set(1030,3026);c.set(748,635);c.set(926,174);c.set(1034,201);c.set(269,1829);c.get(158);c.set(1070,601);c.set(539,3033);c.set(128,62);c.get(467);c.set(1104,2808);c.set(1185,2882);c.set(1260,2915);c.get(158);c.set(300,181);c.get(358);c.get(153);c.set(313,2622);c.get(794);c.set(469,2993);c.set(386,1615);c.set(710,3287);c.get(881);c.get(88);c.set(560,1395);c.get(836);c.get(699);c.get(673);c.get(615);c.get(1013);c.get(1214);c.set(1319,2153);c.get(433);c.set(1129,1471);c.get(269);c.set(1425,221);c.set(43,1132);c.get(832);c.get(822);c.get(72);c.set(709,1131);c.get(418);c.set(279,337);c.set(845,2859);c.get(490);c.get(234);c.set(1108,849);c.set(985,3287);c.set(652,924);c.set(987,2364);c.get(277);c.set(780,168);c.set(659,487);c.set(1417,596);c.get(1023);c.get(874);c.get(513);c.get(1346);c.get(1261);c.set(1365,1788);c.get(168);c.set(1083,1435);c.set(513,395);c.set(645,2277);c.get(273);c.get(432);c.set(443,1479);c.set(680,1589);c.set(1400,1736);c.set(1163,319);c.set(393,2477);c.set(1333,2613);c.set(1275,356);c.get(1156);c.get(1165);c.get(379);c.set(422,2082);c.set(400,1280);c.set(676,283);c.set(1311,1362);c.set(61,2677);c.set(358,2748);c.get(553);c.set(731,3084);c.set(667,3230);c.get(1104);c.set(1241,1199);c.set(327,201);c.set(985,1948);c.get(553);c.set(1360,3073);c.set(81,1520);c.get(473);c.set(1412,1835);c.set(693,2954);c.set(1061,949);c.set(1165,2832);c.get(1130);c.set(526,397);c.set(1202,1088);c.get(542);c.set(1251,1490);c.get(882);c.get(1319);c.set(215,3038);c.set(432,923);c.get(497);c.set(649,109);c.set(1265,463);c.get(117);c.set(1353,1107);c.get(667);c.set(1395,2945);c.get(182);c.set(1082,2926);c.set(200,1520);c.set(55,2505);c.set(635,2495);c.set(879,1978);c.get(95);c.set(22,663);c.get(363);c.get(528);c.get(152);c.get(743);c.get(1139);c.get(339);c.set(697,329);c.get(1275);c.get(427);c.get(848);c.set(852,1622);c.set(793,1649);c.set(111,2416);c.set(574,1597);c.get(1043);c.set(1183,3137);c.get(519);c.get(412);c.get(87);c.set(514,1251);c.get(1259);c.set(1374,881);c.set(391,81);c.get(398);c.get(596);c.set(679,715);c.set(920,1686);c.set(137,1602);c.set(1363,198);c.set(1268,3079);c.get(523);c.set(76,330);c.get(638);c.set(946,3094);c.set(672,452);c.set(1161,1649);c.set(572,1331);c.get(513);c.get(1298);c.get(848);c.get(993);c.get(425);c.set(945,1673);c.get(1191);c.set(453,2083);c.set(788,885);c.get(1352);c.get(1204);c.get(542);c.set(1418,917);c.set(134,1341);c.get(1033);c.get(62);c.set(744,1964);c.get(92);c.set(864,1150);c.set(342,525);c.get(1268);c.set(600,637);c.set(704,302);c.set(545,158);c.get(1132);c.set(1116,1487);c.set(121,2522);c.get(289);c.get(590);c.set(997,3124);c.set(596,717);c.set(678,1115);c.set(1368,1987);c.set(1199,936);c.get(489);c.set(1129,11);c.set(937,921);c.get(1369);c.set(1147,594);c.set(774,930);c.set(345,939);c.set(1008,676);c.get(20);c.set(129,1701);c.get(804);c.set(803,1410);c.get(458);c.get(365);c.set(29,2139);c.set(642,407);c.set(505,1349);c.set(1221,1084);c.get(1235);c.get(379);c.get(159);c.set(70,932);c.get(1382);c.set(190,1534);c.get(1103);c.get(108);c.get(656);c.set(1347,3268);c.get(510);c.set(278,2222);c.get(11);c.set(1052,729);c.set(1285,2510);c.set(422,690);c.set(1199,1435);c.set(234,120);c.set(467,3157);c.set(213,1599);c.get(303);c.get(1296);c.set(114,22);c.set(117,1255);c.set(1005,2872);c.get(114);c.set(542,593);c.set(676,1147);c.set(1285,1424);c.get(580);c.set(1195,1652);c.get(1202);c.get(41);c.set(81,63);c.set(149,1788);c.get(1073);c.get(688);c.get(1093);c.set(274,1878);c.get(866);c.set(1198,2577);c.set(365,144);c.set(209,663);c.set(656,2);c.get(561);c.set(1046,742);c.get(1177);c.set(1275,2001);c.get(1340);c.set(690,1375);c.get(66);c.set(271,2498);c.set(1379,2353);c.set(673,1005);c.get(1168);c.get(1311);c.set(687,502);c.get(1336);c.set(96,794);c.get(730);c.set(1211,3042);c.get(1227);c.set(1300,2017);c.set(567,2004);c.set(211,3085);c.set(68,1006);c.set(1067,1187);c.set(65,1312);c.set(950,1797);c.set(438,1695);c.set(295,1453);c.set(1363,1090);c.set(1188,2654);c.set(236,236);c.set(728,2475);c.get(296);c.get(478);c.get(868);c.get(553);c.get(1211);c.set(51,660);c.set(1008,1941);c.set(973,1294);c.set(477,544);c.set(969,3035);c.set(254,198);c.set(1050,860);c.get(311);c.set(1095,2475);c.get(1253);c.set(1373,3008);c.set(123,1416);c.set(465,235);c.set(1239,1884);c.set(1106,999);c.get(53);c.get(226);c.set(168,2596);c.get(513);c.set(1048,341);c.set(136,483);c.set(7,29);c.get(114);c.set(368,2562);c.set(483,2525);c.set(360,1776);c.set(955,236);c.get(605);c.get(59);c.set(114,218);c.get(46);c.set(310,931);c.get(887);c.set(1424,3141);c.set(117,2138);c.set(29,916);c.set(1200,1241);c.set(39,2777);c.set(302,1623);c.set(99,445);c.set(477,3007);c.get(199);c.set(950,2988);c.get(1228);c.set(40,2783);c.get(1154);c.get(1234);c.get(1176);c.get(1018);c.set(1426,2596);c.get(383);c.get(738);c.set(1280,2243);c.set(672,1018);c.get(1352);c.get(704);c.get(691);c.get(999);c.get(152);c.set(862,501);c.set(687,322);c.set(813,1461);c.get(990);c.set(1194,2915);c.get(256);c.set(291,2570);c.set(1228,1573);c.set(414,1739);c.set(482,2385);c.get(334);c.get(975);c.set(759,2487);c.set(792,227);c.set(1034,737);c.set(343,2987);c.get(820);c.set(541,1975);c.get(348);c.set(446,416);c.get(1360);c.get(802);c.set(24,1299);c.set(160,2141);c.set(471,2978);c.get(772);c.get(50);c.set(416,954);c.get(312);c.get(1138);c.get(305);c.set(636,2440);c.set(266,2529);c.get(1422);c.set(10,135);c.set(1025,981);c.get(16);c.set(1349,436);c.set(1160,2943);c.set(1133,2709);c.get(704);c.get(750);c.set(509,103);c.set(166,2968);c.set(793,1719);c.set(864,3188);c.get(1031);c.set(1008,2819);c.set(271,338);c.get(833);c.set(842,2800);c.set(893,598);c.get(511);c.get(1161);c.set(567,501);c.get(425);c.set(1394,3026);c.get(438);c.set(1319,59);c.get(201);c.get(1048);c.get(837);c.get(125);c.set(1090,2983);c.get(438);c.get(423);c.get(519);c.set(139,2608);c.set(979,1110);c.set(201,1186);c.set(1032,2155);c.set(376,2758);c.set(1312,1543);c.set(494,2057);c.get(295);c.get(356);c.set(1045,2146);c.set(307,1766);c.set(575,1574);c.get(1290);c.get(465);c.set(1411,1717);c.set(735,3207);c.get(86);c.set(166,1036);c.set(1129,1536);c.set(31,2944);c.get(468);c.set(594,188);c.get(1274);c.set(1342,2741);c.get(1063);c.get(1261);c.set(914,2310);c.get(1323);c.set(1276,2609);c.get(1224);c.get(1009);c.get(430);c.set(211,805);c.set(1044,984);c.set(1053,1261);c.set(772,2505);c.set(690,568);c.set(321,1121);c.get(1330);c.set(142,836);c.get(304);c.get(936);c.set(1304,3291);c.set(284,1570);c.get(1001);c.set(1271,1144);c.get(583);c.set(40,2655);c.get(842);c.get(1389);c.get(1357);c.set(656,2495);c.get(564);c.set(959,922);c.set(1391,825);c.set(1126,1222);c.set(783,2631);c.set(1038,2879);c.get(405);c.get(691);c.get(1085);c.get(539);c.set(433,3152);c.get(714);c.set(360,2390);c.set(940,3285);c.get(102);c.get(1107);c.set(1136,3063);c.set(1050,3085);c.set(175,1121);c.get(554);c.get(576);c.set(992,1609);c.get(71);c.set(1411,2703);c.set(603,767);c.get(7);c.set(604,32);c.set(704,1304);c.set(653,3143);c.set(1295,1633);c.set(324,2791);c.get(537);c.set(1092,2721);c.set(854,2448);c.get(1410);c.set(1301,2453);c.set(393,575);c.get(1423);c.get(868);c.get(1391);c.get(153);c.get(1276);c.set(760,1906);c.get(1024);c.get(28);c.get(1156);c.set(1121,1961);c.get(606);c.get(522);c.get(953);c.get(822);c.set(721,2736);c.set(693,1771);c.get(504);c.set(133,2671);c.get(474);c.get(1418);c.set(74,2915);c.set(280,353);c.get(1170);c.set(916,1253);c.set(182,801);c.get(343);c.set(1402,102);c.set(139,2738);c.set(110,2718);c.set(514,3111);c.get(1120);c.get(1162);c.set(867,1755);c.set(934,2127);c.get(429);c.set(586,1981);c.set(434,447);c.get(920);c.get(1328);c.set(865,514);c.set(1049,2516);c.set(199,747);c.set(477,3038);c.get(379);c.set(990,1008);c.set(730,772);c.get(1364);c.set(936,3102);c.get(140);c.get(164);c.get(115);c.set(537,1664);c.set(1404,1608);c.get(1087);c.get(1206);c.get(757);c.set(514,149);c.get(457);c.get(1180);c.set(1170,2627);c.get(611);c.set(262,2229);c.set(140,2655);c.set(1193,2394);c.set(112,2143);c.get(463);c.get(998);c.set(166,254);c.set(707,1420);c.get(669);c.set(1251,2625);c.get(1376);c.get(1315);c.set(116,946);c.set(528,1126);c.set(104,1202);c.set(1293,1530);c.set(921,1694);c.set(606,2635);c.set(481,3031);c.set(269,728);c.set(564,2193);c.get(507);c.get(823);c.set(1259,1095);c.set(295,1934);c.set(733,2818);c.get(549);c.set(68,2900);c.set(132,147);c.set(1006,1719);c.set(954,1564);c.get(354);c.set(949,1052);c.get(260);c.get(1326);c.set(988,2673);c.set(1344,1863);c.set(471,3259);c.get(339);c.get(842);c.get(786);c.get(492);c.get(73);c.get(230);c.get(77);c.get(520);c.set(569,255);c.set(857,2425);c.get(1345);c.set(1053,870);c.set(658,2003);c.get(1022);c.set(638,167);c.set(524,2427);c.set(347,836);c.get(1111);c.get(247);c.get(1000);c.set(411,970);c.set(109,1831);c.get(88);c.set(249,546);c.set(1346,145);c.get(911);c.set(291,2147);c.set(346,2360);c.set(1049,263);c.set(699,1803);c.set(628,1625);c.set(1293,2435);c.get(511);c.get(928);c.set(938,928);c.set(238,624);c.get(330);c.get(556);c.set(1153,709);c.get(920);c.set(515,1863);c.set(799,893);c.set(462,2230);c.get(22);c.get(356);c.set(398,2469);c.get(206);c.set(1406,2037);c.get(875);c.get(678);c.set(96,965);c.set(1392,361);c.set(31,1015);c.get(1421);c.set(1050,3094);c.get(1003);c.get(641);c.set(977,1503);c.set(170,1105);c.set(1258,2932);c.set(428,2851);c.set(1155,1847);c.set(173,1471);c.set(1412,2301);c.get(575);c.set(168,2445);c.set(317,2194);c.set(1378,3145);c.get(481);c.set(1368,1310);c.set(684,994);c.set(977,939);c.get(815);c.get(475);c.set(615,257);c.set(229,876);c.get(460);c.set(484,67);c.get(875);c.get(96);c.set(1048,2295);c.set(1079,973);c.set(1095,463);c.get(1336);c.set(371,2256);c.set(743,999);c.get(248);c.set(692,1465);c.get(153);c.set(803,2399);c.set(1271,2446);c.set(1319,2212);c.set(441,805);c.set(374,2128);c.get(712);c.get(262);c.get(464);c.get(284);c.get(1077);c.set(704,338);c.set(1114,2721);c.set(498,2980);c.get(53);c.get(852);c.set(612,2257);c.set(889,1503);c.get(734);c.get(285);c.get(159);c.set(1014,476);c.get(899);c.get(270);c.set(956,2715);c.set(186,508);c.set(13,922);c.get(165);c.get(1422);c.get(410);c.set(377,1680);c.set(540,1243);c.set(727,1546);c.get(662);c.set(496,273);c.set(1203,3102);c.set(1296,2261);c.set(205,619);c.get(815);c.set(530,1073);c.set(1340,184);c.get(1314);c.get(484);c.set(463,520);c.set(1169,2074);c.set(1234,705);c.set(1045,2588);c.set(899,1091);c.set(1312,2739);c.set(260,1446);c.set(427,1888);c.set(1299,1535);c.set(1354,1049);c.set(314,2730);c.get(1408);c.set(1140,1461);c.get(1356);c.set(276,2669);c.set(2,2346);c.set(840,700);c.set(360,2606);c.get(1417);c.set(783,1475);c.set(1115,2933);c.set(615,789);c.set(1013,2956);c.set(1005,3302);c.get(1255);c.get(1270);c.set(1399,1817);c.set(1043,2661);c.get(280);c.get(865);c.get(524);c.get(383);c.set(1160,3039);c.get(1406);c.set(497,758);c.set(1019,196);c.set(670,3057);c.get(439);c.set(75,730);c.set(200,1772);c.set(503,3090);c.get(470);c.set(437,965);c.set(249,2440);c.set(1283,234);c.get(1247);c.set(245,823);c.set(170,2001);c.set(909,385);c.set(726,373);c.set(892,1088);c.set(1000,1685);c.get(1268);c.set(296,3192);c.set(869,1727);c.set(1107,1369);c.get(856);c.get(118);c.get(1003);c.set(334,2111);c.set(755,2837);c.set(801,3069);c.set(561,897);c.set(479,144);c.set(898,497);c.get(1297);c.get(1025);c.set(728,509);c.get(853);c.get(634);c.get(231);c.set(151,1738);c.set(614,1868);c.set(143,1468);c.get(1154);c.get(237);c.set(597,2355);c.get(1011);c.get(1124);c.set(357,1406);c.set(213,2104);c.set(821,1241);c.get(1401);c.set(221,749);c.set(699,2677);c.get(1225);c.set(731,1657);c.get(1035);c.get(1318);c.set(701,858);c.set(46,2396);c.get(777);c.get(1366);c.get(607);c.get(900);c.get(408);c.get(918);c.get(712);c.set(962,1534);c.get(38);c.get(729);c.set(153,1424);c.set(1071,2706);c.set(614,568);c.set(639,1367);c.set(323,1356);c.get(661);c.set(964,2337);c.set(1292,2922);c.get(50);c.set(410,2732);c.set(930,2633);c.get(81);c.set(1219,1061);c.get(838);c.set(288,3074);c.get(1348);c.set(673,1310);c.get(781);c.get(1062);c.get(1312);c.set(269,2652);c.get(1360);c.get(125);c.get(264);c.get(1131);c.set(779,2032);c.get(1281);c.get(4);c.get(137);c.get(165);c.set(403,582);c.set(294,1837);c.set(106,641);c.get(545);c.get(367);c.set(902,2550);c.set(889,1544);c.get(1111);c.get(298);c.set(1072,2823);c.set(414,2968);c.get(998);c.get(448);c.set(1132,3217);c.set(110,1674);c.set(68,1103);c.get(1337);c.set(425,1196);c.get(761);c.set(1325,50);c.get(144);c.get(712);c.set(548,2590);c.get(253);c.set(61,740);c.set(260,1630);c.get(1263);c.set(938,2210);c.get(734);c.get(1354);c.get(635);c.get(488);c.set(41,273);c.set(647,2566);c.get(677);c.set(1137,1836);c.set(820,2886);c.get(1);c.set(1134,489);c.set(1260,3250);c.get(1357);c.get(1372);c.get(1178);c.set(1240,1392);c.set(337,2751);c.set(1022,285);c.set(676,522);c.get(134);c.get(161);c.set(1332,1481);c.get(1216);c.get(624);c.get(685);c.get(177);c.set(558,1017);c.set(25,2438);c.get(4);c.set(583,1426);c.get(1111);c.set(243,2727);c.set(568,984);c.get(609);c.get(877);c.get(874);c.set(1263,236);c.set(716,275);c.get(1356);c.set(548,826);c.set(512,2464);c.get(588);c.get(67);c.set(500,2731);c.set(55,2527);c.set(260,1263);c.set(266,1501);c.get(1317);c.get(667);c.set(312,570);c.get(518);c.get(465);c.get(1427);c.get(136);c.set(1245,2793);c.get(114);c.set(793,1736);c.set(220,2247);c.set(1302,2756);c.get(791);c.get(1164);c.get(1263);c.get(238);c.get(9);c.get(546);c.set(1261,5);c.get(372);c.get(101);c.get(73);c.set(1212,1277);c.get(1004);c.set(1386,776);c.set(1020,1961);c.get(812);c.set(574,1099);c.set(612,1682);c.get(1414);c.get(892);c.get(846);c.set(514,445);c.set(233,2336);c.get(1143);c.set(52,1820);c.get(1022);c.set(293,2464);c.get(1114);c.get(206);c.set(255,632);c.set(832,723);c.set(1394,2399);c.set(608,2126);c.set(1129,2096);c.set(1023,2498);c.set(832,112);c.get(1187);c.set(399,2160);c.set(423,720);c.set(1020,2706);c.set(951,2514);c.get(1137);c.get(916);c.set(79,1261);c.get(538);c.set(404,1015);c.set(1404,1342);c.set(730,734);c.get(523);c.get(926);c.get(562);c.set(589,1503);c.get(1095);c.set(618,91);c.get(1108);c.get(422);c.set(1217,2066);c.get(1063);c.set(240,328);c.get(966);c.set(304,1981);c.get(1019);c.get(521);c.set(556,2854);c.set(995,2384);c.get(748);c.set(622,2184);c.get(821);c.get(878);c.get(98);c.set(947,477);c.set(1049,567);c.set(935,1296);c.get(159);c.get(1065);c.get(1153);c.get(46);c.set(104,2902);c.set(592,2078);c.set(990,2834);c.get(1044);c.set(1386,2339);c.set(955,1827);c.get(360);c.set(1074,1588);c.get(201);c.get(183);c.set(1080,7);c.set(126,3078);c.get(810);c.set(994,2690);c.set(1043,3038);c.set(468,1450);c.get(1013);c.get(1178);c.get(560);c.set(941,2726);c.set(1260,574);c.get(847);c.get(51);c.set(461,219);c.get(35);c.set(1405,416);c.set(186,157);c.set(271,3095);c.set(1249,3221);c.get(1105);c.get(36);c.set(1418,1733);c.get(12);c.get(957);c.set(1077,3000);c.get(308);c.set(179,1227);c.set(1220,1628);c.set(108,1726);c.get(609);c.set(10,919);c.set(673,317);c.set(921,1427);c.get(1229);c.set(804,1455);c.set(43,1315);c.get(28);c.set(1320,3049);c.set(1117,2952);c.set(247,2928);c.get(884);c.get(195);c.set(158,1655);c.get(696);c.get(806);c.get(713);c.set(971,938);c.set(499,2952);c.set(1173,2607);c.get(1395);c.get(745);c.set(1357,2809);c.set(1032,3276);c.set(1366,1713);c.get(516);c.set(583,1251);c.get(1313);c.get(246);c.set(488,1756);c.set(867,1703);c.set(301,433);c.set(158,766);c.set(1265,28);c.set(1361,2054);c.set(881,1530);c.set(995,2102);c.set(788,1525);c.set(58,667);c.set(1108,554);c.get(606);c.set(745,2180);c.set(650,2234);c.get(1370);c.set(852,557);c.get(1064);c.set(1400,688);c.get(218);c.get(694);c.set(1289,2952);c.get(1062);c.set(361,3082);c.set(439,285);c.set(464,3238);c.get(1320);c.set(225,1174);c.get(295);c.set(487,1387);c.set(908,412);c.get(941);c.set(1223,1043);c.set(812,459);c.get(1187);c.set(284,3199);c.get(932);c.get(984);c.get(1047);c.set(243,894);c.get(771);c.set(786,2038);c.get(1169);c.set(933,488);c.get(457);c.set(646,795);c.set(235,2431);c.get(955);c.get(135);c.set(66,2784);c.set(1033,4);c.set(849,815);c.set(873,953);c.get(1409);c.get(152);c.set(209,1127);c.set(1332,780);c.set(745,1990);c.set(1294,41);c.get(1044);c.get(1066);c.get(1100);c.get(35);c.get(907);c.set(611,2579);c.set(1380,1959);c.set(952,603);c.set(1026,396);c.get(840);c.get(908);c.set(623,2597);c.get(746);c.get(1256);c.set(1334,1713);c.set(1222,107);c.set(1115,295);c.set(718,1924);c.get(681);c.get(1180);c.set(396,2666);c.set(501,658);c.get(119);c.get(1301);c.get(43);c.set(180,1840);c.set(308,1582);c.set(1402,2479);c.get(623);c.set(239,3111);c.set(611,49);c.set(999,2839);c.set(777,150);c.set(369,2839);c.get(18);c.set(495,2690);c.set(407,591);c.set(599,125);c.set(1330,199);c.set(974,1190);c.get(1168);c.get(1137);c.set(879,2576);c.get(619);c.set(1323,1278);c.set(1078,657);c.get(746);c.set(578,2161);c.get(1333);c.get(689);c.set(1410,2960);c.set(485,2635);c.set(208,2646);c.set(379,2069);c.get(1340);c.set(855,2955);c.set(498,1653);c.set(1081,2520);c.set(621,2234);c.set(686,3290);c.set(1150,1926);c.set(418,2429);c.set(912,3095);c.set(1191,2514);c.set(1163,1860);c.set(149,2769);c.get(960);c.get(504);c.get(439);c.get(580);c.set(154,1291);c.get(40);c.set(586,1066);c.set(1007,1326);c.set(902,174);c.get(723);c.set(149,444);c.set(736,2955);c.set(961,374);c.set(968,1298);c.set(579,1208);c.set(315,1776);c.get(871);c.get(785);c.get(995);c.set(737,1218);c.set(1101,3203);c.get(935);c.set(485,1527);c.get(463);c.set(983,213);c.set(294,573);c.set(497,925);c.set(434,329);c.set(208,1612);c.get(1343);c.get(941);c.get(1175);c.get(1133);c.set(822,1756);c.set(861,585);c.get(1077);c.set(650,1010);c.set(107,271);c.get(1385);c.get(878);c.get(1325);c.set(225,314);c.get(922);c.set(113,2385);c.get(1404);c.set(270,1750);c.set(552,1810);c.set(625,11);c.set(72,1439);c.set(1417,970);c.set(975,676);c.set(314,2074);c.set(601,342);c.set(423,447);c.set(1304,2456);c.set(474,2280);c.set(1008,434);c.get(385);c.set(1001,629);c.get(622);c.set(203,331);c.set(1077,820);c.get(1147);c.get(528);c.set(1204,2110);c.get(662);c.set(1429,40);c.get(462);c.set(268,2599);c.get(205);c.set(297,2991);c.set(1412,1531);c.get(427);c.get(1058);c.set(864,1830);c.get(524);c.set(253,1344);c.set(74,2133);c.get(1292);c.set(616,801);c.get(347);c.get(1016);c.get(551);c.get(1362);c.set(187,1946);c.get(687);c.get(878);c.set(711,874);c.get(1025);c.set(833,703);c.set(1158,1068);c.set(322,1591);c.set(1049,1815);c.set(7,539);c.get(195);c.get(604);c.set(1207,1780);c.set(5,2098);c.get(1388);c.get(1027);c.set(962,908);c.set(774,489);c.set(1323,1321);c.get(1051);c.get(883);c.get(1208);c.get(465);c.set(1007,905);c.set(1131,542);c.set(67,491);c.get(460);c.set(1295,240);c.set(616,1857);c.set(572,558);c.set(1153,2252);c.set(759,2736);c.get(31);c.set(1127,1594);c.set(576,305);c.get(765);c.get(751);c.set(504,648);c.get(817);c.set(1180,411);c.set(565,848);c.set(417,1092);c.set(606,1254);c.set(450,200);c.get(871);c.set(543,2752);c.get(1308);c.set(1120,2475);c.get(629);c.set(705,841);c.set(677,2042);c.set(498,1819);c.set(129,745);c.get(1299);c.set(874,2878);c.set(521,2353);c.get(1242);c.set(1430,2676);c.get(525);c.set(524,2686);c.set(1163,3241);c.set(1388,1725);c.get(1063);c.set(103,620);c.get(512);c.set(1317,2990);c.get(561);c.get(880);c.set(840,17);c.get(657);c.set(1102,2378);c.set(1182,1600);c.get(1415);c.set(584,1932);c.set(727,2175);c.get(682);c.set(765,1107);c.get(1317);c.set(707,2729);c.set(1210,2748);c.set(1282,2413);c.set(688,3101);c.set(935,1238);c.get(540);c.set(1383,684);c.get(200);c.set(88,2695);c.get(946);c.set(665,959);c.set(1408,2115);c.set(541,38);c.set(28,1458);c.set(887,1410);c.set(24,1097);c.set(383,1999);c.set(781,1820);c.get(1405);c.get(216);c.set(549,142);c.get(841);c.set(1070,1703);c.set(129,835);c.get(178);c.get(305);c.set(316,1833);c.get(1149);c.set(1295,898);c.get(325);c.set(180,1720);c.set(1169,904);c.get(402);c.set(574,164);c.get(747);c.set(1290,35);c.get(907);c.set(1361,905);c.get(188);c.set(1056,141);c.set(272,828);c.get(383);c.set(783,2207);c.set(1008,1792);c.set(36,856);c.get(965);c.set(838,1392);c.set(67,1649);c.get(509);c.get(798);c.get(979);c.set(249,565);c.set(825,3038);c.set(997,8);c.get(385);c.set(273,2040);c.set(471,2795);c.set(461,961);c.get(231);c.set(1242,2666);c.set(73,1752);c.set(987,2035);c.get(1294);c.set(1034,1873);c.set(228,1180);c.set(1046,1422);c.set(249,2188);c.set(1396,941);c.set(1020,247);c.get(1288);c.set(28,2783);c.get(1027);c.get(876);c.set(941,1833);c.set(47,627);c.set(1339,903);c.get(962);c.set(243,1998);c.set(1426,3295);c.get(1341);c.set(1257,830);c.get(1016);c.set(887,2393);c.get(1374);c.set(1214,3008);c.set(1301,1659);c.get(446);c.get(366);c.get(424);c.set(447,2300);c.get(1273);c.get(902);c.get(1327);c.set(60,2761);c.set(764,1674);c.set(1210,2517);c.get(1307);c.set(1368,791);c.set(983,834);c.get(860);c.get(533);c.set(130,1298);c.set(985,524);c.set(1430,1634);c.get(253);c.set(1122,2547);c.set(936,742);c.set(685,2355);c.set(1065,2533);c.set(53,3262);c.set(14,1066);c.set(341,1214);c.get(284);c.get(567);c.set(2,2292);c.set(1174,2998);c.set(312,3104);c.get(406);c.set(800,1185);c.get(167);c.get(916);c.set(139,1937);c.set(298,301);c.set(864,2322);c.set(625,1368);c.get(1390);c.get(320);c.get(752);c.get(433);c.get(288);c.set(551,2190);c.get(1202);c.set(1245,1196);c.set(1002,2157);c.set(1038,1600);c.get(1284);c.set(394,1768);c.get(204);c.set(469,1682);c.get(202);c.get(554);c.set(1040,1920);c.set(888,669);c.get(207);c.get(859);c.set(587,2465);c.set(57,1939);c.get(485);c.get(347);c.get(44);
	}

}
