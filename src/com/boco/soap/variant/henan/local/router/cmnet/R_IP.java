package com.boco.soap.variant.henan.local.router.cmnet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class R_IP extends VariantValueInvoke{
	
	  private Map<String, String> map= null;
	
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {
		String result="";
		Object IP=data.get("IP");//获取IP
		if(null!=IP){
			if(IP.toString().indexOf("/")<=-1){
				result=IP+"/32";
			}else{
				result=IP.toString();
			}
		}
		return  new String[] { result };
		
	}
	
	
}
