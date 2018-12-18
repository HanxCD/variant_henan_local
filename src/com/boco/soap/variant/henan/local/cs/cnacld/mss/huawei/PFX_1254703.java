package com.boco.soap.variant.henan.local.cs.cnacld.mss.huawei;

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
public class PFX_1254703 extends VariantValueInvoke {

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {

		Object o = data.get("E164");
		String result = null;
		if (null == o) {
			result = "[NULL]";
		} else {
			result = o.toString().trim();
			if (result.startsWith("86")){
				result = "1254703" + result.substring(2);
			}

		}

		return new String[] { result };

	}
}