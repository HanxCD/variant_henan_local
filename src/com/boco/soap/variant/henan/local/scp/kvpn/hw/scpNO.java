package com.boco.soap.variant.henan.local.scp.kvpn.hw;

import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class scpNO extends VariantValueInvoke {
    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {
        Object startGt = data.get("START_GT");
        Object areaCode = data.get("AREA_CODE");
        Object areaCity = data.get("BUSI_CITY");

        String scpNo = null;
        if (startGt == null) {
            scpNo = "[NULL]";
        } else {
            scpNo = this.getScpNo(startGt.toString().trim(), areaCode.toString().trim(), areaCity, dbFile);
        }

        return new String[] { scpNo };
    }

    private String getScpNo(String startGt, String areaCode, Object areaCity, String dbFile) {
        DataQueryUtils utils = DataQueryUtils.getInstance();

        String sql = "select GT,SCPNO from TCM_VPMN_SCP where AREA_CODE='" + areaCode + "'";

        if ((areaCity != null) && (areaCity.toString().equals("开封"))) {
            sql = sql + " and BUSI_CITY='开封'";
        }
        if ((areaCity != null) && (areaCity.toString().equals("郑州"))) {
            sql = sql + " and BUSI_CITY !='开封'";
        }
        sql = sql + ";";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
        String[] result = new String[resultList.size()];
        String scpNo = null;
        if (resultList.size() != 0) {
            for (int i = 0; i < resultList.size(); i++) {
                result[i] = resultList.get(i).get("GT").toString();
                if (startGt.startsWith(result[i])) {
                    scpNo = resultList.get(i).get("SCPNO").toString();
                    break;
                }
            }
            return scpNo;
        }
        return "[NULL]";
    }
}