/*
 * Copyright 2014 BOCO Inter-Telecom (xi'an).
 * All rights reserved. 
 * project name: variant_sichuan
 * version V1.0
 * -------------------------------------------
 * author: lijixin
 * date: 2014-10-31
 * note: 
 */
package com.boco.soap.variant.henan.local.ims.mcgf.zte;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * gt gmsc�˾� ��Ϊ   sccpgt E164
 * 
 * @author lijixin
 * @Email lijixin2014@boco.com.cn
 * 
 */
public class RESULT_LTDX extends VariantValueInvoke {

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {
		
		Object o = data.get("AREA_CODE");
		Object city=data.get("BUSI_CITY");
		String result = null;
		if(null == o){
			result = "[NULL]";
		} else {
			String areaCode=o.toString();
			if (areaCode.indexOf("37")!=-1) {
				if (city!=null&&city.toString().equals("开封")) {
					result="180";
				}else{
					result="1"+areaCode.charAt(areaCode.length()-1)+"0";
				}
			}else if(areaCode.indexOf("39")!=-1){
				result="2"+areaCode.charAt(areaCode.length()-1)+"0";
			}else{
				result = "[NULL]";
			}
		}
		return new String[]{result};
		
	}
}