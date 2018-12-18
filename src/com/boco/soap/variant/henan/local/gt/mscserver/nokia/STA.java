
package com.boco.soap.variant.henan.local.gt.mscserver.nokia;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class STA extends VariantValueInvoke
{
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {
		String stateTemp = data.get("STATE")==null?"":data.get("STATE").toString();
		String stateStr = stateTemp.replaceAll(",|\\s+|、|，", ",");
		return new String[] { stateStr };
	}

}