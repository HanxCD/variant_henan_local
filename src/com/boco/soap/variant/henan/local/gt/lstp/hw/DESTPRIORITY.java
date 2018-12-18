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

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * gt LSTP 华为   sccpgt 目的信令点索引
 * @author lijixin 
 * @Email lijixin2014@boco.com.cn
 *
 */
public class DESTPRIORITY extends VariantValueInvoke{

	/* (non-Javadoc)
	 * @see com.boco.soap.check.standvalue.valueinvoke.IValueInvoke#getValues(com.boco.soap.common.pojo.INeElement, com.boco.soap.common.pojo.solution.IInstructionParameter, java.util.Map, java.lang.String)
	 */
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {
		String hlrName = data.get("HLR_NAME").toString();
		String priority="";
		//华为HSS主用
		if(hlrName.indexOf("FE01")>0||hlrName.indexOf("FE02")>0){
			priority="1,0";
		}else if(hlrName.indexOf("FE03")>0||hlrName.indexOf("FE10")>0){
			priority="0,1";
		}
		return new String[]{priority};
	}

}
