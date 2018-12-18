package com.boco.soap.variant.henan.local.gt.lstp.bell;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class RTGIND extends VariantValueInvoke {
	/*
	 * Editor:Jianwen Sha Edited time:2015-8-11 10:20:21 DESC:��ȡ Ѱַ�������?
	 */
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {

		String hlrName = data.get("HLR_NAME").toString();
		String result="";
		if(hlrName.indexOf("BNK")>0){
			result="RTGGLBT,RTGGLBT,RTGGLBT";
		}else  if(hlrName.indexOf("BHW")>0){
			result="RTGPC,RTGPC";
		}
		return new String[] { result };

	}

}
