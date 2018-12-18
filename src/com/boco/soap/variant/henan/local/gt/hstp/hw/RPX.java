/*
 * Copyright 2014 BOCO Inter-Telecom (xi'an).
 * All rights reserved.
 * project name: variant_sichuan
 * version V1.0
 * -------------------------------------------
 * author: lijixin
 * date: 2014-11-3
 * note:
 */
package com.boco.soap.variant.henan.local.gt.hstp.hw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;
import com.boco.soap.variant.common.SPC16;

/**
 * gt LSTP ��Ϊ sccpgt SCCPѰַ���Ե�����
 *
 * @author lijixin
 * @Email lijixin2014@boco.com.cn
 *
 */
public class RPX extends VariantValueInvoke {
    private Map<String, String> map = null;
    private final Map<String, Map<String, String>> indexMap = new HashMap<String, Map<String, String>>();

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.boco.soap.check.standvalue.valueinvoke.IValueInvoke#getValues(com
     * .boco.soap.common.pojo.INeElement,
     * com.boco.soap.common.pojo.solution.IInstructionParameter, java.util.Map,
     * java.lang.String)
     */
    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {
        String provinceStr = data.get("PROVINCE").toString();
        String[] RPXStr = this.getRPX(arg0.getName(), provinceStr, dbFile);
        return RPXStr;
    }

    /**
     * @param name
     * @param spcStr
     * @param dbFile
     * @return
     */
    private String[] getRPX(String neName, String provinceStr, String dbFile) {
        if (this.map == null) {
            this.initMap(dbFile);
        }
        String spc = null;
        if (this.map.get(provinceStr) != null) {
            spc = this.map.get(provinceStr).toString();

        }

        if (spc == null) {
            return new String[] { "[NULL]" };
        } else {
            if (!this.indexMap.containsKey(neName)) {
                this.queryIndexMap(neName, dbFile);
            }
            String result = this.indexMap.get(neName).get(spc);
            if (result == null) {
                result = "[NULL]";
            }
            return new String[] { result };
        }

    }

    private void queryIndexMap(String neName, String dbFile) {
        Map<String, String> tempMap = new HashMap<String, String>();
        this.indexMap.put(neName, tempMap);
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "select internal_nw_code spc , b.SCCP_STRATEGY_NAME idx from HW_STP_N7DSP a,HW_STP_SCCPADDRPL b  where  a.devicename='" + neName
                + "' and b.devicename='" + neName + "' and a.dest_spc_idx = b.dest0_idx";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
        for (Map<String, ?> temp : resultList) {
            tempMap.put(temp.get("spc").toString(), temp.get("idx").toString());
        }
    }

    private void initMap(String dbFile) {
        this.map = new HashMap<String, String>();
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "SELECT PROVINCE,SPC FROM TCM_ALL_LSTPHSTP WHERE bureau_data_no LIKE '%H1%' OR bureau_data_no LIKE '%HSTP1%'  ORDER BY BUREAU_DATA_NO ASC";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

        for (Map<String, ?> temp : resultList) {
            this.map.put(temp.get("PROVINCE").toString(), SPC16.spc10to16(temp.get("SPC").toString()));

        }
    }

}
