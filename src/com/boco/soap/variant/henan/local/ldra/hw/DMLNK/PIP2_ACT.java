package com.boco.soap.variant.henan.local.ldra.hw.DMLNK;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class PIP2_ACT extends VariantValueInvoke {
	/**
	 * @author Sha Jianwen
	 * @time 2015-11-18 11:12:41
	 * 对端IPv4地址2 或 对端IPv6地址2
	 * 将IP协议与IP地址绑定核查、制作 PIP42="10.95.217.17"
	 * PIP2若不制作，现网为NULL字符串，需要核查，制作时需为""
	 */
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {

		Object o = data.get("PIP2");
		Object tp = data.get("IPTP");
		String result = null;
		if (null == tp) {
			result = "NULL";// 现网不做PIP2时，显示为"NULL"
		} else {
			if (null == o) {
				if (tp.toString().trim().toUpperCase().indexOf("IPV4") >= 0) {
					result = "PIP42=\"NULL\"";
				} else if (tp.toString().trim().toUpperCase().indexOf("IPV6") >= 0) {
					result = "PIP62=\"NULL\"";
				} else {
					result = "NULL";
				}
			} else {
				if (tp.toString().trim().toUpperCase().indexOf("IPV4") >= 0) {
					result = "PIP42=\"" + o.toString().trim() + "\"";
				} else if (tp.toString().trim().toUpperCase().indexOf("IPV6") >= 0) {
					result = "PIP62=\"" + o.toString().trim() + "\"";
				} else {
					result = "NULL";
				}
			}
		}

		return new String[] { result };

	}
}
