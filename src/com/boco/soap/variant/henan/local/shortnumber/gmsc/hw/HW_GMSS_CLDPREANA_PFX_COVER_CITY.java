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
package com.boco.soap.variant.henan.local.shortnumber.gmsc.hw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * 短号码 gmsc 呼叫字冠（PFX）
 *
 */
public class HW_GMSS_CLDPREANA_PFX_COVER_CITY extends VariantValueInvoke {

    private final Map<String, String> areacodeMap = new HashMap<String, String>();

    @Override
    public String[] getValues(INeElement ne, IInstructionParameter arg1, Map<String, ?> data, String arg3) {
        String coverCityList = this.getCoverCity(ne, arg3);
        Object o = data.get("START_GT");
        String result = "";
        String start_gt = "";
        if (null == o) {
            result = "[NULL]";
        } else {
            result = o.toString();
            if (result.startsWith("037") || result.startsWith("039")) {
                return null;
            }
            if (this.areacodeMap.size() == 0) {
                this.init();
            }
            System.out.println(coverCityList.toString());
            for (String tempStr : coverCityList.split(",")) {
                if (this.areacodeMap.containsKey(tempStr)) {
                    start_gt += "," + "0" + this.areacodeMap.get(tempStr) + o.toString();
                } else {
                    start_gt += "," + "0" + tempStr + o.toString();
                }
            }
        }
        start_gt = start_gt.substring(1);
        return start_gt.split(",");

    }

    private String getCoverCity(INeElement ne, String dbFile) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "SELECT BUSI_CITY FROM TCM_HW_GMSS_MTB_REL WHERE GROUP_NAME =(SELECT TT.GROUP_NAME FROM TCM_HW_GMSS_MTB_REL TT WHERE TT.DEVICENAME ='" + ne.getName() + "') ";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
        String coverCity = "";
        for (Map<String, ?> temp : resultList) {
            coverCity += "," + temp.get("BUSI_CITY");
        }
        return coverCity.substring(1);
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