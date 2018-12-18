package com.boco.soap.variant.henan.local.cs.common;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * @author  wanghao
 * @Email
 * @date 2018-01-23 
 * @note 物联网号段中只核查制作标准表中是否放音为是的号段
 */
public class UserGT0_Ting_fangyin extends VariantValueInvoke {

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {

		Object o = data.get("E164");
		Object p = data.get("ISORNOTPALYMUSIC");
		String result = null;
		if (String.valueOf(p).equals("否")){
			return null;
		} else {
		if (null == o) {
			result = "[NULL]";
		} else {
			result = "0"+o.toString().substring(2);

		}
		

		return new String[] { result };
		}

	}
}
