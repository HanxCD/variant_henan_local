package com.boco.soap.variant.henan.local.cs.cnacld.gmsc.huawei;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class MINL extends VariantValueInvoke {

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> arg2, String arg3) {
		Object o = arg2.get("BUSI_CITY");
		if (null != o) {
			if( o.toString().equals("郑州")|| o.toString().equals("洛阳")|| o.toString().equals("南阳"))
			return new String[] { "12" };
			else
				return new String[] { "11" };
		} else {
			return new String[] { "[NULL]" };
		}
	}
}
