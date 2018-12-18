package com.boco.soap.variant.henan.local.ims.sbc.hw;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * 
 * author: wanghao
 * date: 2018-07-05
 * note: 华为SBC短号码拦截数据   !.*!+裸号
 * 
 */
public class IMS_HWSBC_NOCALLPFX extends VariantValueInvoke {

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {

		Object o = data.get("START_GT");
		String result = null;
		if (null == o) {
			result = "[NULL]";
		} else {
			result = o.toString().trim();
			if (result.startsWith("9")||result.startsWith("1")){
				result = "!.*!" + result;
			}

		}

		return new String[] { result };

	}
}