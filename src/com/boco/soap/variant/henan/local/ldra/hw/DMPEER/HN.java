package com.boco.soap.variant.henan.local.ldra.hw.DMPEER;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class HN extends VariantValueInvoke {

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {
		/**
		 * @author Sha Jianwen
		 * @time:2015-11-17 10:30:30
		 * 主机名,该主机名在全局范围内唯一
		 */
		Object o = data.get("HN");
		String result = null;
		if (null == o) {
			result = "NULL";
		} else {
			result = o.toString().trim();
		}

		return new String[] { result };

	}
}
