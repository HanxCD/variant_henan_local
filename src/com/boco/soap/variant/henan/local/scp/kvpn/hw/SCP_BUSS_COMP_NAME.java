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

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * 
 */
public class SCP_BUSS_COMP_NAME extends VariantValueInvoke {

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {
		
		Object o = data.get("COMP_NAME");
       
		String result = null;
		if(null == o){
			result = "[NULL]";
		} else {
			if(o.toString().trim().equals("电信")){
				System.out.println(o.toString()+"=*********====");
				result="4";
			}else if(o.toString().trim().equals("联通")){
				result="2";
			}
		}
		
		return new String[]{result};
	}	
}