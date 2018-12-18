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
package com.boco.soap.variant.henan.local.scp.kvpn.hw;

import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * guyufei
 */
public class startGt extends VariantValueInvoke {

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {
		
		Object o = data.get("START_GT");
		//Object pro = data.get("PROVINCE");
		Object areaCode = data.get("AREA_CODE");
		String result = "[NULL]";
		String gt[] = null;
		if(null == o){
			result = "[NULL]";
		} else {
			gt = this.getGt(areaCode.toString().trim(),o.toString().trim(),dbFile);
			for(int i=0;i<gt.length;i++)
			{
				if(o.toString().substring(0, 3).equals((gt[i])))
				{
					result =o.toString().trim();
					break;
				}
				else
				{
					result = o.toString().trim()+"(该号码不在有效号段内)";
				}
			}
		}		
		return new String[]{result};
	}
	private String[] getGt(String areaCode,String startGt, String dbFile) {
		DataQueryUtils utils = DataQueryUtils.getInstance();

		String sql = "select GT from TCM_VPMN_SCP where AREA_CODE='"+areaCode+"';";
	
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
		String[] result = new String[resultList.size()];
		if (resultList.size() != 0) {
			for(int i = 0 ; i < resultList.size() ;i++){
				result[i] = resultList.get(i).get("GT").toString();
			}
			return result;

		} else {
		
			return new String[] { "[NULL]" };
		}
	}
}