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

public class PARAM_VALUE extends VariantValueInvoke {

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {
		
		Object objNeType = data.get("NE_TYPE");
		Object paraValue = data.get("PARAM_VALUE");
		String result = "[NULL]";

		if (objNeType.toString().trim().toUpperCase().equals("SBC")&&paraValue!=null) {

			result = paraValue.toString().trim();
		}
		return new String[] { result };
	}
}