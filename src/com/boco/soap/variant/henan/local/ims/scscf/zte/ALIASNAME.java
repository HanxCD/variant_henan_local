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
package com.boco.soap.variant.henan.local.ims.scscf.zte;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class ALIASNAME extends VariantValueInvoke {
	
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {
		
		String descrStr = data.get("PROVINCE").toString();
		if(data.get("NAME")!=null){
			String nameStr=data.get("NAME").toString();
			Pattern p = Pattern.compile("scscf(\\S*?)\\.");
			Matcher m = p.matcher(nameStr);
			if (m.find()) {
				descrStr=descrStr+m.group(1).toUpperCase();
			}
		}
		return new String[]{descrStr};
	}

}