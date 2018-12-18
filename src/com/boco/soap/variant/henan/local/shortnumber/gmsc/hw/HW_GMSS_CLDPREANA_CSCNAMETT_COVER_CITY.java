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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 *
 * @author Chang
 * @description 获取铁通的呼叫源
 */
public class HW_GMSS_CLDPREANA_CSCNAMETT_COVER_CITY extends VariantValueInvoke {

    private Map<String, String> map = null;

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {
        String sDest = "";
        if (this.map == null) {
            this.map = new HashMap<String, String>();
            this.initMap(dbFile);
        }

        List<String> coverCityList = this.getCoverCity(arg0);
        for (String str : coverCityList) {
            String gtrcStr = this.map.get(str);
            sDest += "," + gtrcStr + "TT";
        }
        return sDest.substring(1).split(",");
    }

    private List<String> getCoverCity(INeElement ne) {
        List<String> cityList = new ArrayList<String>();
        Map<String, String> coverCityMap = ne.getCoverCities();
        Iterator<Entry<String, String>> iter = coverCityMap.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<String, String> entry = iter.next();
            String cityName = entry.getKey().toString();
            String[] citys = cityName.split(",");
            for (int i = 0; i < citys.length; i++) {
                cityList.add(citys[i]);
            }
        }
        return cityList;
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