package com.boco.soap.variant.henan.local.cs.common;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * @time 2017年2月24日 下午2:55:07
 * @author caozengran
 *
 */
public class CSCNameByDevicdName extends VariantValueInvoke {
    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {
        String sDest = "";
        if (arg0.getName().equals("ZZDS1") || arg0.getName().equals("ZZDS2")) {
            sDest = "ALL,ANYDX,ANYLT,ANYWT,KAFDX,KAFDXC,KAFLT,KAFTT,KAFWT,PDSDX,PDSTT,PDSWT,XIXDX,XIXWT,ZHKDX,ZHKWT,ZMDDX,ZMDLT,ZMDPBX,ZMDWT";
        } else if (arg0.getName().equals("ZZDS3") || arg0.getName().equals("ZZDS4")) {
            sDest = "LUYDX,LUYDXC,LUYLT,LUYTT,LUYWT,NAYDX,NAYDXC,NAYLT,NAYTT,NAYWT,PUYDX,PUYLT,PUYTT,PUYWT,XCHDX,XCHDXC,XCHLT,XCHTT,XCHWT,XIYDX,XIYDXC,XIYLT,XIYTT,XIYWT,ZZDX,ZZDXC,ZZLT,ZZTT,ZZWT";
        } else if (arg0.getName().equals("ZZDS5") || arg0.getName().equals("ZZDS6")) {
            sDest = "ALL,HEBDX,HEBDXC,HEBLT,HEBPBX,HEBTT,HEBWT,LUYDX,LUYDXC,LUYLT,LUYTT,LUYWT,NAYDX,NAYDXC,NAYLT,NAYTT,NAYWT,SHQDX,SHQLT,SHQTT,SHQWT,SMXDX,SMXMSC,SMXPBX,SMXPBX,SMXWT,ZZDX,ZZDXC,ZZLT,ZZTT,ZZWT,矿务局-HEB";
        } else if (arg0.getName().equals("ZZDS7") || arg0.getName().equals("ZZDS8")) {
            sDest = "ALL,ANYDX,ANYLT,ANYTT,ANYWT,KAFDX,KAFDXC,KAFLT,KAFTT,KAFWT,PDSDX,PDSTT,PDSWT,XIXDX,XIXTT,XIXWT,ZHKDX,ZHKLT,ZHKWT,ZMDDX,ZMDLT,ZMDPBX,ZMDTT,ZMDWT";
        } else if (arg0.getName().equals("ZZDS9") || arg0.getName().equals("ZZDS10")) {
            sDest = "ALL,LUYDX,LUYDXC,LUYLT,LUYTT,LUYWT,NAYDX,NAYDXC,NAYLT,NAYTT,NAYWT,PUYDX,PUYLT,PUYTT,PUYWT,XCHDX,XCHDXC,XCHLT,XCHTT,XCHWT,XIYDX,XIYDXC,XIYLT,XIYTT,XIYWT,ZZDX,ZZDXC,ZZLT,ZZTT,ZZWT";
        } else if (arg0.getName().equals("ZZDS11") || arg0.getName().equals("ZZDS12")) {
            sDest = "ALL,HEBDX,HEBDXC,HEBLT,HEBPBX,HEBTT,HEBWT,JIZDX,JIZLT,JIZMSC,JIZTT,JIZWT,LUHDX,LUHDXC,LUHLT,LUHTT,LUHWT,LUYDX,LUYDXC,LUYLT,LUYTT,LUYWT,SMXDX,SMXMSC,SMXPBX,SMXPBX,SMXWT,ZZDX,ZZDXC,ZZLT,ZZTT,ZZWT,矿务局-HEB";
        } else if (arg0.getName().equals("ZZDS13") || arg0.getName().equals("ZZDS14")) {
            sDest = "ALL,JIZDX,JIZLT,JIZMSC,JIZWT,LUHDX,LUHDXC,LUHLT,LUHTT,LUHWT,NAYDX,NAYDXC,NAYLT,NAYTT,NAYWT,SHQDX,SHQLT,SHQTT,SHQWT";
        }/* else if (arg0.getName().equals("ZZDS101") || arg0.getName().equals("ZZDS102")) {
            sDest = "语音杂志,动感短信,CLVR";
         }*/

        return sDest.split(",");
    }

}