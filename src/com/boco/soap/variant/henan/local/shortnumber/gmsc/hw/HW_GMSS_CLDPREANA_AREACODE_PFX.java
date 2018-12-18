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

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * 短号码 gmsc 带区号的呼叫字冠
 * 
 */
public class HW_GMSS_CLDPREANA_AREACODE_PFX extends VariantValueInvoke {

	@Override
	public String[] getValues(INeElement ne, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {
		
		Object o = data.get("START_GT");
		String result = null;
		if(null == o){
			result = "[NULL]";
		} else {
			result = o.toString();
			if(!(result.startsWith("037")||result.startsWith("039")))
			{
				return null;
			}
		}
		Object oCallType = data.get("CALL_TYPE");// 如果拨号方式为 直拨+接入地区号
		if (oCallType != null) {
			String callType = oCallType.toString();
			Object accessArea = data.get("ACCESS_AREA");
			Object area = data.get("BUSI_CITY");
			if (callType.contains("接入地区号")) {
				if (accessArea != null && area != null) {
					if (!accessArea.toString().equals(area.toString())) {
						return null;
					}
				}
			}
		}
		return new String[]{result};
		
	}
}