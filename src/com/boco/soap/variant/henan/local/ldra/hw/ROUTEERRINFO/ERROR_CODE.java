package com.boco.soap.variant.henan.local.ldra.hw.ROUTEERRINFO;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class ERROR_CODE extends VariantValueInvoke {

	/**
	 * @author Sha Jianwen
	 * @time 2016-4-14 14:31:00
	 * ERROR_CODE ：错误码 
	 * ZZDRA02BHW、ZZDRA01BHW
	 * 需做标准数据过滤：
	 * 将标准数据中的主键设置为空引用(null) 即可过滤掉不参与核查。
	 */
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {
		Object objNeType = data.get("NE_TYPE");
		Object objActTbName = data.get("ACT_TBNAME");
		String result = null;

		if (objNeType.toString().trim().toUpperCase().equals("LDRA")
				&& objActTbName.toString().trim().toUpperCase().equals("ROUTEERRINFO")) {

			result = data.get("PARAM_NAME").toString().trim();
			return new String[] { result };
		}
		else{
			return null;
		}
	}
}