package com.boco.soap.variant.henan.local.gt.mscserver.nokia;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class ResultCode1 extends VariantValueInvoke {

	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {
		
		String resStr = data.get("GT_RESULT")==null?"":data.get("GT_RESULT").toString();
		return new String[] { resStr };
	}
}