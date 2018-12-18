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

/**
 * gt gmsc�˾� ��Ϊ   sccpgt E164
 *
 * @author lijixin
 * @Email lijixin2014@boco.com.cn
 * Date 2017-11-22 wanghao
 * 修改为map.put(temp.get("E164").toString(),String.valueOf(temp.get("HLR_DPC")));防止那个HLR_DPC为空时报错
 */
public class RESULTTGT extends VariantValueInvoke {

    private Map<String, String> map = null;

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {
        String city = arg0.getPhysicalAddr();
        String telString = data.get("E164").toString();
        if (city.indexOf("商丘") >= 0) {
            if (!arg0.getName().equals("SHQGS103")) {
                return new String[] { "负荷分担信令点组+原GT" };
            } else {
                return new String[] { "DSP group+OLD GT" };
            }
        }
        String deviceVersion = arg0.getVersion();
        String result = null;
        if (this.map == null) {
            this.map = new HashMap<String, String>();
            //this.initMap(arg3);
        }
        if (this.map.get(telString) != null) {

            result = "SPC";
        } else {
            if ((deviceVersion != null) && deviceVersion.contains("007")) {
                result = "SPC+原GT";
            } else {
                result = "SPC+OLD GT";
            }
        }
        return new String[] { result };

    }

    private void initMap(String dbFile) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "SELECT E164,HLR_DPC FROM Tcm_Local_Gtt_List";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

        for (Map<String, ?> temp : resultList) {
            this.map.put(temp.get("E164").toString(), String.valueOf(temp.get("HLR_DPC")));

        }
    }
}