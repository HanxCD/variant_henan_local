package com.boco.soap.variant.henan.local.ldra.hw.RTDHOST;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class DESTHOST extends VariantValueInvoke {

	/**
	 * @author Sha Jianwen
	 * @time 2015-11-24 15:52:47
	 * RTDHOST-DESTHOST：目的主机名
	 * 需要做RTDHOST表的设备有：HSS(IMS、EPC)；PGW;PCRF;I/S-CSCF;TAS
	 * 标识一条目的主机路由配置信息数据记录的关键字段之一（其取值不能被修改)
	 * 在SPS系统中，该参数统一转换为小写形式存储在配置数据库。
	 * 与I/S-CSCF对接时，选择Cx
	 * 			参考号：2
	 * 			目的主机名：S-CSCF目的主机名（本省主机名后缀）
	 * 			下一路由规则：灵活路由出口
	 * 			下一路由参考号：指向C链路（本省固定为1）
	 * 与TAS对接时，选择Sh
	 * 			参考号：3
	 * 			目的主机名：AS主机名（集中部署的AS主机名的后缀） 
	 * 			下一路由规则：灵活路由出口
	 * 			下一路由参考号：指向本省HDRA；本省HDRA固定为0
	 * 与I/S-CSCF对接时，选择Cx
	 * 			参考号：2
	 * 			目的主机名： S-CSCF目的主机名（本省主机名后缀）
	 * 			下一路由规则：灵活路由出口
	 * 			下一路由参考号：指向C链路（本省固定为1）
	 * 与HSS对接时，选择S6a、Cx、Sh、Zh、SLh
	 * 			参考号：2、3
	 * 			目的主机名：HSS主机名（本省）
	 * 			下一路由规则：灵活路由出口
	 * 			下一路由参考号：指向具体HSS（路由出口索引从51开始
	 */
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {

		Object _devtypeObject = data.get("DEVTYPE");
		String _devTypeString = null;
		Object o = data.get("HN");
		String result = null;
		if (null == o || _devtypeObject == null) {
			result = "null";
		} else {

			_devTypeString = _devtypeObject.toString().trim().toUpperCase();

			if (_devTypeString.equals("I/S-CSCF")) {
				result = "ha.node.ims.mnc000.mcc460.3gppnetwork.org";
			} else {
				result = o.toString().trim().toLowerCase().trim();
			}
		}

		return new String[] { result };

	}
}
