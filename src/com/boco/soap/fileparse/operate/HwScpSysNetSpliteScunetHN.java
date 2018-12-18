package com.boco.soap.fileparse.operate;

/**
 * 吉林的
 * @author guyufei
 */

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HwScpSysNetSpliteScunetHN implements ISpliteOperate {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private static final String reg_start_gt = "\\s*001204(\\d+)0000000\\s*";
	String start_gt = "";
	@Override
	public List<Map<String, String>> operate(List<Map<String, String>> oldList) {
		if (log.isDebugEnabled()) {
			log.debug("正则匹配到的数据：" + oldList.toString());
		}

		for (Map<String, String> map : oldList) {
				String address = map.get("GT").trim();	
				Pattern pGt = Pattern.compile(reg_start_gt);
				Matcher matGt9 = pGt.matcher(address);
				
				while (matGt9.find()) {
					start_gt = matGt9.group(1);
					String[] temp = new String[10];
					int i = 0;
					while(i < 10){
						temp[i] = start_gt.substring(i,i+1);
						i++;
					}
					start_gt = temp[1] + temp[0] + temp[3] + temp[2] + temp[5] + temp[4] + temp[7] + temp[6] + temp[9] + temp[8];
					map.put("GT", start_gt);
				}
		}

		return oldList;
	}
}

