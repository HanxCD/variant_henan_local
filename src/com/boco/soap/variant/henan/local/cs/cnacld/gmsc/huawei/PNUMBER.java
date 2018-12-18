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
package com.boco.soap.variant.henan.local.cs.cnacld.gmsc.huawei;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class PNUMBER extends VariantValueInvoke {

	private Map<String, String> map = null;

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {
		Object o = data.get("BUSI_CITY");
		if (map == null) {
			map = new HashMap<String, String>();
			this.initMap(dbFile);
		}
		String sCity = null;
		if (null == o) {
			sCity = "[NULL]";
		} else {
			sCity = o.toString();
		}
		String sNetName = arg0.getName();
		int lastNumber = Integer.parseInt(String.valueOf(sNetName
				.charAt(sNetName.length() - 1)));

		int res = lastNumber % 2;
		String result = "[NULL]";
		String coverCity = map.get(sNetName);
		if (coverCity == null) {
			return new String[] { "[华为关口局主备关系映射表中找不到对应网元数据]" };
		}
		if (coverCity.indexOf(sCity) >= 0) {
			result = (res == 1 ? "0" : "1");
		} else {
			result = (res == 1 ? "1" : "0");
		}
		return new String[] { result };

	}

	private void initMap(String dbFile) {
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "select COVER_CITY,DEVICENAME from TCM_HW_GMSS_MTB_REL";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

		for (Map<String, ?> temp : resultList) {
			map.put(temp.get("DEVICENAME").toString(), temp.get("COVER_CITY")
					.toString());

		}
	}

}