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
package com.boco.soap.variant.henan.local.ims.sbc.zte;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class default_refresh_time extends VariantValueInvoke {

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {
		
		Object objNeType = data.get("NE_TYPE");
		Object paraName = data.get("PARAM_NAME");
		String result="";

		if (objNeType.toString().trim().toUpperCase().equals("SBC")
			&&paraName.toString().trim().toUpperCase().equals("SESSION TIMER缺省值")) {
			result = paraName.toString().trim();
			return new String[] { result };
		}
			return null;
	}
}