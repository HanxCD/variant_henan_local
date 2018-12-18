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
public class LSDGSPC extends VariantValueInvoke{

	/* (non-Javadoc)
	 * @see com.boco.soap.check.standvalue.valueinvoke.IValueInvoke#getValues(com.boco.soap.common.pojo.INeElement, com.boco.soap.common.pojo.solution.IInstructionParameter, java.util.Map, java.lang.String)
	 */
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {
		Object cityObject = data.get("BUSI_CITY");
		if (cityObject == null)
			return new String[] { "[NULL]" };
		String city = cityObject.toString();
		if (city.indexOf("商丘") < 0){
				return new String[] { "" };
		}else{
			return new String[] { "ZZHSS06"};
		}
		
	}

}
