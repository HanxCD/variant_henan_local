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
 *
 * @author Chang
 * @description 获取铁通的呼叫源
 */
public class HW_GMSS_CLDPREANA_CSCNAMETT_KF extends VariantValueInvoke {

    private Map<String, String> citymap = null;

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {
        String sDest = "";
        if (this.citymap == null) {
            this.citymap = new HashMap<String, String>();
            this.initCityMap(dbFile, arg0.getName());
        }

        String cityStr = this.citymap.get(arg0.getName());
        if (cityStr.indexOf("开封") > -1) {
            sDest = "KFTT";
        } else {
            return null;
        }

        return new String[] { sDest };
    }

    private void initCityMap(String dbFile, String sNename) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "select '" + sNename + "' DEVICENAME,COVER_CITY from TCM_HW_GMSS_MTB_REL where GROUP_NAME in (select GROUP_NAME from TCM_HW_GMSS_MTB_REL where DEVICENAME='" + sNename + "')";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
        String Sbcity = "";
        for (Map<String, ?> temp : resultList) {
            Sbcity += "," + temp.get("COVER_CITY").toString();
        }
        this.citymap.put(sNename, Sbcity.substring(1));
    }
}