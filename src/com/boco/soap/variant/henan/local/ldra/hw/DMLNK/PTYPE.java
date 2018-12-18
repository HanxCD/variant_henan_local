package com.boco.soap.variant.henan.local.ldra.hw.DMLNK;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class PTYPE extends VariantValueInvoke {

	/**
	 * @author Sha Jianwen
	 * @time 2015-11-18 10:23:28
	 * 传输协议类型
	 */
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {

		Object o = data.get("PTYPE");
		return new String[] { o == null ? "NULL" : o.toString().trim() };
	}
}
