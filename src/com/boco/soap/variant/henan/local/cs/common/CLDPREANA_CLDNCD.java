package com.boco.soap.variant.henan.local.cs.common;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/*
 * Copyright 2017 BOCO Inter-Telecom (he'nan).
 * All rights reserved.
 * project name: variant_henan_local
 * version V1.0
 * -------------------------------------------
 * author: wanghao
 * date: 2017-07-19
 * note: 运营商NDC联通电信大号段在关口局的被叫号码预分析表中CLDPREANA：不用做ALL的那条，因此去掉ALL，这个
 * 是当CLDNCN这个值为HEBPBX、SMXMSC、SMXPBX、	JIZMSC时取值为DEFAULT
 */
public class CLDPREANA_CLDNCD extends VariantValueInvoke {
    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {
        String sDest = "";
        if ((arg0.getName().equals("ZZDS1")) || (arg0.getName().equals("ZZDS2"))) {
            sDest = "99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99";
        } else if ((arg0.getName().equals("ZZDS3")) || (arg0.getName().equals("ZZDS4"))) {
            sDest = "99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99";
        } else if ((arg0.getName().equals("ZZDS5")) || (arg0.getName().equals("ZZDS6"))) {
            sDest = "99,99,99,DEFAULT,99,99,99,99,99,99,99,99,99,99,99,99,DEFAULT,DEFAULT,DEFAULT,99,99,99,99,99,99,99";
        } else if ((arg0.getName().equals("ZZDS7")) || (arg0.getName().equals("ZZDS8"))) {
            sDest = "99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99";
        } else if ((arg0.getName().equals("ZZDS9")) || (arg0.getName().equals("ZZDS10"))) {
            sDest = "99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99";
        } else if ((arg0.getName().equals("ZZDS11")) || (arg0.getName().equals("ZZDS12"))) {
            sDest = "99,99,99,DEFAULT,99,99,99,99,DEFAULT,99,99,99,99,99,99,99,99,99,99,99,99,99,DEFAULT,DEFAULT,DEFAULT,99,99";
        } else if ((arg0.getName().equals("ZZDS13")) || (arg0.getName().equals("ZZDS14"))) {
            sDest = "99,99,DEFAULT,99,99,99,99,99,99,99,99,99,99,99,99";
        }
        return sDest.split(",");
    }
}
