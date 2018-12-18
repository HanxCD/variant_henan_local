package com.boco.soap.variant.henan.local.ldra.hw.DMLNK;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class REGPORT_STD extends VariantValueInvoke {

	/**
	 * @author Sha Jianwen
	 * @time 2015-11-18 11:25:34
	 * 注册端口号
	 * 当“使用注册端口”为“YES(是)”时，本参数才有效。
	 * 默认值：3868
	 * 需做//<nullable>处理
	 */
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {

		Object o = data.get("REGPORT");
		Object _regportflagObject = data.get("REGPORTFLAG");
		String result = null;
		if (null == o || null == _regportflagObject) {
			result = "";// <nullable>处理
		} else if (_regportflagObject.toString().trim().equals("是") && o.toString().trim().toUpperCase().equals("NULL")) {
			result = "3868";
		} else {
			result = o.toString().trim();
		}

		return new String[] { result };
	}
}
