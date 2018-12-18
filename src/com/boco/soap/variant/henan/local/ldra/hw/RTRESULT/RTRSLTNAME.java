package com.boco.soap.variant.henan.local.ldra.hw.RTRESULT;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class RTRSLTNAME extends VariantValueInvoke {

	/**
	 * @author Sha Jianwen
	 * @time 2015-11-18 11:29:18
	 * RTRESULT:路由结果名称
	 * 执行命令时，不区分大小写
	 * 显示时，按照最初添加时的值显示
	 * 只有服务端的设备才会出现在此表，模板允许出现在此表的设备类型有：
	 * 1.PCRF;2.HSS-IMS;3.HSS-EPC;4.DRA。
	 * 其他设备类型不应该出现在此表。
	 */
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {

		Object o = data.get("RTRSLTNAME");
		String result = null;
		if (null == o) {
			result = "NULL";
		} else {
			result = o.toString().trim();
		}

		return new String[] { result };

	}
}
