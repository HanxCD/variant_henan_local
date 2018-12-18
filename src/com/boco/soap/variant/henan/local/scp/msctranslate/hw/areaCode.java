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
package com.boco.soap.variant.henan.local.scp.msctranslate.hw;

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
public class areaCode extends VariantValueInvoke {
	private Map<String, String> map = null;
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {
		
		Object mscId = data.get("MSCID");
		Object areaCode = data.get("AREA_CODE");

		if (map == null) {
			map = new HashMap<String, String>();
			this.initMap(dbFile);
		}
		String area = map.get(mscId.toString().trim());
		if (area == null)
		{
			return new String[] { areaCode.toString().trim() };
		}
		else
		{
			return new String[]{area};
		}
	}
	private void initMap(String dbFile) {
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "select MSCID,VIRTUAL_AREA_CODE from TCM_ALL_VIRTUAL_LIST; ";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

		for (Map<String, ?> temp : resultList) {
			if(temp.get("MSCID").toString().startsWith("86"))
			{
				map.put(temp.get("MSCID").toString().substring(2), temp.get("VIRTUAL_AREA_CODE").toString());
			}
			else
			{
				map.put(temp.get("MSCID").toString(), temp.get("VIRTUAL_AREA_CODE").toString());
			}
		}
	}
}