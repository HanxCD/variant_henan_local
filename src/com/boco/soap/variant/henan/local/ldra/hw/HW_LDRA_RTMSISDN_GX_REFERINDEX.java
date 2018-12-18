package com.boco.soap.variant.henan.local.ldra.hw;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class HW_LDRA_RTMSISDN_GX_REFERINDEX extends VariantValueInvoke {

	/**
	 * @author Sha Jianwen
	 * @time 2015年11月26日 下午6:38:31
	 * LDRA RTMSISDN GX接口 华为、诺西、爱立信分别为：2,3,4
	 */
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {
		return new String[] { "2", "3", "4" };
	}
}