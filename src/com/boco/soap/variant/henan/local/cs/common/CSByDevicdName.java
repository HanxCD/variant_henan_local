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
public class CSByDevicdName extends VariantValueInvoke {
    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {
        String sDest = "";
        if (arg0.getName().equals("ZZDS1") || arg0.getName().equals("ZZDS2")) {
            sDest = "TUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,TUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧";
        } else if (arg0.getName().equals("ZZDS3") || arg0.getName().equals("ZZDS4")) {
            sDest = "ISUP中继侧,ISUP中继侧,ISUP中继侧,TUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,TUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,TUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧";
        } else if (arg0.getName().equals("ZZDS5") || arg0.getName().equals("ZZDS6")) {
            sDest = "TUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,TUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,TUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,TUP中继侧";
        } else if (arg0.getName().equals("ZZDS7") || arg0.getName().equals("ZZDS8")) {
            sDest = "TUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,TUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧";
        } else if (arg0.getName().equals("ZZDS9") || arg0.getName().equals("ZZDS10")) {
            sDest = "TUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,TUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,TUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,TUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧";
        } else if (arg0.getName().equals("ZZDS11") || arg0.getName().equals("ZZDS12")) {
            sDest = "TUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,TUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,TUP中继侧,ISUP中继侧,ISUP中继侧,TUP中继侧,ISUP中继侧,TUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,TUP中继侧";
        } else if (arg0.getName().equals("ZZDS13") || arg0.getName().equals("ZZDS14")) {
            sDest = "TUP中继侧,ISUP中继侧,ISUP中继侧,TUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,TUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧,ISUP中继侧";
        }/* else if (arg0.getName().equals("ZZDS101") || arg0.getName().equals("ZZDS102")) {
            sDest = "语音杂志,动感短信,CLVR";
         }*/

        return sDest.split(",");
    }
}