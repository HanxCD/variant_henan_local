package com.boco.soap.variant.henan.local.lte.imsi.mme.ericsson;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class IDENTIFIERS extends VariantValueInvoke{


	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {
		
		
		Object objNeType = data.get("NE_TYPE");
		Object objActTbName = data.get("ACT_TBNAME");
		Object paraName = data.get("PARAM_NAME");
		String result = null;
	
		if (objNeType.toString().trim().toUpperCase().equals("MME")
			&& objActTbName.toString().trim().toUpperCase().equals("CONFIGURE")
			&& paraName!=null) {
	
			result = paraName.toString().trim();
			return new String[] { result };
		}
			return null;
	}
}
