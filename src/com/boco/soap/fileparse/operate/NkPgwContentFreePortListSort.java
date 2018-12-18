package com.boco.soap.fileparse.operate;

/**
 * 吉林的
 * @author guyufei
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class NkPgwContentFreePortListSort implements ISpliteOperate {
	
	public List<Map<String, String>> operate(List<Map<String, String>> oldList) {
		List<Map<String, String>> newList2 = new ArrayList<Map<String, String>>();
		for (Map<String, String> map : oldList) {
			String num = (String) map.get("PORT_LIST");
			
			if ((num != null) && (!num.equals(""))) {
				String[] arrSpc = num.trim().split("\\s+|,|，|\\.|、");
				Arrays.sort(arrSpc);
				map.put("SPLITPC", Arrays.toString(arrSpc).replaceAll("\\[|\\]", ""));
				newList2.add(map);
			}
		}
		return newList2;
	}
}

