package com.boco.soap.variant.henan.local.ldra.hw.RTAPN;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class BNK_CMWAP_NAME extends VariantValueInvoke {

	/**
	 * @author Sha Jianwen
	 * @time 2015-11-24 19:04:34
	 * RTAPN-NAME:APN路由名称
	 * 执行命令时，不区分大小写。
	 * APN路由名称：3厂家 * 8个APN(CMNET/cmnet/CMWAP/cmwap/IMS/ims/SOS/sos) 共24遍；
	 * 且CMNET/cmnet/CMWAP/cmwap 指向归属PRCF;
	 * IMS/ims/SOS/sos 指向漫游地PCRFPOOL，故3个厂家均需做。	
	 * 之所以不能用动态，是因为不同的“APN路由名称”要一一对应不同的“APN” ，且APN字符串，大小写敏感
	 */
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {

		Object o = data.get("DN");
		String result = null;
		if (null == o) {
			result = "null";
		} else {
			// BNK-CMWAP-ZZPCRF01BER
			result = "BNK-CMWAP-" + o.toString().trim().toUpperCase();
		}
		return new String[] { result };
	}
}
