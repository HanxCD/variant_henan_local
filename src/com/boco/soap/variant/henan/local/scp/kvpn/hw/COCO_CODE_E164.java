package com.boco.soap.variant.henan.local.scp.kvpn.hw;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class COCO_CODE_E164 extends VariantValueInvoke {
    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {
        Object cococode = data.get("E164");
        String result = "[NULL]";
        if (cococode != null) {
            result = cococode.toString().substring(2, 5);
        }
        return new String[] { result };
    }
}