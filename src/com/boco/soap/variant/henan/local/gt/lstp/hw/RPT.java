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
package com.boco.soap.variant.henan.local.gt.lstp.hw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class RPT extends VariantValueInvoke {

	private Map<String, String> map = new HashMap<String, String>();
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
		String o = data.get("HLR_NAME").toString();
		String result = "";
		if (null == o) {
			result = "[NULL]";
		} else {
			String hlrName = o.toString();
			Pattern p = Pattern.compile("^\\s*(\\w+)[\\s+|,|，|、]*");
			Matcher m = p.matcher(hlrName);
			if (m.find()){
				if(hlrName.indexOf("BNK") > 0) {
					result = getSpc(m.group(1), dbFile,arg0.getName());
				}else {
					result = m.group(1);
				}
			}
		}
		return new String[] { result };
	}
	
	/**
	 * @param name
	 * @param spcStr
	 * @param dbFile
	 * @return
	 */
	private String getSpc(String hlrName, String dbFile,String neName) {

		if (map == null || map.size() == 0) {
			this.initMap(dbFile);
		}
		String result = "[NULL]";
		for (String keyStr : map.keySet()) {
			String mainKey = keyStr.split("\\|")[0];
			String subKey = keyStr.split("\\|")[1];
			if (mainKey.equals(neName)&&subKey.indexOf(hlrName) >= 0) {
				result = map.get(keyStr);
				break;
			}
		}
		return result;
	}

	private void initMap(String dbFile) {
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "SELECT DEVICENAME, DEST0_NAME||','||DEST1_NAME||CASE DEST2_NAME  WHEN '<NULL>' THEN '' ELSE ','||DEST2_NAME END DEST_NAME,SCCP_STRATEGY_NAME FROM HW_STP_SCCPADDRPL ";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
		for (Map<String, ?> temp : resultList) {
			String deviceName = temp.get("DEVICENAME").toString();
			String destName = temp.get("DEST_NAME").toString();
			String strategyName = temp.get("SCCP_STRATEGY_NAME").toString();
			map.put(deviceName + "|" + destName, strategyName);

		}

	}
}
