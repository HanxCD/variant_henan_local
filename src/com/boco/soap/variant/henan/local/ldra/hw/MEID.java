package com.boco.soap.variant.henan.local.ldra.hw;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class MEID extends VariantValueInvoke {

	/**
	 * @author Sha Jianwen
	 * @time 2016-3-8 09:24:03
	 * MEID: USE ME:MEID=160/161 ZZDRA01BHW 160;ZZDRA02BHW 161;
	 * 切换网元视图的网元级别的头指令
	 */
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {

		Object o = arg0.getName();
		String result = null;
		if (null == o) {
			result = "NULL";
		} else {
			result = o.toString().toUpperCase().trim();
			if (result.contains("ZZDRA01BHW") || result.contains("ZZDRA01AHW-A")) {
				result = "160";
			} else if (result.contains("ZZDRA02BHW")) {
				result = "161";
			}
		}
		return new String[] { result };

	}
}
