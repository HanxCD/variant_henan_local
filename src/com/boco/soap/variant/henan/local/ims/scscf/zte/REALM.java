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

public class REALM extends VariantValueInvoke {
	
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {
		
		String nameStr = data.get("NAME").toString();
		String realmStr="";
		Pattern p = Pattern.compile("\\w+.(\\S+)");
		Matcher m = p.matcher(nameStr);
		if (m.find()) {
			realmStr=m.group(1);
		}
		return new String[]{realmStr};
	}

}