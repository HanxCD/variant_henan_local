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
 * @description 短号码 gmsc 号首（P）根据网元的奇偶去设置P的值
 */
public class HW_GMSS_CNACLD_P1 extends VariantValueInvoke {

    private Map<String, String> map = null;// 设备覆盖地市map
    private final Map<String, String> deviceMap = new HashMap<String, String>();// 设备主备map

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {
        if (this.map == null) {
            this.map = new HashMap<String, String>();
            this.initMap(dbFile);
        }
        String sNetName = arg0.getName();
        String p = "";
        String backDevice = this.deviceMap.get(sNetName);
        // 对应关口局如果覆盖地市包含接入地则做，否则无需制作
        //		if (accessArea != null && coverCity.contains(accessArea.toString())) {
        //			p = this.getP(backDevice);
        //		} else {
        //			return null;
        //		}
        p = this.getP(backDevice);
        return new String[] { p };

    }

    private String getP(String sNetName) {
        String num = sNetName.split("ZZDS")[1];
        String p = "";
        int lastNumber = Integer.parseInt(num);
        int res = lastNumber % 2;
        p = (res == 1 ? "0" : "1");
        return p;
    }

    private void initMap(String dbFile) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "SELECT A.COVER_CITY AS COVER_CITY,A.DEVICENAME AS DEVICENAME,B.DEVICENAME AS DEVICENAME_BACK,B.COVER_CITY AS COVER_CITY_BACK FROM (SELECT COVER_CITY, DEVICENAME, GROUP_NAME FROM TCM_HW_GMSS_MTB_REL) A LEFT JOIN TCM_HW_GMSS_MTB_REL B ON A.GROUP_NAME = B.GROUP_NAME AND A.DEVICENAME != B.DEVICENAME";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

        for (Map<String, ?> temp : resultList) {
            this.map.put(temp.get("DEVICENAME").toString(), temp.get("COVER_CITY").toString());
            this.deviceMap.put(temp.get("DEVICENAME").toString(), temp.get("DEVICENAME_BACK").toString());
        }
    }
}