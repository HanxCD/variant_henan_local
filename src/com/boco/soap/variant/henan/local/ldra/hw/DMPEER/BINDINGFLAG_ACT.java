package com.boco.soap.variant.henan.local.ldra.hw.DMPEER;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class BINDINGFLAG_ACT extends VariantValueInvoke {

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {

		/**
		 * @author Sha Jianwen 
		 * @time 2015-11-17 10:31:47
		 * 绑定标志,用于设置是否启动此对端设备的消息绑定，该参数仅对Gx/Gy/Rx接口有效
		 * 用于设置对网元的会话绑定，PGW、P-CSCF\PCRF网元该参数需要设置为“是”。
		 * --《中国移动通信局数据制作规范JSJ－SB－DRA－HW－V1.0》
		 * 默认值：NO(否)。
		 * 目前需要绑定规则名称的设备类型有：PGW\PCRF\P-CSCF
		 */
		Object o = data.get("BINDINGFLAG");
		String result = null;
		if (null == o) {
			result = "否";
		} else {
			result = o.toString().trim();
		}

		return new String[] { result };

	}
}
