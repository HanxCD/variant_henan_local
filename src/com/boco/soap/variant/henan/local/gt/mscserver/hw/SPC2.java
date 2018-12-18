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
import com.boco.soap.variant.common.SPC16;

/**
 * gt mscServer�˾� ��Ϊ sccpgt SPC- 16����
 * 
 * @author lijixin
 * @Email lijixin2014@boco.com.cn
 * 
 */
public class SPC2 extends VariantValueInvoke {
	private Map<String, String> map=null;

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
		Object o = arg0.getPhysicalAddr();
		if (map == null) {
			map = new HashMap<String, String>();
			this.initMap(dbFile);
		}
		if (o != null) {
			String busiStr = o.toString();
			String result1 = null;
			if (map.get(busiStr) != null) {
				result1 = map.get(busiStr).toString();
			}
			if (result1 != null && !result1.equals("")) {
				return new String[] { result1 };
			} else {
				return new String[] { "[NULL]" };
			}
		} else {
			return new String[] { "[NULL]" };
		}

	}

	private void initMap(String dbFile) {
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "select COVER_CITY,STP1_SPC,STP2_SPC from TCM_LOCAL_LSTP";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

		for (Map<String, ?> temp : resultList) {
			map.put(temp.get("COVER_CITY").toString(), SPC16.spc10to16(temp
					.get("STP1_SPC").toString())
					+ "|" + SPC16.spc10to16(temp.get("STP2_SPC").toString()));

		}
	}

}
