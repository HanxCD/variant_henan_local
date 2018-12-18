package com.boco.soap.variant.henan.local.lte.mme.hw.volte;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class VALUE2 extends VariantValueInvoke {

	@Override
	
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {
		Object objNeType = data.get("NE_TYPE");
		Object objActTbName = data.get("ACT_TBNAME");
		Object objVendorName = data.get("VENDOR_NAME");
		Object paramName = data.get("PARAM_NAME");
		String result ="[NULL]";

		if (objNeType.toString().trim().toUpperCase().equals("MME") 
				&& objActTbName.toString().trim().toUpperCase().equals("SOFTPARAOFBIT")
				&& objVendorName.toString().trim().toUpperCase().equals("华为")) {
			if (paramName.toString().indexOf("Byte_Ex40 Bit5")>-1) {
				result="VALUE_0";
			}else{
				result="VALUE_1";
			}
		}
		return new String[]{result};
	}
}
