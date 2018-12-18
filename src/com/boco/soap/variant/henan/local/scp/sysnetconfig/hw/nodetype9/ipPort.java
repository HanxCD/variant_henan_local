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
package com.boco.soap.variant.henan.local.scp.sysnetconfig.hw.nodetype9;

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
public class ipPort extends VariantValueInvoke {
	private Map<String, String> map = null;
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {
		
		Object mscId = data.get("MSCID");	

		if (map == null) {
			map = new HashMap<String, String>();
			this.initMap(dbFile);
		}
		String ipPort = map.get(mscId.toString().trim());
		if (ipPort == null)
		{
			return new String[]{"TCM_SYS_NETCONFIG表中没有该MSCID"};
		}
		else
		{			
			return new String[] { ipPort.toString().trim() };
		}
	}
	private void initMap(String dbFile) {
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "select MSCID,SCDUIP_PORT from TCM_SYS_NETCONFIG; ";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

		for (Map<String, ?> temp : resultList) {
			
			map.put(temp.get("MSCID").toString(), temp.get("SCDUIP_PORT").toString());
			
		}
	}
}