package com.boco.soap.variant.henan.local.ims.agcf;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * @ClassName:  FUNCTION_CODE
 * @Description:白名单--功能码
 * @author: caozengran
 * @date:   2017年10月31日 下午3:03:34
 * @version: V1.0
 * @Copyright: 2017 www.boco.com.cn Inc. All rights reserved.
 */
public class FUNCTION_CODE extends VariantValueInvoke {

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {
        return new String[] { data.get("FUNCTION_CODE").toString() };
    }
}
