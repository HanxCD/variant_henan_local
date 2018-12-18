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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class IMS_SBC_HW_DNUM extends VariantValueInvoke {

    private Map<String, String> map = null;

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {

        Object busiCity = data.get("BUSI_CITY");//获取区县
        Object district = data.get("DISTRICT");//获取区县
        String result = null;
        if ((this.map == null) || (this.map.size() == 0)) {
            this.initMap(dbFile);
        }

        if (this.map.containsKey(String.valueOf(busiCity) + "||" + String.valueOf(district))) {
            result = this.map.get(String.valueOf(busiCity) + "||" + String.valueOf(district));
        }

        return new String[] { result };
    }

    private void initMap(String dbFile) {
        this.map = new HashMap<String, String>();
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "select BUSI_CITY,DISTRICT,LCID_AXY from  TCM_TAC_LAC_ASSIST";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
        for (Map<String, ?> temp : resultList) {
            String busiCity = String.valueOf(temp.get("BUSI_CITY"));//区县
            String district = String.valueOf(temp.get("DISTRICT"));//区县
            String lcidAxy = String.valueOf(temp.get("LCID_AXY"));//区域识别码

            this.map.put(busiCity + "||" + district, lcidAxy);
        }
    }

}