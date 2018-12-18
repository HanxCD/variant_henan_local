/*
 * Copyright 2014 BOCO Inter-Telecom (xi'an).
 * All rights reserved. 
 * project name: variant_sichuan
 * version V1.0
 * -------------------------------------------
 * author: lijixin
 * date: 2014-11-3
 * note: 
 */
package com.boco.soap.variant.henan.local.gt.hstp.hw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class DX extends VariantValueInvoke {
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
		if (map == null) {
			map = new HashMap<String, String>();
			this.initMap(dbFile);
		}
		String spcStr = data.get("E164").toString();
		if (null != spcStr) {
			String DXStr = map.get(spcStr + "," + arg0.getName());
			if (DXStr != null) {
				return new String[] { DXStr };
			} else {
				return new String[] { "[NULL]" };
			}
		} else {
			return new String[] { "[NULL]" };
		}
	}

	/**
	 * @param name
	 * @param spcStr
	 * @param dbFile
	 * @return
	 */
	// private String getDX(String neName, String spcStr, String dbFile) {
	// DataQueryUtils utils = DataQueryUtils.getInstance();
	//
	// String sql = "select DEST_SPC_IDX from HW_STP_N7DSP where  DEVICENAME='"
	// + neName + "' and INTERNAL_NW_CODE = '" + spcStr + "'";
	//
	// List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
	//
	// if (resultList.size() != 0) {
	//
	// return (String) resultList.get(0).get("DEST_SPC_IDX");
	//
	// } else {
	// return "[NULL]";
	// }
	//
	// }
	private void initMap(String dbFile) {
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "select DEVICENAME,INTERNAL_NW_CODE,DEST_SPC_IDX from HW_STP_N7DSP";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

		for (Map<String, ?> temp : resultList) {
			map.put(temp.get("DEVICENAME").toString() + ","
					+ temp.get("INTERNAL_NW_CODE").toString(), temp.get(
					"DEST_SPC_IDX").toString());

		}
	}
}
