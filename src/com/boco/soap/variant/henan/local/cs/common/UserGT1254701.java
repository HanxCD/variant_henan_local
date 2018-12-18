package com.boco.soap.variant.henan.local.cs.common;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * »ªÎª¶Ë¾Öºô½Ð×Ö¹Ú
 * 
 * @author 
 * @Email
 * 
 */
public class UserGT1254701 extends VariantValueInvoke {

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {

		Object o = data.get("E164");
		String result = null;
		if (null == o) {
			result = "[NULL]";
		} else {
			result = "1254701"+o.toString().substring(2);

		}

		return new String[] { result };

	}
}