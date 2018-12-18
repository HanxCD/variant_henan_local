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
package com.boco.soap.variant.henan.local.gt.lstp.hw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class RPT_1 extends VariantValueInvoke {

    private final Map<String, String> map = new HashMap<String, String>();
    private final Map<String, String> map2 = new HashMap<String, String>();

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
        String o = data.get("HLR_NAME").toString();
        String result = "";
        if (null == o) {
            result = "[NULL]";
        } else {
            String hlrName = o.toString();
            Pattern p = Pattern.compile("^\\s*(\\w+)[\\s+|,|，|、]*");
            Matcher m = p.matcher(hlrName);//ZZHSS103FE01BHW
            if (m.find()) {//ZZHSS103FE01BHW-ANY2
                result = this.getSpc(m.group(1), dbFile, arg0.getName());//网元名称
            }
        }
        return new String[] { result };
    }

    /**
     * @param name
     * @param spcStr
     * @param dbFile
     * @return
     */
    private String getSpc(String hlrName, String dbFile, String neName) {

        if ((this.map == null) || (this.map.size() == 0)) {
            this.initMap(dbFile, neName);
            this.initMap2(dbFile, neName);
        }
        String result = "[NULL]";
        for (String keyStr : this.map2.keySet()) {
            if (keyStr.indexOf(hlrName) >= 0) {
                result = this.map2.get(keyStr);
                return result;
            }
        }
        for (String keyStr : this.map.keySet()) {
            if (keyStr.indexOf(hlrName) >= 0) {
                result = this.map.get(keyStr);
                return result;
            }
        }
        return result;
    }

    private void initMap(String dbFile, String deviceName) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "SELECT DEST0_NAME||','||DEST1_NAME||CASE DEST2_NAME  WHEN '<NULL>' THEN '' ELSE ','||DEST2_NAME END DEST_NAME,SCCP_STRATEGY_NAME FROM HW_STP_SCCPADDRPL  WHERE DEVICENAME='" + deviceName + "' order by SCCP_STRATEGY_NAME";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
        for (Map<String, ?> temp : resultList) {
            String destName = temp.get("DEST_NAME").toString();//ZZHSS103FE01BHW,ZZHSS103FE10BHW
            String strategyName = temp.get("SCCP_STRATEGY_NAME").toString();//ZZHSS103FE01BHW-ANY2

            if ((destName.indexOf("BHW") > 0) || (destName.indexOf("BNK") > 0)) {

                this.map.put(destName, strategyName);
            }
        }

    }

    private void initMap2(String dbFile, String deviceName) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "SELECT DEST0_NAME||','||DEST1_NAME||CASE DEST2_NAME  WHEN '<NULL>' THEN '' ELSE ','||DEST2_NAME END DEST_NAME,SCCP_STRATEGY_NAME FROM HW_STP_SCCPADDRPL  WHERE SCCP_STRATEGY_NAME like '%-%' AND  DEVICENAME='" + deviceName + "' order by SCCP_STRATEGY_NAME ";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
        for (Map<String, ?> temp : resultList) {
            String destName = temp.get("DEST_NAME").toString();//ZZHSS103FE01BHW,ZZHSS103FE10BHW
            String strategyName = temp.get("SCCP_STRATEGY_NAME").toString();//ZZHSS103FE01BHW-ANY2

            if ((destName.indexOf("BHW") > 0) || (destName.indexOf("BNK") > 0)) {

                this.map2.put(destName, strategyName);
            }
        }

    }
}
