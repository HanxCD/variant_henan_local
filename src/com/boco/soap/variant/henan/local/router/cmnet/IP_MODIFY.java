package com.boco.soap.variant.henan.local.router.cmnet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class IP_MODIFY extends VariantValueInvoke{
	
	  private Map<String, String> map= null;
	
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {
		String result="";
		Object IP=data.get("IP");//获取IP
		
		Object rtName=data.get("STRANAME");//获取策略名称
		 if(rtName.toString().trim().equals("目的投诉")){
			if(IP.toString().indexOf("/")<=-1){
				result="from destination-address "+IP+"/32";
			}else{
				result="from destination-address "+IP;
			}
			
		}else{
			if(IP.toString().indexOf("/")<=-1){
				result=IP+"/32";
			}else{
				result=IP.toString();
			}
		}
		return  new String[] { result };
	}
	
	
}
