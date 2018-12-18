package com.boco.soap.variant.henan.local.sau.sccpgt;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class START_GT extends VariantValueInvoke {
    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> arg2, String arg3) {
        Object o = arg2.get("MSCID");
        String result = null;
        if (o != null) {
            result = o.toString();
            if (!result.startsWith("86")) {
                result = "86" + result;
            }
        }
        return new String[] { result };
    }
}
