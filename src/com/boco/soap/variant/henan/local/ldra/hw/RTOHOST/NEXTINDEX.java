package com.boco.soap.variant.henan.local.ldra.hw.RTOHOST;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class NEXTINDEX extends VariantValueInvoke {

	/**
	 * @author Sha Jianwen
	 * @time 2015-11-24 15:52:47
	 * RTOHOST-NEXTINDEX：下一路由参考号
	 * 下一路由参考号的总数与POOL的总数一致
	 * 与PGW对接时，选择Gx		
	 * 			参考号：0 集团规范固定值
	 * 			源端主机名：标准工单主机名
	 * 			下一路由规则：RTMSISDN(MSISDN路由)
	 * 			下一路由参考号：根据厂家区分：华为：2；诺西：3；爱立信：4
	 */
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {

		Object _hnObject = data.get("HN");
		String _hnString = null;
		Object _devtypeObject = data.get("DEVTYPE");
		String _devtypeString = null;
		String result = null;

		if (null == _hnObject || _devtypeObject == null) {
			result = "NULL";
		} else {

			_hnString = _hnObject.toString().trim().toLowerCase();
			_devtypeString = _devtypeObject.toString().trim().toUpperCase();

			if (_devtypeString.equals("PGW")) {
				if (_hnString.contains("bhw")) {
					result = "2";
				} else if (_hnString.contains("bnk")) {
					result = "3";
				} else if (_hnString.contains("ber")) {
					result = "4";
				} else {
					result = "NULL";
				}
			} else {
				// TODO:其他设备类型处理
				result = "NULL";
			}
		}

		return new String[] { result };

	}
}
