package com.boco.soap.variant.henan.local.gt.lstp.bell;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;
import com.boco.soap.variant.common.NumplanConvert;

public class E214 extends VariantValueInvoke {

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {
		Object o = data.get("E164");
		String result = "[NULL]";
		if (null != o) {
			result = NumplanConvert.E164ToE214(o.toString());
		}
		return new String[] { result };
	}
}
