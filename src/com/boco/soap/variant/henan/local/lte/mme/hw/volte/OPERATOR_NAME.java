package com.boco.soap.variant.henan.local.lte.mme.hw.volte;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * @ClassName:  OPERATOR_NAME
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: caozengran
 * @date:   2017年3月24日 下午5:58:56
 * @version: V1.0
 * @Copyright: 2017 www.boco.com.cn Inc. All rights reserved.
 */
public class OPERATOR_NAME extends VariantValueInvoke {

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {

        Object objNeType = data.get("FORE_OPERATORS");
        return new String[] { String.valueOf(objNeType) };

    }
}
