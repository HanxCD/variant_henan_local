package com.boco.soap.variant.henan.local.cs.common;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * ��Ϊ�˾ֺ����ֹ�
 * 
 * @author baixp
 * @Email
 * 
 */
public class GTADD86NO134 extends VariantValueInvoke {

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {

		Object o = data.get("START_GT");
		
		String result = null;
		if (null == o) {
			result = "[NULL]";
		} else {
			result ="86"+o.toString().trim();

		}
		if(o.toString().trim().equals("134"))
			return "861340,861341,861342,861343,861344,861345,861346,861347,861348".split(",");
		else
		return new String[] { result };

	}
}