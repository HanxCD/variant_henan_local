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
 * gt gmsc�˾� ��Ϊ   sccpgt E164
 *
 * @author lijixin
 * @Email lijixin2014@boco.com.cn
 *
 */
public class STP extends VariantValueInvoke {
    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {
        /*String city=data.get("BUSI_CITY").toString();
        if (city.indexOf("商丘") >= 0) {
        	return new String[] { "STP3" };
        } else {
        	return new String[] { "STP1" };
        }*/
        return new String[] { "STP1" };
    }
}