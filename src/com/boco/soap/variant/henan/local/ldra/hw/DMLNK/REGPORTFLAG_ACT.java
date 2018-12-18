package com.boco.soap.variant.henan.local.ldra.hw.DMLNK;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class REGPORTFLAG_ACT extends VariantValueInvoke {

	/**
	 * @author Sha Jianwen
	 * @time 2015-11-18 10:23:28
	 * 使用注册端口
	 * 只有当“工作模式”为“SERVER(服务端)”时，本参数才有效
	 *	工作模式为“客户端”时，指令中无改参数。
	 */
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {

		Object _wmodeObject = data.get("WMODE");
		Object o = data.get("REGPORTFLAG");
		String result = null;
		if (null == o || _wmodeObject == null) {
			result = "";
		} else if (_wmodeObject.toString().trim().equals("客户端")) {
			result = "NO";
		} else {
			result = o.toString().trim();
			if (result.indexOf("是") >= 0)
				result = "YES";
			else if (result.indexOf("否") >= 0)
				result = "NO";
			else {
				result = "NULL";
			}
		}

		return new String[] { result };
	}
}
