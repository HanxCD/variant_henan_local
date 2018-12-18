package com.boco.soap.variant.henan.local.cs.common;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * ��Ϊ�˾ֺ����ֹ�
 * 
 * @author baixp
 * @Email
 * 
 */
public class GT3ONLY134 extends VariantValueInvoke {

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {

		Object o = data.get("START_GT");
		String result = null;
		if (null == o) {
			result = "[NULL]";
		} else {
			if(o.toString().trim().indexOf("134")==0)
			result =o.toString().trim().substring(0,3);
			else
				result =o.toString().trim();
		}

		return new String[] { result };

	}
}