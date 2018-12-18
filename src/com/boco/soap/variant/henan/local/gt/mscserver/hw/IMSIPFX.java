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
import com.boco.soap.variant.common.NumplanConvert;

/**
 * 
 * 
 * 
 *
 */
public class IMSIPFX extends VariantValueInvoke{

	/* (non-Javadoc)
	 * @see com.boco.soap.check.standvalue.valueinvoke.IValueInvoke#getValues(com.boco.soap.common.pojo.INeElement, com.boco.soap.common.pojo.solution.IInstructionParameter, java.util.Map, java.lang.String)
	 */
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {
		Object o = data.get("E164");
		String result = "[NULL]";
		if(null != o){
			result = NumplanConvert.E164ToE212(o.toString());
			System.out.println("----------------------------");
			System.out.println("----------result:"+result+"---------------");
			System.out.println("----------------------------");
		}
		return new String[]{result};
	}

}
