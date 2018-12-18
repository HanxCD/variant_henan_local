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
package com.boco.soap.variant.henan.local.scp.mvpn.hw;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * 
 */
public class serviceNo extends VariantValueInvoke {

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {
		//业务区号为固定格式：130+3位区号+01
		Object o = data.get("AREA_CODE");

		String result = null;
		if(null == o){
			result = "[NULL]";
		} else {
			result ="130"+o.toString().trim()+"01";
				
		}
		
		return new String[]{result};
	}	
}