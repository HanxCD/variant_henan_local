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
package com.boco.soap.variant.henan.local.gt.gmsc.hw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;
import com.boco.soap.variant.common.SPC16;

/**
 * 
 * @author caozengran 
 *
 */
public class SPC extends VariantValueInvoke{
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
		Object o = data.get("PROVINCE");
		if (map == null) {
			map = new HashMap<String, String>();
			this.initMap(dbFile);
		}
		if (o != null) {
			String[] spcArr = map.get(o.toString()).split(",");
			String spcStr="";
			for (String spcTmp : spcArr) {
				if (!spcTmp.equals("") && spcTmp != null) {
					spcStr=spcStr+SPC16.spc10to16(spcTmp)+"|";
				} 
			}
			spcStr=spcStr.substring(0,spcStr.length()-1);
			return new String[]{spcStr};
			
		} else {

			return new String[] { "[NULL]" };
		}

	}

	private void initMap(String dbFile) {
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "select PROVINCE,GROUP_CONCAT(SPC) SPC from TCM_ALL_LSTPHSTP t WHERE  (t.BUREAU_DATA_NO  LIKE '%L1' OR t.BUREAU_DATA_NO  LIKE '%L2'OR t.BUREAU_DATA_NO  LIKE '%L3' OR t.BUREAU_DATA_NO  LIKE '%L4') group by PROVINCE  ";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

		for (Map<String, ?> temp : resultList) {
			map.put(temp.get("PROVINCE").toString(), temp.get("SPC").toString());
		}
	}

}
