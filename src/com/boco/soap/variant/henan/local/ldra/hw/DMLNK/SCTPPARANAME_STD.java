package com.boco.soap.variant.henan.local.ldra.hw.DMLNK;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class SCTPPARANAME_STD extends VariantValueInvoke {

	/**
	 * @author Sha Jianwen
	 * @time 2015-11-18 10:23:28
	 * SCTP协议参数名称
	 * 当传输协议类型为“SCTP”时，需要对该SCTP协议类型中设定对应参数值，以保证SCTP协议传输的可靠性。
	 * 建议默认值为“Default”
	 * 当传输协议类型为非“SCTP”时，不需要该参数
	 */
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {

		Object o = data.get("PTYPE");
		if (null == o) {
			return new String[] { "Default" };
		} else {
			return new String[] { o.toString().trim().toUpperCase().equals("SCTP") ? "Default" : "" };
		}
	}
}
