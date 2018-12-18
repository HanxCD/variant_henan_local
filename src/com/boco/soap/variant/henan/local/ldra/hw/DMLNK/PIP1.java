package com.boco.soap.variant.henan.local.ldra.hw.DMLNK;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class PIP1 extends VariantValueInvoke {

	/**
	 * @author Sha Jianwen
	 * @time 2015-11-18 11:12:41
	 * 对端IPv4地址1 或 对端IPv6地址1
	 * 将IP协议与IP地址绑定核查、制作 PIP41="10.95.217.17"
	 */
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {

		Object o = data.get("PIP1");
		Object tp = data.get("IPTP");
		String result = null;
		if (null == tp) {
			result = "NULL";
		} else {
			if (null == o) {
				if (tp.toString().trim().toUpperCase().indexOf("IPV4") >= 0) {
					result = "PIP41=\"NULL\"";
				} else if (tp.toString().trim().toUpperCase().indexOf("IPV6") >= 0) {
					result = "PIP61=\"NULL\"";
				} else {
					result = "NULL";
				}
			} else {
				if (tp.toString().trim().toUpperCase().indexOf("IPV4") >= 0) {
					result = "PIP41=\"" + o.toString().trim() + "\"";
				} else if (tp.toString().trim().toUpperCase().indexOf("IPV6") >= 0) {
					result = "PIP61=\"" + o.toString().trim() + "\"";
				} else {
					result = "NULL";
				}
			}
		}

		return new String[] { result }; 
	}
}
