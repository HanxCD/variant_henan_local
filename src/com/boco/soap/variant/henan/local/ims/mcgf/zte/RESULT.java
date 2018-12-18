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
package com.boco.soap.variant.henan.local.ims.mcgf.zte;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 *
原来的指向	现在的指向
100	300
110	301
120	302
130	303
140	304
150	305
160	306
170	307
180	308
190	309
210	311
220	312
230	313
240	314
250	315
260	316
280	318

 *
 *
 */
public class RESULT extends VariantValueInvoke {

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {

        Object o = data.get("AREA_CODE");
        Object city = data.get("BUSI_CITY");
        String result = null;
        if (null == o) {
            result = "[NULL]";
        } else {
            String areaCode = o.toString();
            //indexOf=-1判断是否存在37或39
            if (areaCode.indexOf("37") != -1) {
                if ((city != null) && city.toString().equals("开封")) {
                    result = "308";
                } else {
                    //areaCode.length()-1取区号最后一位
                    result = "30" + areaCode.charAt(areaCode.length() - 1);
                }
            } else if (areaCode.indexOf("39") != -1) {
                result = "31" + areaCode.charAt(areaCode.length() - 1);
            } else {
                result = "[NULL]";
            }
        }
        return new String[] { result };

    }
}