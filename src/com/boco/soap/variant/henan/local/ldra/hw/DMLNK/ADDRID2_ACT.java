package com.boco.soap.variant.henan.local.ldra.hw.DMLNK;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class ADDRID2_ACT extends VariantValueInvoke {

	/**
	 * @author Sha Jianwen
	 * @time 2015-11-18 10:23:28
	 * DA IP地址名称2
	 * 只有当“传输协议类型”参数为“SCTP(SCTP)”时本参数才有效。SCTP支持使用多IP来建立链路，第一个IP地址必须提供，第二个可选。
	 * 该参数必须由ADD IPADDR或者ADD SRVIP命令定义，然后才能在此处引用。
	 * 执行命令时，不区分大小写。
	 * 参数现网值为IP地址   10.95.181.130
	 * 指令参数值为：ADDRNAME，字符串名称。ADDRID2="0-1-Signal-IP1"
	 */
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {

		Object _iptpObject = data.get("IPTP");
		Object o = data.get("ADDRID2");
		Object _pip1oObject = data.get("ADDRIPV6ID2");
		String result = null;

		if (_iptpObject == null) {
			result = "NULL";
		} else {
			if (_iptpObject.toString().toUpperCase().trim().equals("IPV4")) {
				result = o == null ? "NULL" : o.toString().trim();
			} else {
				result = _pip1oObject == null ? "NULL" : _pip1oObject.toString().trim();
			}
		}

		return new String[] { result };
	}
}