package com.boco.soap.variant.henan.local.ims.mcgf.zte;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;
import java.util.Map;

public class AC extends VariantValueInvoke{
	 public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> arg2, String arg3)
	  {
	    Object o = arg2.get("AREA_CODE");
	    if (o != null) {
	      return new String[] { o.toString() };
	    }
	    return new String[] { "[NULL]" };
	  }

}