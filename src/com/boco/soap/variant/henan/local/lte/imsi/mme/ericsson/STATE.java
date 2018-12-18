package com.boco.soap.variant.henan.local.lte.imsi.mme.ericsson;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class STATE extends VariantValueInvoke{


	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {
		
		
		Object objNeType = data.get("NE_TYPE");
		Object objActTbName = data.get("ACT_TBNAME");
		Object paraValue = data.get("PARAM_VALUE");
		String result = "NULL";
	
		if (objNeType.toString().trim().toUpperCase().equals("MME")
			&& objActTbName.toString().trim().toUpperCase().equals("CONFIGURE")
			&& paraValue!=null) {
	
			result = paraValue.toString().trim();
		}
		return new String[] { result };
	}
}
