package com.boco.soap.variant.henan.local.ldra.hw.RTRESULT;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class RLSMOD_ACT extends VariantValueInvoke {

	/**
	 * @author Sha Jianwen
	 * @time 2015-11-18 11:34:39
	 * RTRESULT-RLSMOD:中继设备选择方式
	 * 用于指定路由中的中继设备的选择方式。当“协议类型”配置为“DIAMETER(DIAMETER)”时有效
	 * ROUND_ROBIN(轮选)\MASTER_SLAVE(主备)
	 */
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {
		Object o = data.get("RLSMOD");
		String result = null;
		if (null == o) {
			result = "NULL";
		} else {
			result = o.toString().trim();
		}

		return new String[] { result };

	}
}