package com.boco.soap.variant.henan.local.scp.msctranslate.hw;

/*
 * Copyright 2016 BOCO Inter-Telecom (he'nan).
 * All rights reserved.
 * version V1.0
 * -------------------------------------------
 * author: wanghao	
 * date: 2016-10-09
 * note:
 */

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class BUSI_CITY extends VariantValueInvoke{
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {
		
		Object o = data.get("BUSI_CITY");
		
		String result = null;
	
		if(null == o){
			result = "[NULL]";
		} else {			
					result =o.toString().trim();			
		}
		
		return new String[]{result};
	}	

}