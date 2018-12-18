package com.boco.soap.variant.henan.local.shortnumber.gmsc.hw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class HW_GMSS_CLDPREANA_CLDNCN_INDEX extends VariantValueInvoke {
    private Map<String, String> map = null;
    private Map<String, String> citymap = null;

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {
        String sDest = "";
        if (this.map == null) {
            this.map = new HashMap<String, String>();
            this.initMap(dbFile, arg0.getName());
        }
        if (this.citymap == null) {
            this.citymap = new HashMap<String, String>();
            this.initCityMap(dbFile, arg0.getName());
        }
        String cityStr = this.citymap.get(arg0.getName());
        for (String str : cityStr.split(",")) {
            String gtrcStr = this.map.get(str.trim());
            sDest += "," + "1" + gtrcStr + "0";
        }
        return sDest.substring(1).split(",");
    }

    private void initCityMap(String dbFile, String sNename) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "select '" + sNename + "' DEVICENAME,COVER_CITY from TCM_HW_GMSS_MTB_REL where GROUP_NAME in (select GROUP_NAME from TCM_HW_GMSS_MTB_REL where DEVICENAME='" + sNename + "')";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
        String Sbcity = "";
        for (Map temp : resultList) {
            Sbcity = Sbcity + "," + temp.get("COVER_CITY").toString();
        }
        this.citymap.put(sNename, Sbcity.substring(1));
    }

    private void initMap(String dbFile, String sNename1) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "select '" + sNename1 + "' SERVER,BUSI_CITY,MGWINDEX from HW_GMSC_CITY_RELATION where DEVICENAME  IN (SELECT DEVICENAME  FROM TCM_HW_GMSS_MTB_REL A WHERE A.GROUP_NAME IN (SELECT GROUP_NAME FROM TCM_HW_GMSS_MTB_REL B WHERE B.DEVICENAME='" + sNename1 + "' ))";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
        for (Map temp : resultList) {
            this.map.put(temp.get("BUSI_CITY").toString(), temp.get("MGWINDEX").toString());
        }
    }
}