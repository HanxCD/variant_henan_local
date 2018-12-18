package com.boco.soap.fileparse.operate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HW_SCP_BUSINESS implements ISpliteOperate{

	@Override
	public List<Map<String, String>> operate(List<Map<String, String>> oldList) {
		List<Map<String, String>> newList = new ArrayList<Map<String, String>>();
		for (Map<String, String> map : oldList) {
			String start = map.get("STARTNO");
			String end = map.get("ENDNO");
			String type=map.get("NUMTYPE");
			if(start.startsWith("1")){
				if(type.equals("3")){//移动 前七位细分号段数据
//					start=start.substring(0,7);
//					end=start.substring(0,7);
					start = map.get("STARTNO").substring(0,map.get("STARTNO").length()-4);
					end = map.get("ENDNO").substring(0,map.get("ENDNO").length()-4);
//					String AREACODE = map.get("AREACODE");
//					String PRINCENO = map.get("PRINCENO");
//					String NUMTYPE = map.get("NUMTYPE");
//					String ORIMSNO = map.get("ORIMSNO");
//					String MODIFIER = map.get("MODIFIER");
					   int count=Integer.parseInt(end)-Integer.parseInt(start);
					   int StartNo=Integer.parseInt(start);
					   int EndNo=Integer.parseInt(end);
					   for(int i=0;i<=count;i++){
						   Map<String,String> newMap = new HashMap<String, String>();
						   int Startnum=StartNo+i;
						    newMap.put("STARTNO", String.valueOf(Startnum));
							newMap.put("ENDNO", String.valueOf(Startnum));
							newMap.put("AREACODE", map.get("AREACODE"));
							newMap.put("PRINCENO", map.get("PRINCENO"));
							newMap.put("NUMTYPE", map.get("NUMTYPE"));
							newMap.put("ORIMSNO", map.get("ORIMSNO"));
							newMap.put("MODIFIER", map.get("MODIFIER"));
							newList.add(newMap);
					   } 
				}else if(type.equals("2")){//联通大号段数据 0,4 

					if(start.startsWith("17040")||start.startsWith("17070")){
						start = map.get("STARTNO").substring(0,map.get("STARTNO").length()-7);
						end = map.get("ENDNO").substring(0,map.get("ENDNO").length()-7);//前四位
					}else{
					  start = map.get("STARTNO").substring(0,map.get("STARTNO").length()-8);
					  end = map.get("ENDNO").substring(0,map.get("ENDNO").length()-8);//前三位
					}
					String AREACODE = map.get("AREACODE");
					String PRINCENO = map.get("PRINCENO");
					String NUMTYPE = map.get("NUMTYPE");
					String ORIMSNO = map.get("ORIMSNO");
					String MODIFIER = map.get("MODIFIER");
					if(start==end){
					   newList.add(map);				
					  }
					else{
					   int count=Integer.parseInt(end)-Integer.parseInt(start);
					   int StartNo=Integer.parseInt(start);
					   int EndNo=Integer.parseInt(end);
					   for(int i=0;i<=count;i++){
						   Map<String,String> newMap = new HashMap<String, String>();
						   int Startnum=StartNo+i;
						    newMap.put("STARTNO", String.valueOf(Startnum));
							newMap.put("ENDNO", String.valueOf(Startnum));
							newMap.put("AREACODE", map.get("AREACODE"));
							newMap.put("PRINCENO", map.get("PRINCENO"));
							newMap.put("NUMTYPE", map.get("NUMTYPE"));
							newMap.put("ORIMSNO", map.get("ORIMSNO"));
							newMap.put("MODIFIER", map.get("MODIFIER"));
							newList.add(newMap);
					   } 
				   }
				}else if(type.equals("4")){//电信大号段数据
					
					if(start.startsWith("1700")){
						start = map.get("STARTNO").substring(0,map.get("STARTNO").length()-7);
						end = map.get("ENDNO").substring(0,map.get("ENDNO").length()-7);//前四位
					}else{
					  start = map.get("STARTNO").substring(0,map.get("STARTNO").length()-8);
					  end = map.get("ENDNO").substring(0,map.get("ENDNO").length()-8);//前三位
					}
						String AREACODE = map.get("AREACODE");
						String PRINCENO = map.get("PRINCENO");
						String NUMTYPE = map.get("NUMTYPE");
						String ORIMSNO = map.get("ORIMSNO");
						String MODIFIER = map.get("MODIFIER");
					if(start==end){
					   newList.add(map);				
					  }
					else{
					   int count=Integer.parseInt(end)-Integer.parseInt(start);
					   int StartNo=Integer.parseInt(start);
					   int EndNo=Integer.parseInt(end);
					   for(int i=0;i<=count;i++){
						   Map<String,String> newMap = new HashMap<String, String>();
						   int Startnum=StartNo+i;
						    newMap.put("STARTNO", String.valueOf(Startnum));
							newMap.put("ENDNO", String.valueOf(Startnum));
							newMap.put("AREACODE", map.get("AREACODE"));
							newMap.put("PRINCENO", map.get("PRINCENO"));
							newMap.put("NUMTYPE", map.get("NUMTYPE"));
							newMap.put("ORIMSNO", map.get("ORIMSNO"));
							newMap.put("MODIFIER", map.get("MODIFIER"));
							newList.add(newMap);
					   } 
					
					}
				}
				
			}else{
				System.out.println(start+"--------------------------------");
			}
			
		}
		
		return newList;
	}

}
