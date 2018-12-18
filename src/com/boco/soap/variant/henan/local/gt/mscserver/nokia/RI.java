package com.boco.soap.variant.henan.local.gt.mscserver.nokia;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class RI extends VariantValueInvoke {
    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {
        Object o = data.get("RI");
        String result = null;
        if (null == o) {
            result = "NULL";
        } else {
            result = o.toString();

            if (StringUtils.isNotEmpty(result)) {
                result = result.replaceAll(",|\\s+|、|，", ",");
            }
        }
        return new String[] { result };
    }
}
