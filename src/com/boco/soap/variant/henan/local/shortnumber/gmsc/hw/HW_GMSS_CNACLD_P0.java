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
package com.boco.soap.variant.henan.local.shortnumber.gmsc.hw;

import java.util.HashMap;
import java.util.Map;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * 
 * @author Chang
 * @description 短号码 gmsc 号首（P）根据网元的奇偶去设置P的值
 */
public class HW_GMSS_CNACLD_P0 extends VariantValueInvoke {

	private Map<String, String> map=null;//设备覆盖地市map
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {
		if (map == null) {
			map = new HashMap<String, String>();
		}
		String sNetName = arg0.getName();
		String p = this.getP(sNetName);
		return new String[] { p };

	}
	

	private String getP(String sNetName) {
		String num = sNetName.split("ZZDS")[1];
		String p = "";
		int lastNumber = Integer.parseInt(num);
		int res = lastNumber % 2;
		p = (res == 1 ? "0" : "1");
		return p;
	}
}