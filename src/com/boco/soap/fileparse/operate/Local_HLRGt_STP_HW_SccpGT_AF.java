package com.boco.soap.fileparse.operate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Local_HLRGt_STP_HW_SccpGT_AF implements ISpliteOperate{
	private Logger log = LoggerFactory.getLogger(Local_HLRGt_STP_HW_SccpGT_AF.class);
	
	@Override
	public List<Map<String, String>> operate(List<Map<String, String>> oldList) {
		if(log.isDebugEnabled()){
			log.debug(oldList.toString());
		}
		List<Map<String,String>> newList = new ArrayList<Map<String,String>>();
		for(Map<String,String> map : oldList){
			String num = map.get("AF");
			if(num!=null&&!num.contains("E")){
				map.put("STA", "0");
				newList.add(map);
				
			}else{
				List<Map<String,String>> newCodeList = this.copy(oldList,map,"AF","E");
				newList.addAll(newCodeList);
			}
		}
		if(log.isDebugEnabled()){
			log.debug(newList.toString());
		}
		return newList;
	}
	
	private List<Map<String, String>> copy(List<Map<String, String>> oldList, Map<String,String> map,String name,String newCodes) {
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();
			
			for (int i = 0; i < 10; i++) {
				Iterator<String> keyIt = map.keySet().iterator();
				Map<String,String> newMap = new HashMap<String, String>();
				while(keyIt.hasNext()){
					String key = keyIt.next();
					if(key.equalsIgnoreCase(name)){
						String tel=map.get(key).replaceFirst(newCodes, String.valueOf(i));
						newMap.put(key,tel);
						if (tel.indexOf(newCodes)>0) {
							result.addAll(copy(oldList, newMap,name,newCodes));
						}
						
					} else {
						newMap.put(key, map.get(key));
					}
				}
				boolean flag=false;
				for(Map<String,String> mapTmp : oldList){
					if (mapTmp.get("AF").equals(newMap.get("AF"))) {
						flag=true;
						break;
					}
				}
				if (!flag) {
					result.add(newMap);
				}
			}
			
		return result;
	}
}
