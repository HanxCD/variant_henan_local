package com.boco.soap.variant.henan.local.cs.cnacld.mss.huawei;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;


public class PFX_1254708 extends VariantValueInvoke {

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {

		Object o = data.get("E164");
		String result = null;
		if (null == o) {
			result = "[NULL]";
		} else {
			String tel=o.toString();
			if (tel.startsWith("86"))
			{
				result = "1254708" + tel.substring(2);
			}else{
				result="1254708" + tel;
			}
		}

		return new String[] { result };

	}
}