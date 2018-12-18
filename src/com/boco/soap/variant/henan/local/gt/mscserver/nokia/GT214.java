
package com.boco.soap.variant.henan.local.gt.mscserver.nokia;


import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;
import com.boco.soap.variant.common.NumplanConvert;





import java.util.Map;

public class GT214 extends VariantValueInvoke
{
	
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {
		String ele_type = data.get("ELE_TYPE")==null?"":data.get("ELE_TYPE").toString();
		if(ele_type.equals("用户号段"))
		{
			String busi_type = data.get("BUSI_TYPE")==null?"":data.get("BUSI_TYPE").toString();
			if(arg0.getCoverCities().values().toString().indexOf(busi_type)<0)
			{
				return null;
			}
		
		Object o  = data.get("START_GT");
		String result = "[NULL]";
		if(null != o){
			result = NumplanConvert.E164ToE214(o.toString());
		}
		return new String[] { result };
		}
		else
			return null;
	}
	
	
}