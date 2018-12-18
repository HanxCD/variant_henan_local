package com.boco.soap.variant.henan.local.shortnumber.gmsc.hw;

import java.util.Map;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * 短号码 gmsc 所有用户拨打在综合业务关口局上的号码，如果此号码在不同地市既有集中接入又有分散接入，那么对该号码做特殊处理
 * 
 * @author changquanzhou
 * 
 */
public class HW_GMSS_CNACLD_ZHYW_PFX extends VariantValueInvoke {
	@Override
	public String[] getValues(INeElement ne, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {
		Object o = data.get("START_GT");
		Object oInnerType = data.get("INNER_TYPE");
		String result = null;
		if (null == o) {
			result = "[NULL]";
		} else {
			if (oInnerType!=null) {
				String innerType = oInnerType.toString();
				//如果该号码又有集中又有分散接入，则不需在关口局做数据
				if (innerType.contains("分散")&& innerType.contains("集中")) {
					return null;
				}
			}
			result = o.toString();
		}
		
		return new String[] { result };

	}

}