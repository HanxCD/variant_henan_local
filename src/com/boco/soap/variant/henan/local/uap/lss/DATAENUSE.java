package com.boco.soap.variant.henan.local.uap.lss;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class DATAENUSE extends VariantValueInvoke {
    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {
        String colStr = "CSU、CCU、CDB、CPC";
        Object o = data.get("PANELTYPE");
        String result = null;
        if (o == null) {
            result = "[NULL]";
        } else {
            result = o.toString().trim();
            if (colStr.indexOf(result) > -1) {
                result = "否";
            } else {
                result = "是";
            }
        }

        return new String[] { result };
    }
}
