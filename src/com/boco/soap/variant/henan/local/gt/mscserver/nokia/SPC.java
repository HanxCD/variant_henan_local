
package com.boco.soap.variant.henan.local.gt.mscserver.nokia;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class SPC extends VariantValueInvoke
{

	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {
		String spcStr = data.get("DPC")==null?"":data.get("DPC").toString();
		spcStr=spcStr.replaceAll(",|\\s+|、|，", ",");
		return new String[] { spcStr };
	}

}