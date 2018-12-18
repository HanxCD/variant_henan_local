package com.boco.soap.variant.henan.local.ims.agcf;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;
/**
 * 是否允许外呼
 * @author 
 *
 */
public class ALLOW_OUTBOUND extends VariantValueInvoke{

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {
		// TODO Auto-generated method stub
		String ALLOW_OUTBOUND = data.get("ALLOW_OUTBOUND").toString();
		if(ALLOW_OUTBOUND.equals("是".trim())){
			return null;
		}
		return new String[]{ALLOW_OUTBOUND};
	}

}
