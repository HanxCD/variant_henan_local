package com.boco.soap.variant.henan.local.lte.mme.hw.volte;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class VALUE extends VariantValueInvoke {
	
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {
		
		Object objNeType = data.get("NE_TYPE");
		Object objActTbName = data.get("ACT_TBNAME");
		Object objVendorName = data.get("VENDOR_NAME");
		String result ="[NULL]";

		if (objNeType.toString().trim().toUpperCase().equals("MME") 
				&& objActTbName.toString().trim().toUpperCase().equals("SOFTPARAOFBIT")
				&& objVendorName.toString().trim().toUpperCase().equals("华为")) {

			result = data.get("PARAM_VALUE") == null ? "未配置参数值" : data.get("PARAM_VALUE").toString().trim();
			return new String[] { result };
		}else{
			return null;
		}
	}
}
