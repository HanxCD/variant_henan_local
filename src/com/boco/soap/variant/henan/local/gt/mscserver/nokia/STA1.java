
package com.boco.soap.variant.henan.local.gt.mscserver.nokia;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class STA1 extends VariantValueInvoke
{
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {
		String stateTemp = data.get("STATE")==null?"":data.get("STATE").toString();
		String stateStr = stateTemp.replaceAll(",|\\s+|、|，", ",");
		String hlrName = data.get("HSS")==null?"":data.get("HSS").toString();
		String resStr = data.get("GT_RESULT")==null?"":data.get("GT_RESULT").toString();
		if(resStr.equals("191")&&(arg0.getPhysicalAddr().contains("济源")||arg0.getPhysicalAddr().contains("焦作"))){
			return new String[] { stateStr };
		}
		if(hlrName.indexOf("BNK")>-1){
			stateStr="AV-EX,AV-EX";
		}
		return new String[] { stateStr };
	}

}