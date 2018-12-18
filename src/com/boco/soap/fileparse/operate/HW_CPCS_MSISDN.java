package com.boco.soap.fileparse.operate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * @ClassName:  HW_CPCS_MSISDN   
 * @Description:TODO(配置类  导入HW_CPCS_MSISDN 现网表数据)   
 * @author: mengningning 
 * @date:   2017-5-9 上午11:51:20   
 * @version: V1.0  
 * @Copyright: 2017 www.boco.com.cn Inc. All rights reserved.
 */
public class HW_CPCS_MSISDN implements ISpliteOperate{

	@Override
	public List<Map<String, String>> operate(List<Map<String, String>> oldList) {
		List<Map<String, String>> newList = new ArrayList<Map<String, String>>();
		for (Map<String, String> map : oldList) {
			String start = map.get("STARTNO");
			String end = map.get("ENDNO");
			if(start.startsWith("861")){
					start = map.get("STARTNO").substring(2,map.get("STARTNO").length()-4).trim();
					end = map.get("ENDNO").substring(2,map.get("ENDNO").length()-4).trim();
					System.out.println(start+"===="+end);
					   int count=Integer.parseInt(end)-Integer.parseInt(start);
					   int StartNo=Integer.parseInt(start);
					   int EndNo=Integer.parseInt(end);
					   for(int i=0;i<=count;i++){
						   Map<String,String> newMap = new HashMap<String, String>();
						   int Startnum=StartNo+i;
						    newMap.put("STARTNO", String.valueOf("86"+Startnum));
							newMap.put("ENDNO", String.valueOf("86"+Startnum));
							newMap.put("NAME", map.get("NAME"));
							newMap.put("MODIFIER", map.get("MODIFIER"));
							newList.add(newMap);
					   } 
			}else{
				System.out.println(start+"--------------------------------");
			}
		}
		
		return newList;
	}

}
