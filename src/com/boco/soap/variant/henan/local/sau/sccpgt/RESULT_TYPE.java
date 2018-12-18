package com.boco.soap.variant.henan.local.sau.sccpgt;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class RESULT_TYPE extends VariantValueInvoke {
    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> arg2, String arg3) {
        Object o = arg2.get("GT_INDEX");
        String result = null;
        if ((o != null) && (o.toString().equals("0"))) {
            result = o.toString();
        } else {
            result = "DPC + OLD GT";
        }
        return new String[] { result };
    }
}
