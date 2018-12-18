/*
 * Copyright 2014 BOCO Inter-Telecom (xi'an).
 * All rights reserved. 
 * project name: variant_sichuan
 * version V1.0
 * -------------------------------------------
 * author: lijixin
 * date: 2014-11-3
 * note: 
 */
package com.boco.soap.variant.henan.local.gt.lstp.hw;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class RT extends VariantValueInvoke {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.boco.soap.check.standvalue.valueinvoke.IValueInvoke#getValues(com
	 * .boco.soap.common.pojo.INeElement,
	 * com.boco.soap.common.pojo.solution.IInstructionParameter, java.util.Map,
	 * java.lang.String)
	 */
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {
		String result="";
		String hlrName = data.get("HLR_NAME").toString();
		Pattern p=Pattern.compile("^\\s*(\\w+)[\\s+|,|，|、](\\w+)"); 
		Matcher m = p.matcher(hlrName);  
		if(m.find() || (hlrName.indexOf("BNK")>0)){
			result="STP1";
		}else if(hlrName.indexOf("BHW")>0){
			result="LSPC2";
		}else{
			result="[NULL]";
		}

		return new String[]{result};
	}

}
