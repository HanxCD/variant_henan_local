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
public class GTADD1254708NO134 extends VariantValueInvoke {

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {

        Object o = data.get("START_GT");
        if (o.toString().trim().equals("1703") || o.toString().trim().equals("1705") || o.toString().trim().equals("1706")) {
            return null;
        }
        String result = null;
        if (null != o) {
            result = "1254708" + o.toString().trim();
            if (o.toString().trim().equals("134")) {
                return "12547081340,12547081341,12547081342,12547081343,12547081344,12547081345,12547081346,12547081347,12547081348".split(",");
            }
        }
        return new String[] { result };
    }
}