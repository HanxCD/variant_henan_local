package com.boco.soap.variant.henan.local.cs.common;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * »ªÎª¶Ë¾Öºô½Ð×Ö¹Ú
 * 
 * @author baixp
 * @Email
 * 
 */
public class PByNe extends VariantValueInvoke {

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {
        String p="";
		int iNumber=Integer.parseInt(arg0.getName().substring(arg0.getName().indexOf("DS")+2));
		if(iNumber%2==0) p="1";
		else p="0";
		return new String[] { p };

	}
}