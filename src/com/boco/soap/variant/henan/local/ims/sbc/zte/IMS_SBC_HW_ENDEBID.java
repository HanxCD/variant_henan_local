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
package com.boco.soap.variant.henan.local.ims.sbc.zte;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class IMS_SBC_HW_ENDEBID extends VariantValueInvoke {

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {

        Object enodeID = data.get("ENODEB_ID");
        String result = "";
        if (enodeID != null) {
            String hexStr = Integer.toHexString(Integer.parseInt(String.valueOf(enodeID)));
            result = String.format("%5s", hexStr).replace(" ", "0");
        }
        return new String[] { result };
    }

    public static void main(String[] args) {
        String enodeID = "12801";
        String hexStr = Integer.toHexString(Integer.parseInt(String.valueOf(enodeID)));
        String result = String.format("%5s", hexStr).replace(" ", "0");
        System.out.println(result);
    }

}