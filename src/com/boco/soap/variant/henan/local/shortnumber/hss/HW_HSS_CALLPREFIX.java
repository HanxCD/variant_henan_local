package com.boco.soap.variant.henan.local.shortnumber.hss;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * 短号码华为HSS 呼叫字冠（PFX）,有12 * 95 * 96*的打匹配在calledna中，配置为other
 * 配置为local或international等其余类型的会到OFATPL中继续;目前配置的逻辑为只查calledna表，判断是否为other
 * @author wanghao2016
 *
 */

public class HW_HSS_CALLPREFIX extends VariantValueInvoke {
    @Override
    public String[] getValues(INeElement ne, IInstructionParameter arg1, Map<String, ?> data, String arg3) {
        Object b = data.get("BUSI_SCENARIO");
        Object o = data.get("START_GT");
        String result = null;
        if (String.valueOf(b).equals("是")) {
            return null;
        } else {
            if (null == o) {
                result = "[NULL]";
            } else {
                result = o.toString();
            }

            return new String[] { result };

        }
    }

}