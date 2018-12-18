package com.boco.soap.fileparse.operate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Jianwen Sha
 * Edited time:2015-8-25 11:04:23
 * Description  诺西属性分析核查主键:ANUMBER,861380371,INT   核查项: BNUMBER
 */
public class local_Boss_Mss_NK_ZRQI_TS implements ISpliteOperate{
	/*
	 * local_Boss_Mss_NK_ZRQI_TS.class
	 * private Logger log = LoggerFactory.getLogger(local_Boss_Mss_NK_ZRQI_TS.class);
	 */
	
	@Override
	public List<Map<String, String>> operate(List<Map<String, String>> oldList) {
		List<Map<String,String>> newList = new ArrayList<Map<String,String>>();
		String commNum = "";
		for(Map<String,String> map : oldList){
			String num = map.get("TS");
			if(num!=null&&!num.equals("")){
				//TS 
				commNum = num;	
				newList.add(map);
			}else{
				List<Map<String,String>> newCodeList = this.copy(map,commNum,"TS");
				newList.addAll(newCodeList);
			}
		}
		return newList;
	}
	
	
	@SuppressWarnings("static-method")
	private List<Map<String, String>> copy(Map<String,String> map,String newCodes,String name) {
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();
		
			Map<String,String> newMap = new HashMap<String, String>();
			Iterator<String> keyIt = map.keySet().iterator();
			while(keyIt.hasNext()){
				String key = keyIt.next();
				if(key.endsWith(name)){
					newMap.put(key, newCodes);
				} else {
					newMap.put(key, map.get(key));
				}
			}
			result.add(newMap);
		return result;
	}
}
