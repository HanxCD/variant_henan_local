/*
 * Copyright 2014 BOCO Inter-Telecom (xi'an).
 * All rights reserved.
 * project name: variant_sichuan
 * version V1.0
 * -------------------------------------------
 * author: caoran
 * date: 2018-06-15
 * note: 根据网元的覆盖地市拼音简称+不同的运营商名称缩写
 */
package com.boco.soap.variant.henan.local.shortnumber.gmsc.hw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class HW_GMSS_CNACLD_RSNAME_COVERCITY extends VariantValueInvoke {

    private final Map<String, String> map = new HashMap<String, String>();

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {
        this.initMap(dbFile);
        Object o = data.get("ROUTE_NAME");
        String[] sCityList = arg0.getCorverAddr().split(",");

        String result = null;
        if (null == o) {
            result = "[NULL]";
        } else {
            for (int i = 0; i < sCityList.length; i++) {
                String sCity = sCityList[i];
                if (o.toString().equals("电信")) {
                    result += "," + this.map.get(sCity) + "DX";
                }
                if (o.toString().equals("网通") || o.toString().equals("联通")) {
                    result += "," + this.map.get(sCity) + "LT";
                }
                if (o.toString().equals("铁通")) {
                    result += "," + this.map.get(sCity) + "TT";
                }
            }
            if (StringUtils.isNotEmpty(result)) {
                result = result.substring(1);
            }
        }

        return result.split(",");

    }

    private void initMap(String dbFile) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "select BUSI_CITY,SIMPLENAME from TCM_LOCAL_CITY_LIST";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

        for (Map<String, ?> temp : resultList) {
            this.map.put(temp.get("BUSI_CITY").toString(), temp.get("SIMPLENAME").toString());

        }
    }
}