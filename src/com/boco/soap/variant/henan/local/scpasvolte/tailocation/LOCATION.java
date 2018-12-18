package com.boco.soap.variant.henan.local.scpasvolte.tailocation;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;
import java.util.Map;

public class LOCATION extends VariantValueInvoke
{
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3)
    {
        Object o = data.get("REGIONID");

        String result = null;

        if (o == null)
            result = "[NULL]";
        else {
            result = o.toString().trim();
        }

        return new String[] { result };
    }
}