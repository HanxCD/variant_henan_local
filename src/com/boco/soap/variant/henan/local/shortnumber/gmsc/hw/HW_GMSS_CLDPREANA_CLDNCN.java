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

import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * HW_GMSS_CLDPREANA_CLDNCN
 * 
 */
public class HW_GMSS_CLDPREANA_CLDNCN extends VariantValueInvoke {

	@Override
	public String[] getValues(INeElement ne, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {

		String neName = ne.getName();
		String dcn = this.getDcn(neName, dbFile);
		return new String[] { dcn };

	}

	private String getDcn(String neName, String dbFile) {
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "SELECT MAX(DCN)+1 AS DCN FROM HW_GMSS_DNC WHERE DEVICENAME= '"
				+ neName + "'";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
		String dcn = "";
		if (resultList != null && resultList.size() > 0) {
			dcn = resultList.get(0).get("DCN").toString().trim();
		}
		return dcn;
	}
}