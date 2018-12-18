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
public class HW_GMSS_CNACLD_P_ZB_COVERCITY extends VariantValueInvoke {

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {
        String sNetName = arg0.getName();
        String coverCityList = this.getCoverCity(arg0, dbFile);
        String result = "";
        if (coverCityList.length() > 0) {
            for (String tempStr : coverCityList.split(",")) {
                if (arg0.getCorverAddr().indexOf(tempStr) > -1) {
                    result += "," + this.getP(sNetName);
                } else {
                    result += "," + this.getPN(sNetName);
                }
            }
            return result.substring(1).split(",");
        } else {
            return new String[] { this.getP(sNetName) };
        }

    }

    private String getP(String sNetName) {
        String num = sNetName.split("ZZDS")[1];
        String p = "";
        int lastNumber = Integer.parseInt(num);
        int res = lastNumber % 2;
        p = (res == 1 ? "0" : "1");
        return p;
    }

    private String getPN(String sNetName) {
        String num = sNetName.split("ZZDS")[1];
        String p = "";
        int lastNumber = Integer.parseInt(num);
        int res = lastNumber % 2;
        p = (res == 0 ? "0" : "1");
        return p;
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

}