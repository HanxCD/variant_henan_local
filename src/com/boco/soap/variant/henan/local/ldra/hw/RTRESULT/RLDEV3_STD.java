package com.boco.soap.variant.henan.local.ldra.hw.RTRESULT;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class RLDEV3_STD extends VariantValueInvoke {
	// 记录日志
	private Logger logger = LoggerFactory.getLogger(RLDEV3_STD.class);

	/**
	 * @author Sha Jianwen
	 * @time 2015-11-18 11:35:43
	 * RTRESULT-RLDEV3:中继设备3
	 */
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {

		Object o = data.get("RLDEV3");
		String result = null;

		if (null == o) {
			result = "";// <nullable处理>
		} else {
			if (o.toString().trim().length() == 0) {
				result = "";
			} else {
				result = o.toString().trim();
			}
		}

		return new String[] { result };

	}
}
