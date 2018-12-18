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
package com.boco.soap.variant.henan.local.gt.mscserver.hw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;
import com.boco.soap.variant.common.SPC16;

/**
 * gt mscServer�˾� ��Ϊ sccpgt SPC- 16����
 *
 * @author lijixin
 * @Email lijixin2014@boco.com.cn
 * Date 2017-11-22 wanghao
 * 修改为map.put(temp.get("E164").toString(),String.valueOf(temp.get("HLR_DPC")))防止那个HLR_DPC为空时报错
 */
public class SPC1 extends VariantValueInvoke {
    private Map<String, String> map = null;
    private Map<String, String> map2 = null;

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
        Object o = arg0.getPhysicalAddr();
        if (this.map == null) {
            this.map = new HashMap<String, String>();
            //this.initMap(dbFile);
        }
        String telString = data.get("E164").toString();
        String result1 = null;
        if (this.map.get(telString) != null) {
            if (o.toString().contains("商丘")) {
                return new String[] { "000000" };
            }
            result1 = this.map.get(telString).toString();
        } else {
            if (this.map2 == null) {
                this.map2 = new HashMap<String, String>();
                this.initMap2(dbFile);
            }
            String busiStr = arg0.getPhysicalAddr();
            if (this.map2.get(busiStr) != null) {
                result1 = this.map2.get(busiStr).toString();
            }
        }
        if ((result1 != null) && !result1.equals("")) {
            return new String[] { result1 };
        } else {
            return new String[] { "[NULL]" };
        }

    }

    private void initMap(String dbFile) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "SELECT E164,HLR_DPC FROM Tcm_Local_Gtt_List";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

        for (Map<String, ?> temp : resultList) {
            this.map.put(temp.get("E164").toString(), String.valueOf(temp.get("HLR_DPC")));

        }
    }

    private void initMap2(String dbFile) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "select COVER_CITY,STP1_SPC,STP2_SPC from TCM_LOCAL_LSTP ";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

        for (Map<String, ?> temp : resultList) {
            this.map2.put(temp.get("COVER_CITY").toString(), SPC16.spc10to16(temp.get("STP1_SPC").toString()) + "|" + SPC16.spc10to16(temp.get("STP2_SPC").toString()));

        }
    }

}
