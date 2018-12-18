package com.boco.soap.variant.henan.local.ldra.hw.RTRESULT;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class RLDEV2_ACT extends VariantValueInvoke {

	/**
	 * @author Sha Jianwen
	 * @time 2015-11-18 11:35:43
	 * RTRESULT-RLDEV2:中继设备2
	 * 中继设备为“C链”时：若核查设备是ZZDRA01BHW，则RLDEV2=ZZDRA02BHW；
	 * 若核查设备是ZZDRA02BHW，则RLDEV2=ZZDRA01BHW；
	 */
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {

		Object o = data.get("RLDEV2");
		String _deviceNameString = arg0.getName();
		String result = null;
		if (null == o) {
			result = "NULL";
		} else {
			if (o.toString().trim().toUpperCase().equals("C链")) {
				result = _deviceNameString.trim().toUpperCase().equals("ZZDRA01BHW") ? "ZZDRA02BHW" : "ZZDRA01BHW";
			} else {
				result = o.toString().trim();
			}
		}

		return new String[] { result };

	}
}
