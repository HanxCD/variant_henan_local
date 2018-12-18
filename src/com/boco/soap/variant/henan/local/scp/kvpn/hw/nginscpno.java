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
package com.boco.soap.variant.henan.local.scp.kvpn.hw;

import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * guyufei
 */
public class nginscpno extends VariantValueInvoke {

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {

        Object o = data.get("BUSI_CITY");
        Object startGt = data.get("START_GT");
        Object areaCode = data.get("AREA_CODE");
        Object areaCity = data.get("BUSI_CITY");
        String accessScpNo = null;
        String result = null;
        if (null == o) {
            result = "[NULL]";
        } else {
            if (null == startGt) {
                accessScpNo = "[NULL]";
            } else {
                accessScpNo = this.getAccessScpNo(startGt.toString().trim(), areaCode.toString().trim(), areaCity, arg3);
            }
            if (!accessScpNo.equals("468")) {
                result = "0|0";
            } else {
                result = "1|468";
            }

        }
        return new String[] { result };
    }

    private String getAccessScpNo(String startGt, String areaCode, Object areaCity, String dbFile) {
        DataQueryUtils utils = DataQueryUtils.getInstance();

        String sql = "select GT,ACCESSSCPNO from TCM_VPMN_SCP where AREA_CODE='" + areaCode + "'";
        if ((areaCity != null) && areaCity.toString().equals("开封")) {
            sql += " and BUSI_CITY='开封'";
        }
        if ((areaCity != null) && areaCity.toString().equals("郑州")) {
            sql += " and BUSI_CITY!='开封'";
        }
        sql += ";";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
        String[] result = new String[resultList.size()];
        String accessScpNo = null;
        if (resultList.size() != 0) {
            for (int i = 0; i < resultList.size(); i++) {
                result[i] = resultList.get(i).get("GT").toString();
                if (startGt.startsWith(result[i])) {
                    accessScpNo = resultList.get(i).get("ACCESSSCPNO").toString();
                    break;
                }
            }
            return accessScpNo;
        } else {
            return "[NULL]";
        }
    }
}