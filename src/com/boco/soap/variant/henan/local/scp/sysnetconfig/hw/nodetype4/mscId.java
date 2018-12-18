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
package com.boco.soap.variant.henan.local.scp.sysnetconfig.hw.nodetype4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * guyufei
 */
public class mscId extends VariantValueInvoke {	
	private Map<String, String> map = null;
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {
		
		Object o = data.get("MSCID");
		Object pro = data.get("PROVINCE");
		String result = null;
		if (map == null) {
			map = new HashMap<String, String>();
			this.initMap(dbFile);
		}
		if(null == o)
		{
			result = "[NULL]";
		} 
		else 
		{			
			if (!map.containsKey(String.valueOf(pro))) {
				return null;
			}
			else
			{
				result ="86"+ o.toString().trim();				
			}
		}
		
		return new String[]{result};
	}
	
	private void initMap(String dbFile) {
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "select PROVINCE,IP_NODE from TCM_IP_PROVINCE";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

		for (Map<String, ?> temp : resultList) {
			map.put(temp.get("PROVINCE").toString().trim(), temp.get("IP_NODE").toString().trim());

		}
	}
}