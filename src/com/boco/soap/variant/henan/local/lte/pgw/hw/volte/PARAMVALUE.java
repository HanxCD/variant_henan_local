package com.boco.soap.variant.henan.local.lte.pgw.hw.volte;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class PARAMVALUE extends VariantValueInvoke {

	public String paramVue="";
	@Override
	
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {
		
		Object objValue= data.get("PARAM_VALUE");
		String result="[NULL]";
		if(objValue!=null){
			result=objValue.toString();
		}
		return new String[] {result };
	}
}
