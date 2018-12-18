package com.boco.soap.fileparse.operate;

/**
 * scp startno 和 endno 拆分
 * @author guyufei
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HwScpCasMsisdntoidSplite implements ISpliteOperate {

	@Override
	public List<Map<String, String>> operate(List<Map<String, String>> oldList) {

		List<Map<String, String>> newList = new ArrayList<Map<String, String>>();
		for (Map<String, String> map : oldList) {
			String start = map.get("MSISDNSTART");
			String end = map.get("MSISDNSTOP");
			System.out.print(start);
			if(start.startsWith("1"))
			{
				start = map.get("MSISDNSTART").substring(0,map.get("MSISDNSTART").length()-4);
				end = map.get("MSISDNSTOP").substring(0,map.get("MSISDNSTOP").length()-4);
				
			}
//			else
//			{
//				start = map.get("STARTNO").substring(0,map.get("STARTNO").length()-3);
//				end = map.get("ENDNO").substring(0,map.get("ENDNO").length()-3);
//								
//			}
			String feId = map.get("SCPTOINGW_FEID");
			String nodeId = map.get("INGW_NODEID");

			if(start==end)
			{
				newList.add(map);				
			}
			else
			{

				int count=Integer.parseInt(end)-Integer.parseInt(start);
			
				for(int i=0;i<count+1;i++)
				{	
					Map<String,String> newMap = new HashMap<String, String>();
					Integer sno=Integer.parseInt(start)+i;
					Integer eno=Integer.parseInt(start)+i;

					String startno="";
					String endno="";
					if(start.startsWith("1"))
					{
						startno=sno.toString();
						endno=eno.toString();
//						startno=sno.toString() +"0000";
//						endno=eno.toString() +"9999";
						
					}
//					else 
//					{
//						startno=sno.toString() +"000";
//						endno=eno.toString() +"999";						
//					}
					
					newMap.put("MSISDNSTART", startno);
					newMap.put("MSISDNSTOP", endno);
					newMap.put("SCPTOINGW_FEID", feId);
					newMap.put("INGW_NODEID", nodeId);
					
					newList.add(newMap);
				}
			}
		
		}

		return newList;
	}
}
