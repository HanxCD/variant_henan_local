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
package com.boco.soap.variant.henan.local.gt.mscserver.hw;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * 
 * 
 * 
 *
 */
public class MSAREANAME extends VariantValueInvoke{

	/* (non-Javadoc)
	 * @see com.boco.soap.check.standvalue.valueinvoke.IValueInvoke#getValues(com.boco.soap.common.pojo.INeElement, com.boco.soap.common.pojo.solution.IInstructionParameter, java.util.Map, java.lang.String)
	 */
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {
		String neName = arg0.getName();
		String result = "[NULL]";
		if (!neName.equals("SMXGS102") &&!neName.equals("SHQGS102") && !neName.equals("SHQGS103")
				&& !neName.equals("ZZGS114") && !neName.equals("XIYGS102")) {
			result = "0";
		} else {
			result = neName;
		}
		return new String[]{result};
	}

}
