package com.boco.soap.variant.henan.local.ims.mcgf.zte;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class MGCF_DEVICENAME extends VariantValueInvoke {

	/**
	 * @author Sha Jianwen
	 * @time 2016年3月30日 上午10:49:34
	 * VOLTE-IMS-MGCF VOLTE参数核查
	 * 网元名称：MGCF_DEVICENAME
	 * 以网元名称作为主键，核查若干参数值是否正确。（核查结果只有：正确、错做）
	 */

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {
				return new String[] { arg0.getName().toString().trim() };
	}
}
