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
package com.boco.soap.variant.henan.local.gt.mscserver.hw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * 
 * 
 * 
 *
 */
public class isIotFlag extends VariantValueInvoke {
	private Map<String, String> map = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.boco.soap.check.standvalue.valueinvoke.IValueInvoke#getValues(com
	 * .boco.soap.common.pojo.INeElement,
	 * com.boco.soap.common.pojo.solution.IInstructionParameter, java.util.Map,
	 * java.lang.String)
	 */
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {
		Object o = data.get("E164");
		if (o == null) {
			return new String[] { "[NULL]" };
		}
		String e214 = o.toString().trim();
		if (e214.startsWith("86")) {
			e214 = e214.substring(2);
		}
		if (map == null) {
			map = new HashMap<String, String>();
			this.initMap(dbFile);
		}
		for (String keyStr : map.keySet()) {
			String gt = map.get(keyStr);
			if (gt.startsWith("86")) {
				gt = gt.substring(2);
			}
			if (e214.startsWith(gt)) {
				return null;
			}

		}
		return new String[] { e214 };
	}

	private void initMap(String dbFile) {
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "select GT from TCM_IOT_GT ";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
		int i = 0;
		for (Map<String, ?> temp : resultList) {
			map.put(i + "", temp.get("GT").toString().trim());
			i++;
		}
	}

}
