package com.boco.soap.variant.henan.local.scp.kvpn.hw;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;
/**
 * 
 * @ClassName:  BussNum   
 * @Description:TODO(号码类型配置获取业务区号   )   
 * @author: mengningning 
 * @date:   2017-5-4 上午11:50:12   
 * @version: V1.0  
 * @Copyright: 2017 www.boco.com.cn Inc. All rights reserved.
 */
public class BussNum extends VariantValueInvoke{

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {
		Object o = data.get("AREA_CODE");

		String result = null;
		if(null == o){
			result = "[NULL]";
		} else {
			result ="130"+o.toString().trim()+"01";
				
		}
		
		return new String[]{result};
		
		
	}

}
