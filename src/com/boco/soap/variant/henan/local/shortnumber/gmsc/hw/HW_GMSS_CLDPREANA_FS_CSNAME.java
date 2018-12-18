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
package com.boco.soap.variant.henan.local.shortnumber.gmsc.hw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;


public class HW_GMSS_CLDPREANA_FS_CSNAME extends VariantValueInvoke {

	private Map<String, String> map= new HashMap<String, String>();
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {
		initMap(dbFile);
		Object o = data.get("ROUTE_NAME");
		Object sCity = data.get("BUSI_CITY");
		
		String result = null;
		if(null == o){
			result = "[NULL]";
		} else {
			if(o.toString().equals("电信"))
			result =map.get(sCity)+"DX";
			if(o.toString().equals("网通")||o.toString().equals("联通"))
				result =map.get(sCity)+"WT";
			if(o.toString().equals("铁通"))
				result =map.get(sCity)+"TT";
		}
		
		return new String[]{result};
		
	}
	
	private void initMap(String dbFile) {
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "select BUSI_CITY,SIMPLENAME from TCM_LOCAL_CITY_LIST";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

		for (Map<String, ?> temp : resultList) {
			map.put(temp.get("BUSI_CITY").toString(), temp.get("SIMPLENAME")
					.toString());

		}
	}
}