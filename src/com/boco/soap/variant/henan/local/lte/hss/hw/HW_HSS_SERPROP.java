package com.boco.soap.variant.henan.local.lte.hss.hw;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;
/**
 * 
 * @ClassName:  HW_HSS_SERPROP   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: mengningning 
 * @date:   2017-5-12 上午11:09:05   
 * @version: V1.0  
 * @Copyright: 2017 www.boco.com.cn Inc. All rights reserved.
 */
public class HW_HSS_SERPROP extends VariantValueInvoke{

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {

		Object isCall=data.get("IS_CALLOTHER");
		Object callType=data.get("CALLOTHER_TYPE");
	    String result="";
	   if(null!=isCall){
		  if(isCall.equals("是")){
			  if(null!=callType){
			if(callType.equals("LOCAL")){
				result= "LOCAL";
			}else if(callType.equals("PLMN")){
				result="PLMN";
			}else{
				return null;
		   	}
		   }
		  }
	   }
		return new String[] { result };
	}

}
