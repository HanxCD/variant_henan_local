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
package com.boco.soap.variant.henan.local.shortnumber.mscserver.hw;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * 短号码 mscserver 变换后的短号码（ND）
 * 
 * @author lijixin
 * @Email lijixin2014@boco.com.cn
 * 
 */
public class HW_MSS_DNC_ND extends VariantValueInvoke {

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {

		Object o = data.get("START_GT");
		Object oBack = data.get("BACK_GT");
		Object oAreaCode = data.get("AREA_CODE");
		String areaCode = oAreaCode != null ? oAreaCode.toString() : "";
		String result = null;
		if (null == o) {
			result = "[NULL]";
		} else {
			String back = oBack != null ? oBack.toString() : "";
			if (back.contains("区号")) {
				result = o.toString() + areaCode;
			} else {
				result = o.toString() + back;
			}
		}
		return new String[] { result };

	}
}