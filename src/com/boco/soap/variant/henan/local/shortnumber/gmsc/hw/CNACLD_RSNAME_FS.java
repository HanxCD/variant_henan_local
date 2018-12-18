/*
 * Copyright 2014 BOCO Inter-Telecom (xi'an).
 * All rights reserved. 
 * project name: variant_sichuan
 * version V1.0
 * -------------------------------------------
 * author: wanghao
 * date: 2017-12-28
 * note: 华为关口局本省分散接入方式一+方式五开放外呼根据路由选择取值
 */
package com.boco.soap.variant.henan.local.shortnumber.gmsc.hw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;


public class CNACLD_RSNAME_FS extends VariantValueInvoke {

	private Map<String, String> map= new HashMap<String, String>();
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {
		
		Object o = data.get("ROUTE_NAME");
		
		String result = null;
		if(null == o){
			result = "[NULL]";
		} else {
			if(o.toString().equals("电信"))
			result = "DX";
			if(o.toString().equals("网通")||o.toString().equals("联通"))
				result = "WT";
			if(o.toString().equals("铁通"))
				result = "TT";
		}
		
		return new String[]{result};
	}
}