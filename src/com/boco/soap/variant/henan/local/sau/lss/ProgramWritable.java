package com.boco.soap.variant.henan.local.sau.lss;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class ProgramWritable extends VariantValueInvoke {
    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {
        Object o = data.get("PROENWRITING");

        String result = null;

        if (o == null) {
            result = "[NULL]";
        } else {
            result = o.toString().trim();
        }

        return new String[] { result };
    }
}
