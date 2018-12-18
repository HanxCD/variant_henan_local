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
 * 短号码 gmsc 呼叫字冠（PFX）
 * 
 */
public class HW_GMSS_CLDPREANA_PFX extends VariantValueInvoke {

	@Override
	public String[] getValues(INeElement ne, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {
		
		Object o = data.get("START_GT");
		String result = null;
		if(null == o){
			result = "[NULL]";
		} else {
			result = o.toString();
			 System.out.println("PFX  "+result+"-------------------");
			if(result.startsWith("037")||result.startsWith("039"))
			{
				return null;
			}
			 System.out.println("PFX 之後 "+result+"-------------------");
		}
		return new String[]{result};
		
	}
}