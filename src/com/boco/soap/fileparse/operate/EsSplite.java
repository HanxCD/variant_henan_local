package com.boco.soap.fileparse.operate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class EsSplite implements ISpliteOperate{
	
	@Override
	public List<Map<String, String>> operate(List<Map<String, String>> oldList) {
		List<Map<String,String>> newList = new ArrayList<Map<String,String>>();
		List<Map<String,String>> newList2 = new ArrayList<Map<String,String>>();
		String commNum = "";
		for(Map<String,String> map : oldList){
			String num = map.get("ES");
			if(num!=null&&!num.equals("")){
				commNum = num;
				newList.add(map);
			}else{
				List<Map<String,String>> newCodeList = this.copy(map,commNum,"ES");
				newList.addAll(newCodeList);
			}
		}
		for (Map<String, String> map : newList) {
			String num = (String) map.get("SPLITPC");
			if ((num != null) && (!num.equals(""))) {
				String[] arrSpc = num.trim().split("\\s+|,|，|\\.|、");
				String spc = "";
				for (int i = 0; i < arrSpc.length; i++) {
					int spcNum = Integer.valueOf(arrSpc[i]);
					spc += Integer.toHexString(spcNum).toUpperCase();
				}
				map.put("SPLITPC", spc);
				newList2.add(map);
			}
		}
		return newList2;
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
