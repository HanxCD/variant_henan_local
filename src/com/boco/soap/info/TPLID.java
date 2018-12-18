package com.boco.soap.info;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class TPLID  extends VariantValueInvoke {

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {
		Object o = data.get("TPLID");
		String result="";
		if(null==o){
			result = "[NULL]";
		} else {
			result =o.toString();
		}
		return new String[]{result};
	}

}


