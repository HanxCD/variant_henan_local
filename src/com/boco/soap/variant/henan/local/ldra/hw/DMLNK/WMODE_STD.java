package com.boco.soap.variant.henan.local.ldra.hw.DMLNK;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class WMODE_STD extends VariantValueInvoke {

    /**
     * @author Sha Jianwen
     * @time 2015-11-18 10:54:10
     * 工作模式
     * 默认值：“CLIENT(客户端)”
     * 指令参数：CLIENT/SERVER;
     * 现网参数：客户端、服务端
     */
    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {

        Object o = data.get("WMODE");
        String result = "CLIENT";
        if (null == o) {
            result = "CLIENT";
        } else {
            if (o.toString().trim().contains("服务端")) {
                result = "SERVER";
            }
        }
        return new String[] { result };
    }
}
