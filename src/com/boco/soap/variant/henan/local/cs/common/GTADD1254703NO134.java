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
public class GTADD1254703NO134 extends VariantValueInvoke {

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {

		Object o = data.get("START_GT");
		String result = null;
		if (null == o) {
			result = "[NULL]";
		} else {
			result ="1254703"+o.toString().trim();

		}
		if(o.toString().trim().equals("134"))
			return "12547031340,12547031341,12547031342,12547031343,12547031344,12547031345,12547031346,12547031347,12547031348".split(",");
		else
		return new String[] { result };

	}
}