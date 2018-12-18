package com.boco.soap.fileparse.operate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Editor��Jianwen Sha
 * Edited time:2015-8-4 15:50:04
 * Desc:����HSTP�豸  SCCP-GLBTITL����������GTAR����
 */
public class Local_BossGt_Hstp_Bell_SccpGT_GTAR implements ISpliteOperate{
	private Logger log = LoggerFactory.getLogger(Local_BossGt_Hstp_Bell_SccpGT_GTAR.class);
	
	@Override
	public List<Map<String, String>> operate(List<Map<String, String>> oldList) {
		if(log.isDebugEnabled()){
			log.debug(oldList.toString());
		}
		List<Map<String,String>> newList = new ArrayList<Map<String,String>>();
		List<Map<String,String>> newList2 = new ArrayList<Map<String,String>>();
		String commNum = "";
		for(Map<String,String> map : oldList){
			String num = map.get("GTAR");
			if(num!=null&&!num.equals("")){
				commNum = num;
				newList.add(map);
			}else{
				List<Map<String,String>> newCodeList = this.copy(map,commNum,"GTAR");
				newList.addAll(newCodeList);
			}
		}
		for(Map<String,String> map : newList){
			String num = map.get("DN");
			if(num!=null&&!num.contains("E")){
				map.put("STA", "0");
				newList2.add(map);
			}else{
				List<Map<String,String>> newCodeList = this.copyDN(oldList,map,"DN","E");
				newList2.addAll(newCodeList);
			}
		}
		if(log.isDebugEnabled()){
			log.debug(newList.toString());
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
			newMap.put("STA", "1");
			result.add(newMap);
		return result;
	}
	

	/**
	 * ���Ŷ��е�E�ֲ��0-9��10������
	 * @param oldList 
	 * @param map
	 * @param name
	 * @param newCodes
	 * @return
	 */
	private List<Map<String, String>> copyDN(List<Map<String, String>> oldList, Map<String,String> map,String name,String newCodes) {
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();
			
			for (int i = 0; i < 10; i++) {
				Map<String,String> newMap = new HashMap<String, String>();
				Iterator<String> keyIt = map.keySet().iterator();
				while(keyIt.hasNext()){
					String key = keyIt.next();
					if(key.equalsIgnoreCase(name)){
						String tel=map.get(key).replaceFirst(newCodes, String.valueOf(i));
						newMap.put(key,tel);
						if (tel.indexOf(newCodes)>0) {
							result.addAll(copyDN(oldList, newMap,name,newCodes));
						}
						
					} else {
						newMap.put(key, map.get(key));
					}
				}
				boolean flag=false;
				for(Map<String,String> mapTmp : oldList){
					if (mapTmp.get("DN").equals(newMap.get("DN"))) {
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
