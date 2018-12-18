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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
public class LSDGNM extends VariantValueInvoke {
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
		Object cityObject = data.get("BUSI_CITY");
		if (cityObject == null)
			return new String[] { "[NULL]" };
		String city = cityObject.toString();
		String hlrArr="SMXGS102,XIYGS102,ZZGS111,ZZGS112,ZZGS113,ZZGS114";
		if (city.indexOf("商丘") < 0)
			if (hlrArr.indexOf(arg0.getName())>0) {
				return new String[] { "NULL" };
			} else {
				return new String[] { "null" };
			}
		if (map == null) {
			map = new HashMap<String, String>();
			this.initMap(dbFile);
		}
		Object o = data.get("HLR_DPC");
		String result = "[NULL]";
		String dpc = "";
		if (null == o) {
			result = "null";
		} else {
			dpc = o.toString();
			Pattern p = Pattern.compile("^\\s*(\\w+)[\\s+|,|，|、]");
			Matcher m = p.matcher(dpc);
			if (m.find()) {
				dpc = m.group(1);
			}
			String groupName = map.get(arg0.getName() + "," + dpc);
			if (groupName != null) {
				return new String[] { groupName };
			}else{
				return new String[] { "ZZHSS06" };
			} 
		}

		return new String[] { result };

	}

	private void initMap(String dbFile) {
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "select t.DEVICENAME,m.INTERNAL_NW_SPC,t.GROUPNAME from HW_MSS_SCCP_LDSHGRP t,HW_MSS_SCCPDSP m where (m.DEST_SPC_NAME=t.SPC or m.DEST_SPC_NAME=t.SPC1 or m.DEST_SPC_NAME=t.SPC2)";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

		for (Map<String, ?> temp : resultList) {
			map.put(temp.get("DEVICENAME").toString() + ","
					+ temp.get("INTERNAL_NW_SPC").toString(), temp.get(
					"GROUPNAME").toString());

		}
	}

}
