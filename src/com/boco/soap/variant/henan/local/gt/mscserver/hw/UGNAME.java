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

import java.util.HashMap;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 *
 * @author caozengran
 *@time 2016年11月30日 上午11:15:39
 */
public class UGNAME extends VariantValueInvoke {
    private final Map<String, String> areacodeMap = new HashMap<String, String>();

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {
        String neCity = arg0.getPhysicalAddr();
        String result = "";
        if ((neCity != null) && (neCity.length() > 0)) {
            neCity = neCity.split(",")[0];
            this.init();
            result = "8613800%s500";
            if (this.areacodeMap.containsKey(neCity)) {
                result = String.format(result, this.areacodeMap.get(neCity));
            }
        }
        return new String[] { result };

    }

    private void init() {
        this.areacodeMap.put("商丘", "370");
        this.areacodeMap.put("郑州", "371");
        this.areacodeMap.put("安阳", "372");
        this.areacodeMap.put("新乡", "373");
        this.areacodeMap.put("许昌", "374");
        this.areacodeMap.put("平顶山", "375");
        this.areacodeMap.put("潢川", "376");
        this.areacodeMap.put("信阳", "376");
        this.areacodeMap.put("南阳", "377");
        this.areacodeMap.put("洛阳", "379");
        this.areacodeMap.put("焦作", "391");
        this.areacodeMap.put("鹤壁", "392");
        this.areacodeMap.put("濮阳", "393");
        this.areacodeMap.put("周口", "394");
        this.areacodeMap.put("漯河", "395");
        this.areacodeMap.put("驻马店", "396");
        this.areacodeMap.put("三门峡", "398");
        this.areacodeMap.put("济源", "391");
        this.areacodeMap.put("开封", "371");

    }

}