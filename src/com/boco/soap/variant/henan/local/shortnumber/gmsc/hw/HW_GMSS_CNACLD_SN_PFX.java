package com.boco.soap.variant.henan.local.shortnumber.gmsc.hw;

import java.util.Map;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * 短号码 gmsc 呼叫字冠（PFX）
 * 
 * @author changquanzhou
 * 
 */
public class HW_GMSS_CNACLD_SN_PFX extends VariantValueInvoke {
	@Override
	public String[] getValues(INeElement ne, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {
		Object o = data.get("START_GT");
		String result = null;
		if (null == o) {
			result = "[NULL]";
		} else {
			result = o.toString();
			if(result.startsWith("037")||result.startsWith("039"))
			{
				return null;
			}
		}
		
		return new String[] { result };

	}

}