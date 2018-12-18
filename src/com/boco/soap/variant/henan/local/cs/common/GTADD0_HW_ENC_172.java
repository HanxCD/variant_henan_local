package com.boco.soap.variant.henan.local.cs.common;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * ��Ϊ�˾ֺ����ֹ�
 *
 * @author baixp
 * @Email
 *
 */
public class GTADD0_HW_ENC_172 extends VariantValueInvoke {

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {

        Object o = data.get("START_GT");
        Object b = data.get("COMP_NAME");
        String result = null;
        if (o == null) {
            result = "[NULL]";
        } else {
            String telString1 = b.toString();
            if (!telString1.startsWith("移动")) {
                return null;
            }
            result = "0" + o.toString().trim();
        }
        return new String[] { result };

    }
}