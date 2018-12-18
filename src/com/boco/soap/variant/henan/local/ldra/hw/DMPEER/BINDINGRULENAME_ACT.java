package com.boco.soap.variant.henan.local.ldra.hw.DMPEER;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class BINDINGRULENAME_ACT extends VariantValueInvoke {

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {
		/**
		 * @author Sha Jianwen
		 * @time 2015-11-17 10:32:07
		 * 绑定规则名称,当“绑定标志”设置为“YES(是)”时，才配置该参数。
		 * 需要做<nullable>处理
		 */
		Object o = data.get("BINDINGRULENAME");
		String result = null;
		if (null == o) {
			result = "NULL";
		} else {
			result = o.toString().trim();
		}

		return new String[] { result };

	}
}