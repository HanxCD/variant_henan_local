/*
 * Copyright 2014 BOCO Inter-Telecom (xi'an).
 * All rights reserved. 
 * project name: variant_sichuan
 * version V1.0
 * -------------------------------------------
 * author: lijixin
 * date: 2014-10-31
 * note: 
 */
package com.boco.soap.variant.henan.local.ims.sbc.zte;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class AREA_CODE extends VariantValueInvoke {

	
	private Map<String, String> map = null;
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {
		
		String busiCity = data.get("BUSI_CITY").toString();
		if (map == null) {
			map = new HashMap<String, String>();
			this.initMap(dbFile);
		}
		String result = null;
		if(map.containsKey(busiCity)){
			result = map.get(busiCity);
		} else {
			result = "[NULL]";
		}
		return new String[]{result};
	}
	

	private void initMap(String dbFile) {
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "SELECT BUSI_CITY,'0'||TEL_AREA  AREACODE FROM AREA_CODE_LIST WHERE PROVINCE='河南'";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
		for (Map<String, ?> temp : resultList) {
			String busi_city = temp.get("BUSI_CITY").toString();
			String area_code = temp.get("AREACODE").toString();
			map.put(busi_city, area_code);
		}
		if(!map.containsKey("济源")){
			map.put("济源",map.get("焦作").toString());
		}
		map.put("开封",map.get("郑州").toString());
	}
}