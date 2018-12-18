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
 * note: 运营商NDC联通电信大号段在关口局的被叫号码预分析表中CLDPREANA：不用做ALL的那条，因此去掉ALL
 */
public class CSCNameByDevicdName1 extends VariantValueInvoke {
    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {
        String sDest = "";
        if ((arg0.getName().equals("ZZDS1")) || (arg0.getName().equals("ZZDS2"))) {
            sDest = "ANYDX,ANYLT,ANYWT,KAFDX,KAFDXC,KAFLT,KAFWT,PDSDX,PDSWT,XIXDX,XIXWT,ZHKDX,ZHKWT,ZMDDX,ZMDLT,ZMDPBX,ZMDWT";
        } else if ((arg0.getName().equals("ZZDS3")) || (arg0.getName().equals("ZZDS4"))) {
            sDest = "LUYDX,LUYDXC,LUYLT,LUYWT,NAYDX,NAYDXC,NAYLT,NAYWT,PUYDX,PUYLT,PUYWT,XCHDX,XCHDXC,XCHLT,XCHWT,XIYDX,XIYDXC,XIYLT,XIYWT,ZZDX,ZZDXC,ZZLT,ZZWT";
        } else if ((arg0.getName().equals("ZZDS5")) || (arg0.getName().equals("ZZDS6"))) {
            sDest = "HEBDX,HEBDXC,HEBLT,HEBPBX,HEBWT,LUYDX,LUYDXC,LUYLT,LUYWT,NAYDX,NAYDXC,NAYLT,NAYWT,SHQDX,SHQLT,SHQWT,SMXDX,SMXMSC,SMXPBX,SMXPBX,SMXWT,ZZDX,ZZDXC,ZZLT,ZZWT,矿务局-HEB";
        } else if ((arg0.getName().equals("ZZDS7")) || (arg0.getName().equals("ZZDS8"))) {
            sDest = "ANYDX,ANYLT,ANYWT,KAFDX,KAFDXC,KAFLT,KAFWT,PDSDX,PDSWT,XIXDX,XIXWT,ZHKDX,ZHKLT,ZHKWT,ZMDDX,ZMDLT,ZMDPBX,ZMDWT";
        } else if ((arg0.getName().equals("ZZDS9")) || (arg0.getName().equals("ZZDS10"))) {
            sDest = "LUYDX,LUYDXC,LUYLT,LUYWT,NAYDX,NAYDXC,NAYLT,NAYWT,PUYDX,PUYLT,PUYWT,XCHDX,XCHDXC,XCHLT,XCHWT,XIYDX,XIYDXC,XIYLT,XIYWT,ZZDX,ZZDXC,ZZLT,ZZWT";
        } else if ((arg0.getName().equals("ZZDS11")) || (arg0.getName().equals("ZZDS12"))) {
            sDest = "HEBDX,HEBDXC,HEBLT,HEBPBX,HEBWT,JIZDX,JIZLT,JIZMSC,JIZWT,LUHDX,LUHDXC,LUHLT,LUHWT,LUYDX,LUYDXC,LUYLT,LUYWT,SMXDX,SMXMSC,SMXPBX,SMXPBX,SMXWT,ZZDX,ZZDXC,ZZLT,ZZWT,矿务局-HEB";
        } else if ((arg0.getName().equals("ZZDS13")) || (arg0.getName().equals("ZZDS14"))) {
            sDest = "JIZDX,JIZLT,JIZMSC,JIZWT,LUHDX,LUHDXC,LUHLT,LUHWT,NAYDX,NAYDXC,NAYLT,NAYWT,SHQDX,SHQLT,SHQWT";
        }
        return sDest.split(",");
    }
}
