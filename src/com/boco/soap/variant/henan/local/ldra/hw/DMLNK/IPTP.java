package com.boco.soap.variant.henan.local.ldra.hw.DMLNK;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class IPTP extends VariantValueInvoke {

	/**
	 * @author Sha Jianwen
	 * @time 2015-11-18 10:23:28 
	 * IP类型 
	 * 执行命令时，不区分大小写。
	 */
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {

		Object o = data.get("IPTP");
		String result = null;
		if (null == o) {
			result = "NULL";
		} else {
			result = o.toString().trim();
		}

		return new String[] { result }; 
	}
}
