package com.boco.soap.fileparse.operate;

/**
 * scp startno 和 endno 拆分
 * @author guyufei
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HwScpKvpnSplite implements ISpliteOperate {

	@Override
	public List<Map<String, String>> operate(List<Map<String, String>> oldList) {

		List<Map<String, String>> newList = new ArrayList<Map<String, String>>();
		for (Map<String, String> map : oldList) {
			String start = map.get("STARTNO");
			String end = map.get("ENDNO");
			System.out.print(start);
			if(start.startsWith("1"))
			{
				start = map.get("STARTNO").substring(0,map.get("STARTNO").length()-4);
				end = map.get("ENDNO").substring(0,map.get("ENDNO").length()-4);
				
			}
//			else
//			{
//				start = map.get("STARTNO").substring(0,map.get("STARTNO").length()-3);
//				end = map.get("ENDNO").substring(0,map.get("ENDNO").length()-3);
//								
//			}
			String scpno = map.get("SCPNO");
			String provno = map.get("PROVNO");
			String areano = map.get("AREANO");
			String areacode = map.get("AREACODE");
			String isdntype = map.get("ISDNTYPE");
			String accessscpno = map.get("ACCESSSCPNO");
			String scpgrpno = map.get("SCPGRPNO");
			String pbxtype = map.get("PBXTYPE");
			String ifims = map.get("IFIMS");
			String brandid = map.get("BRANDID");
			String ifcanaddtokvpn = map.get("IFCANADDTOKVPN");
			String ifcanaddtofvpn = map.get("IFCANADDTOFVPN");
			String ifngin = map.get("IFNGIN");
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
					
					newMap.put("STARTNO", startno);
					newMap.put("ENDNO", endno);
					newMap.put("SCPNO", scpno);
					newMap.put("PROVNO", provno);
					newMap.put("AREANO", areano);
					newMap.put("AREACODE", areacode);
					newMap.put("ISDNTYPE", isdntype);
					newMap.put("ACCESSSCPNO", accessscpno);
					newMap.put("SCPGRPNO", scpgrpno);
					newMap.put("PBXTYPE", pbxtype);
					newMap.put("IFIMS", ifims);
					newMap.put("BRANDID", brandid);
					newMap.put("IFCANADDTOKVPN", ifcanaddtokvpn);
					newMap.put("IFCANADDTOFVPN", ifcanaddtofvpn);
					newMap.put("IFNGIN", ifngin);
					newList.add(newMap);
				}
			}
		
		}

		return newList;
	}
}
