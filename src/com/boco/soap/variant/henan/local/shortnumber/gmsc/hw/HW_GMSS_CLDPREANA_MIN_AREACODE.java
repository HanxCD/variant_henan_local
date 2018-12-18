package com.boco.soap.variant.henan.local.shortnumber.gmsc.hw;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class HW_GMSS_CLDPREANA_MIN_AREACODE extends VariantValueInvoke {

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {
        Object o = data.get("MINL");
        String result = null;
        if (null == o) {
            result = "[NULL]";
        } else {
            result = (Integer.valueOf(o.toString()) + 4) + "";
        }

        return new String[] { result };
    }

}