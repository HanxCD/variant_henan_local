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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * guyufei
 */
public class startGtBegin extends VariantValueInvoke {
    private Map<String, List<String>> map = null;

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {

        Object o = data.get("START_GT");

        String result = null;

        if (null == o) {
            result = "[NULL]";
        } else {
            if (this.map == null) {
                this.map = new HashMap<String, List<String>>();
                this.initMap(arg3, arg0.getName());
            }
            List<String> resultList = this.map.get(data.get("BUSI_CITY").toString());
            for (int i = 0; (resultList != null) && (i < resultList.size()); i++) {
                String[] num = resultList.get(i).split("\\|");
                if ((num.length > 1) && StringUtils.isNotBlank(o.toString())) {
                    if (o.toString().equals(num[0])) {
                        result = String.format("%s0000|%s9999", num[0], num[1]);
                        break;
                    } else {
                        result = "";
                    }
                } else {
                    if (o.toString().equals(num[0])) {
                        result = String.format("%s0000|%s9999", num[0]);
                        break;
                    }
                }
            }
        }
        return new String[] { result };
    }

    private void initMap(String dbFile, String deviceName) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "SELECT BUSI_CITY,START_GT FROM  TCM_CMCC_BOSS_GT m WHERE START_GT not IN (SELECT STARTNO FROM  HW_SCP_KVPN_ISDNTYPE q WHERE DEVICENAME='" + deviceName + "') and BUSI_CITY IS NOT NULL ORDER BY START_GT ";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

        for (Map<String, ?> temp : resultList) {
            List<String> tempList = this.map.get(temp.get("BUSI_CITY").toString());
            if (tempList == null) {
                tempList = new ArrayList<String>();
                this.map.put(temp.get("BUSI_CITY").toString(), tempList);
            }
            tempList.add(temp.get("START_GT").toString());
        }
        this.map = this.getConstectiveNumber(this.map);
    }

    private Map<String, List<String>> getConstectiveNumber(Map<String, List<String>> map) {
        Map<String, List<String>> resultMap = new HashMap<String, List<String>>();
        List<String> resultList = null;
        for (Entry<String, List<String>> entry : map.entrySet()) {
            List<String> list = entry.getValue();
            Collections.sort(list);
            int start = 0;
            resultList = resultMap.get(entry.getKey());
            if (resultList == null) {
                resultList = new ArrayList<String>();
                resultMap.put(entry.getKey(), resultList);
            }
            if (list.size() == 1) {
                String telNum = String.format("%s|%s", list.get(0), list.get(0));
                resultList.add(telNum);
            } else {
                for (int i = 1; i < list.size(); i++) {
                    int sub = Integer.valueOf(list.get(i)) - Integer.valueOf(list.get(i - 1));
                    if (sub == 1) {
                        //                        continue;
                    } else {
                        String telNum = String.format("%s|%s", list.get(start), list.get(i - 1));
                        resultList.add(telNum);
                        start = i;
                    }
                    if (i == (list.size() - 1)) {
                        String telNum = String.format("%s|%s", list.get(start), list.get(i));
                        resultList.add(telNum);
                    }
                }
            }
        }
        return resultMap;
    }

    public static void main(String[] args) {
        startGtBegin begin = new startGtBegin();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        List<String> resultListTmp = new ArrayList<String>();
        resultListTmp.add("1349390");
        /*resultListTmp.add("1349391");
        resultListTmp.add("1349392");
        resultListTmp.add("1349394");
        resultListTmp.add("1349395");
        resultListTmp.add("1349396");
        resultListTmp.add("1349397");*/
        resultListTmp.add("1349390");
        map.put("河南", resultListTmp);
        Object o = "1349390";
        map = begin.getConstectiveNumber(map);
        String result = null;
        List<String> resultList = map.get("河南");
        for (int i = 0; (resultList != null) && (i < resultList.size()); i++) {
            String[] num = resultList.get(i).split("\\|");
            if ((num.length > 1) && StringUtils.isNotBlank(o.toString())) {
                if (o.toString().equals(num[0])) {
                    result = String.format("%s00000|%s99999", num[0], num[1]);
                    break;
                } else {
                    result = "";
                }
            } else {
                if (o.toString().equals(num[0])) {
                    result = String.format("%s00000|%s99999", num[0]);
                    break;
                }
            }
        }
        System.out.println("-----------------------------");
        System.out.println(result);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String resultString = dateFormat.format(new Date());
        System.out.println(resultString);
    }
}