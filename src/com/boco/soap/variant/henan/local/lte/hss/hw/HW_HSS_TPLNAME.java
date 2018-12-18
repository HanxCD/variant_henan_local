package com.boco.soap.variant.henan.local.lte.hss.hw;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class HW_HSS_TPLNAME extends VariantValueInvoke{

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {
		// TODO Auto-generated method stub
		
		
		Object TPLNAME=data.get("APN");
		
        String result = "";
        if (TPLNAME == null) {
            result = "[NULL]";
        } else {
        	result=TPLNAME.toString();
        }
        return new String[] { result}; 
	}

}
