/*
 * Copyright 2014 BOCO Inter-Telecom (xi'an).
 * All rights reserved. 
 * project name: variant_sichuan
 * version V1.0
 * -------------------------------------------
 * author: caoran
 * date: 2018-07-05
 * note: 华为SBC短号码放通区号+裸号
 */
package com.boco.soap.variant.henan.local.ims.sbc.hw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * 短号码 华为SBC 带区号的呼叫字冠
 * 
 */
public class IMS_HWSBC_AREACODEPFX extends VariantValueInvoke {

	private Map<String, String> map = null;

	@Override
	public String[] getValues(INeElement ne, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {

		Object o = data.get("START_GT");
		Object areaCodeObj = data.get("AREA_CODE");
		String result = null;
		if (null == o) {
			result = "[NULL]";
		} else {
			if (this.map == null) {
				this.map = new HashMap<String, String>();
				this.initMap(dbFile);
			}
			if (areaCodeObj!=null) {
				result = o.toString()+areaCodeObj.toString();
			}else if(map.containsKey(String.valueOf(data.get("BUSI_CITY")))){
				result = "0"+map.get(String.valueOf(data.get("BUSI_CITY")))+o.toString();
			}
		}

		return new String[] { result };

	}

	private void initMap(String dbFile) {
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "select BUSI_CITY,CMCC_AREA from AREA_CODE_LIST";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

		for (Map<String, ?> temp : resultList) {
			this.map.put(temp.get("BUSI_CITY").toString(), temp.get("CMCC_AREA").toString());

		}
	}
}