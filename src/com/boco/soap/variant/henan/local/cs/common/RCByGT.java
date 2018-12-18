package com.boco.soap.variant.henan.local.cs.common;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * ��Ϊ�˾ֺ����ֹ�
 * 
 * @author baixp
 * @Email
 * 
 */
public class RCByGT extends VariantValueInvoke {

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {
        String p="";
		int iNumber=Integer.parseInt(data.get("START_GT").toString());
		if(iNumber%2==0) p="102";
		else p="101";
		return new String[] { p };

	}
}