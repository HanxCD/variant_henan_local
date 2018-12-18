package com.boco.soap.variant.henan.local.cs.cnacld.gmsc.huawei;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class CSA extends VariantValueInvoke {

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> arg2, String arg3) {
		Object o = arg2.get("GT_TYPE");
		if (null != o) {
			return new String[] { o.toString().equals("铁通固网")?"MLC":"LC" };
		} else {
			return new String[] { "[NULL]" };
		}
	}
}
