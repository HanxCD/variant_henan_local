package com.boco.soap.variant.henan.local.cs.mss.hw.pfxpro;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class GT extends VariantValueInvoke {

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {
        if (!data.get("GT_TYPE").toString().equals("铁通卡")) {
            return null;
        }
        Object o = data.get("START_GT");
        String result = null;
        if (null == o) {
            result = "[NULL]";
        } else {
            result = o.toString().trim();
        }

        return new String[] { result };

    }
}