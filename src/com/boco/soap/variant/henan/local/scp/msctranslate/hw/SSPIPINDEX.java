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
package com.boco.soap.variant.henan.local.scp.msctranslate.hw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * guyufei
 */
public class SSPIPINDEX extends VariantValueInvoke {

    List<String> scpNoList = null;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {
        if (this.scpNoList == null) {
            this.initMap(arg3, arg0.getName());
            this.logger.info("***********************************");
            this.logger.info("SSPIPINDEX:scpNoMap进行初始化");
        }
        String result = "";
        int currentValue = Integer.valueOf(this.scpNoList.get(0));
        while (this.scpNoList.contains(currentValue + "")) {
            currentValue++;
        }
        result = currentValue + "";
        this.logger.info("返回值为:" + result);

        int preIndex = this.scpNoList.indexOf(String.valueOf(currentValue - 1));
        this.logger.info("返回值为:" + this.scpNoList.subList(0, preIndex));
        this.scpNoList.subList(0, preIndex).clear();
        this.scpNoList.set(0, result);
        return new String[] { result };
    }

    private void initMap(String dbFile, String deviceName) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        this.scpNoList = new ArrayList<String>();
        String sql = "SELECT DEVICENAME,GROUP_CONCAT(SSPIPINDEX) SSPIPINDEX    FROM HW_SCP_SSPIPINFO  WHERE CAST(SSPIPINDEX AS INT) BETWEEN 2000 AND 2999  OR CAST(SSPIPINDEX AS INT) BETWEEN 4000 AND 4999 OR CAST(SSPIPINDEX AS INT) BETWEEN 5000 AND 5999 OR CAST(SSPIPINDEX AS INT) BETWEEN 7000 AND 8999 OR CAST(SSPIPINDEX AS INT) BETWEEN 11000 AND 55535 and DEVICENAME='" + deviceName + "'  GROUP BY DEVICENAME";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
        for (Map<String, ?> temp : resultList) {
            String scpno = temp.get("SSPIPINDEX").toString().trim();
            String[] array = scpno.split(",");
            for (int i = 0; i < array.length; i++) {
                this.scpNoList.add(array[i]);
            }
            Collections.sort(this.scpNoList, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return Integer.valueOf(o1) - Integer.valueOf(o2);
                }
            });
        }
    }
}