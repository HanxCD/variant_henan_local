package com.boco.soap.variant.henan.local.shortnumber.gmsc.hw;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class RSNAME_101102 extends VariantValueInvoke {

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {

        Object o = data.get("ROUTE_NAME");

        if (null == o) {
            return new String[] { "[NULL]" };
        } else {
            return new String[] { o.toString() };
        }

    }
}