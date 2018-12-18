package com.boco.soap.variant.henan.local.gt.lstp.bell;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class E164 extends VariantValueInvoke{

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> arg2, String arg3) {
		Object o = arg2.get("E164");
		if(null != o){
			return new String[]{o.toString()};
		} else {
			return new String[]{"[NULL]"};
		}
	}

}
