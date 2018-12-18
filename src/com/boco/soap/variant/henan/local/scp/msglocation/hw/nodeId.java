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
public class nodeId extends VariantValueInvoke {

    private Map<String, String> map = null;
    private Map<String, String> map1 = null;

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {

        Object mscId = data.get("MSCID");
        Object provinceStr = String.valueOf(data.get("PROVINCE"));
        String nodeId = "";
        //Object pro = data.get("(PROVINCE");
        if (this.map == null) {
            this.map = new HashMap<String, String>();
            this.initMap(dbFile);
        }
        if (this.map1 == null) {
            this.map1 = new HashMap<String, String>();
            this.initMap1(dbFile);
        }
        if (null == mscId) {
            return new String[] { "[NULL]" };
        } else {
            if (!this.map1.containsKey(provinceStr)) {
                return null;
            }
            nodeId = this.map.get(mscId.toString().trim());
            if (nodeId == null) {
                return new String[] { "HW_SCP_SYS_NETCONFIG表中没有该MSCID:" + mscId.toString() };
            } else {
                return new String[] { nodeId };
            }
        }

    }

    private void initMap(String dbFile) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "select PARA2,ADDRESS from HW_SCP_SYS_NETCONFIG where NOTE_TYPE=4; ";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

        for (Map<String, ?> temp : resultList) {
            this.map.put(temp.get("ADDRESS").toString().substring(2).trim(), temp.get("PARA2").toString().trim());

        }
    }

    private void initMap1(String dbFile) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "select PROVINCE,IP_NODE from TCM_IP_PROVINCE";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

        for (Map<String, ?> temp : resultList) {
            this.map1.put(temp.get("PROVINCE").toString().trim(), temp.get("IP_NODE").toString().trim());

        }
    }
}