package com.boco.soap.variant.henan.local.ldra.hw.DMPEER;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class SPM_STD extends VariantValueInvoke {

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {
		/**
		 * @author Sha Jianwen
		 * @time：2015-11-17 10:17:38
		 * 设备对接参数 
		 * 1）成对的DA：此参数只用于对端网元为C链的DRA时选中。
		 * 2）请求消息修改目的主机名：该功能主要用于HSS和PCRF主备用切换时使用。
		 */
		Object o = data.get("SPM");
		String _spmString = null;
		String result = null;
		if (null == o) {
			result = "";// <nullable>处理
		} else {
			_spmString = o.toString().trim();
			if (_spmString.equals("请求消息修改目的主机名")) {
				result = "MODREQDH-1";
			} else if (_spmString.equals("请求消息修改目的域名")) {
				result = "MODREQDR-1";
			} else if (_spmString.equals("请求消息修改源主机名")) {
				result = "MODREQOR-1";
			} else if (_spmString.equals("请求消息修改源域名")) {
				result = "MODREQOR-1";
			} else {
				result = _spmString;
			}
		}

		return new String[] { result };
	}
}
