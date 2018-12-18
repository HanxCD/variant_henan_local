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
package com.boco.soap.variant.henan.local.scp.msglocation.hw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 *
 */
public class msgIdAct1 extends VariantValueInvoke {

    private Map<String, String> map1 = null;
    private Map<String, String> map2 = null;

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {

        Object mscId = data.get("MSCID");
        //Object pro = data.get("(PROVINCE");
        String msgId = "";
        String pro = "";
        if (this.map1 == null) {
            this.map1 = new HashMap<String, String>();
            this.initMap1(dbFile);
        }
        if (this.map2 == null) {
            this.map2 = new HashMap<String, String>();
            this.initMap2(dbFile);
        }
        if (null == mscId) {
            return new String[] { "没有该MSCID" };
        } else {

            pro = this.map2.get(mscId.toString().trim());
            if (pro == null) {
                return new String[] { "没有该省份" };
            }
            msgId = this.map1.get(pro.toString().trim());
            if (msgId == null) {
                return new String[] { "TCM_IP_PROVINCE表中没有该省份" };
            } else {
                String msgIdArr[] = msgId.split("\\|");
                if (msgIdArr.length > 1) {
                    msgId = msgIdArr[1];
                } else {
                    msgId = "";
                }
                return new String[] { msgId.replace("-", "|") };
            }
        }

        //return msgId;
    }

    private void initMap1(String dbFile) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "select PROVINCE,MESSAGE_ID from TCM_IP_PROVINCE ";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

        for (Map<String, ?> temp : resultList) {
            this.map1.put(temp.get("PROVINCE").toString().trim(), temp.get("MESSAGE_ID").toString().trim());

        }
    }

    private void initMap2(String dbFile) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "select PROVINCE,MSCID from TCM_MSC_TRANSLATE ";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

        for (Map<String, ?> temp : resultList) {
            this.map2.put(temp.get("MSCID").toString().trim(), temp.get("PROVINCE").toString().trim());

        }
    }
}