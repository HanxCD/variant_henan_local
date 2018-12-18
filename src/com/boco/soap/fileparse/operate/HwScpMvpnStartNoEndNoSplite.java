package com.boco.soap.fileparse.operate;

/**
 * scp startno 和 endno 拆分
 * @author guyufei
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HwScpMvpnStartNoEndNoSplite implements ISpliteOperate {

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
		
			String isdntype = map.get("ISDNTYPE");
			String totherisdntype = map.get("TOTHERISDNTYPE");
			String ifallowaddtogrp = map.get("IFALLOWADDTOGRP");
			String scpno = map.get("SCPNO");
			String serviceno = map.get("SERVICENO");
			String ifallowaddtoteam = map.get("IFALLOWADDTOTEAM");
			String scpgrpno = map.get("SCPGRPNO");
			String accessscpno = map.get("ACCESSSCPNO");
			String ifhavetrunk = map.get("IFHAVETRUNK");
			String ifims = map.get("IFIMS");
			String ifrailcom = map.get("IFRAILCOM");
			String pbxconnectpref = map.get("PBXCONNECTPREF");
			String ifngin = map.get("IFNGIN");
			String nginscpno = map.get("NGINSCPNO");
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
					newMap.put("ISDNTYPE", isdntype);
					newMap.put("TOTHERISDNTYPE", totherisdntype);
					newMap.put("IFALLOWADDTOGRP", ifallowaddtogrp);
					newMap.put("SCPNO", scpno);
					newMap.put("SERVICENO", serviceno);
					newMap.put("IFALLOWADDTOTEAM", ifallowaddtoteam);
					newMap.put("SCPGRPNO", scpgrpno);
					newMap.put("ACCESSSCPNO", accessscpno);
					newMap.put("IFHAVETRUNK", ifhavetrunk);
					newMap.put("IFIMS", ifims);
					newMap.put("IFRAILCOM", ifrailcom);
					newMap.put("PBXCONNECTPREF", pbxconnectpref);
					newMap.put("IFNGIN", ifngin);
					newMap.put("NGINSCPNO", nginscpno);
					newList.add(newMap);
				}
			}
		
		}

		return newList;
	}
}
