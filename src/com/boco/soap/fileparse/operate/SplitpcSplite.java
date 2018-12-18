package com.boco.soap.fileparse.operate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SplitpcSplite implements ISpliteOperate {

	public List<Map<String, String>> operate(List<Map<String, String>> oldList) {
		List<Map<String, String>> newList = new ArrayList<Map<String, String>>();
		for (Map<String, String> map : oldList) {
			String num = (String) map.get("SPLITPC");
			
			if ((num != null) && (!num.equals(""))) {
				String[] arrSpc = num.trim().split("\\s+|,|，|.|、");
				String spc = "";
				for (int i = 0; i < arrSpc.length; i++) {
					int spcNum = Integer.valueOf(arrSpc[i]);
					spc += Integer.toHexString(spcNum).toUpperCase();
				}
				map.put("SPLITPC", spc);
				newList.add(map);
			}
		}
		return newList;
	}
}