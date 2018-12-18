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
package com.boco.soap.variant.henan.local.ims.sbc.zte;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class IMS_SBC_HW_LAMAPN_MAKE extends VariantValueInvoke {

    private Map<String, String> map = null;
    private final AREA_CODE areaCodeInstence = new AREA_CODE();
    private static final Logger LOGGER = LoggerFactory.getLogger(IMS_SBC_HW_LAMAPN_MAKE.class);

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {
        Object lac10 = data.get("LAC10");
        String LOCTAI = Integer.toHexString(Integer.parseInt(String.valueOf(lac10))).toUpperCase();
        String areaCode = this.areaCodeInstence.getValues(arg0, arg1, data, dbFile)[0];
        LOGGER.info("===areaCode====:{}", areaCode);
        LOGGER.info("===LOCTAI====:{}", LOCTAI);
        String result = null;
        if ((this.map == null) || (this.map.size() == 0)) {
            this.initMap(dbFile, arg0.getName());
        }

        if (this.map.containsKey(LOCTAI + "||" + areaCode)) {
            result = this.map.get(LOCTAI + "||" + areaCode);
        }

        return new String[] { result };
    }

    private void initMap(String dbFile, String neName) {
        this.map = new HashMap<String, String>();
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "select LOCTAI,ANUM,LAMAPN from  V_HW_SBC_LAMAP where devicename='" + neName + "'";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
        for (Map<String, ?> temp : resultList) {
            String busiCity = String.valueOf(temp.get("LOCTAI"));//区县
            String district = String.valueOf(temp.get("ANUM"));//区县
            String lcidAxy = String.valueOf(temp.get("LAMAPN"));//区域识别码

            this.map.put(busiCity + "||" + district, lcidAxy);
        }
    }

    public static void main(String[] args) {
        System.out.println(Integer.toHexString(Integer.parseInt(String.valueOf("14527"))));
    }

}