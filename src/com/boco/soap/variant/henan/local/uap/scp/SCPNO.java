package com.boco.soap.variant.henan.local.uap.scp;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class SCPNO extends VariantValueInvoke {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    public static Map<String, List<String>> scpNoMap = null;

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {

        if (scpNoMap == null) {
            this.initMap(arg3);
        }
        String result = "";
        if (scpNoMap.containsKey(arg0.getName())) {
            List<String> listscpno = scpNoMap.get(arg0.getName());
            int currentValue = Integer.valueOf(listscpno.get(0));
            while (listscpno.contains(currentValue + "")) {
                currentValue++;
            }
            result = currentValue + "";
            int preIndex = listscpno.indexOf(String.valueOf(currentValue - 1));
            if (preIndex > -1) {
                listscpno.set(preIndex, result);
                listscpno = listscpno.subList(preIndex, listscpno.size());
                scpNoMap.put(arg0.getName(), listscpno);
            } else {
                this.logger.info("currentValue:" + currentValue);
                this.logger.info("listscpno:" + listscpno);
            }
        }
        return new String[] { result };
    }

    private void initMap(String dbFile) {
        scpNoMap = new HashMap<String, List<String>>();
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "SELECT DEVICENAME,GROUP_CONCAT(SCPNO) SCPNO  FROM HW_UAP_SCPADR WHERE cast(SCPNO as int) BETWEEN 101 AND 65535 GROUP BY DEVICENAME";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
        for (Map<String, ?> temp : resultList) {
            String deviceName = temp.get("DEVICENAME").toString();
            String scpno = temp.get("SCPNO").toString().trim();
            List<String> listscpno = Arrays.asList(scpno.split(","));
            Collections.sort(listscpno, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return Integer.valueOf(o1) - Integer.valueOf(o2);
                }
            });
            scpNoMap.put(deviceName, listscpno);
        }
    }
}