package com.boco.soap.variant.henan.local.uap.scp;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class GTName extends VariantValueInvoke {
    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {

        Object o = data.get("OFFICE_NAME");

        String result = null;

        if (null == o) {
            result = "[NULL]";
        } else {
            result = o.toString().trim();
        }

        return new String[] { result };
    }

}