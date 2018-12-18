package com.boco.soap.variant.henan.local.router.cmnet;

import java.util.HashMap;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;
/**
 * 
 * @ClassName:  R_SETNAME   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: mengningning 
 * @date:   2017-6-5 下午03:16:49   
 * @version: V1.0  
 * @Copyright: 2017 www.boco.com.cn Inc. All rights reserved.
 */
public class R_SETNAME extends VariantValueInvoke{

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {
		String result="";
		
		Object rtName=data.get("STRANAME");//获取策略名称
		if(null==rtName){
			result="NULL";
		}else if(rtName.toString().trim().equals("ICP备案")){
			result = " poliy-options prefix-list";
		}else if(rtName.toString().trim().equals("分公司专线")){
			result = " poliy-options prefix-list";
		}else if(rtName.toString().trim().equals("投诉外部地址")){
			result = " poliy-options prefix-list";
		}else if(rtName.toString().trim().equals("目的投诉")){
			result=" firewall filter D3F term";
		}
		return  new String[] { result };
	}
	
	

    
}
