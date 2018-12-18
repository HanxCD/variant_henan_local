package com.boco.soap.variant.henan.local.lte.hss.hw;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class HW_HSS_START_GT extends VariantValueInvoke{

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {

		Object start_gt=data.get("START_GT");
		Object isCall=data.get("IS_CALLOTHER");
	    String result="";
	if(null!=isCall){
		if(isCall.toString().equals("是")){
			if(null!=start_gt){
				result=start_gt.toString().trim();
			}else{
				return null;
			}
		}else{
			return null;
		}
	}else{
		return null;
	}
		return new String[] { result };
	}

}
