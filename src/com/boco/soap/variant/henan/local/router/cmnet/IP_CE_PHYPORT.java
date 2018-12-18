package com.boco.soap.variant.henan.local.router.cmnet;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class IP_CE_PHYPORT extends VariantValueInvoke{

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {
		// TODO Auto-generated method stub
		String result="";
		Object phy_port=data.get("UPP_PHY_PORT");
		if(null==phy_port){
			return null;
		}else{
			result=phy_port.toString().trim();
		}
		return new String[]{result};
	}
  
}
