
package com.boco.soap.variant.henan.local.gt.mscserver.nokia;


import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;


import java.util.Map;

public class NDEST extends VariantValueInvoke
{
	
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {
		String spcStr = data.get("COMP_NAME")==null?"":data.get("COMP_NAME").toString();
        String resultCodeStr="";
		if(spcStr.indexOf("联通")>=0)
			resultCodeStr="LTD";
		if(spcStr.indexOf("电信")>=0)
			resultCodeStr="DXD";
		return new String[] { resultCodeStr };
		}
		
	
}