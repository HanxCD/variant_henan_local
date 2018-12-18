package com.boco.soap.variant.henan.local.cs.common;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * 
 * 
 * @author wanghao
 * @Email
 * 
 */

public class GTNK1724 extends VariantValueInvoke {
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {

		Object o = data.get("START_GT");
		String result = null;
		if (null == o) {
			result = "[NULL]";
		} else {
			result = o.toString().trim();
		}
		if (o.toString().equals("172")) {
			return new String[] { "1724" };
		}
		;

		return new String[] { result };

	}

}