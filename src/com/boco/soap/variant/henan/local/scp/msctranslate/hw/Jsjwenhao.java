
package com.boco.soap.variant.henan.local.scp.msctranslate.hw;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * wanghao25   取局数据文号的
 */
public class Jsjwenhao extends VariantValueInvoke {

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {
		
		Object o = data.get("BUREAU_DATA_NO");
		
		String result = null;
	
		if(null == o){
			result = "[NULL]";
		} else {			
					result =o.toString().trim();			
		}
		
		return new String[]{result};
	}	
}