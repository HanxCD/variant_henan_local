/*
 * Copyright 2014 BOCO Inter-Telecom (xi'an).
 * All rights reserved.
 * project name: variant_sichuan
 * version V1.0
 * -------------------------------------------
 * author: lijixin
 * date: 2014-10-31
 * note:
 */
package com.boco.soap.variant.henan.local.gt.mscserver.hw;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 *
 * @author caozengran
 *@time 2016年11月30日 上午11:15:39
 */
public class ENDNUM extends VariantValueInvoke {

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {
        Object o = data.get("E164");
        String result = null;
        if (o == null) {
            result = "[NULL]";
        } else {
            result = String.format("%-13s", new Object[] { o.toString() }).replace(" ", "9");
        }

        return new String[] { result };
    }
}