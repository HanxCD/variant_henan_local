package com.boco.soap.variant.henan.local.shortnumber.gmsc.hw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class HW_GMSS_CLDPREANA_CSCNAMEDXC extends VariantValueInvoke {
    private Map<String, String> map = null;
    private Map<String, String> citymap = null;

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {
        String sDest = "";
        if (this.map == null) {
            this.map = new HashMap();
            this.initMap(dbFile);
        }
        if (this.citymap == null) {
            this.citymap = new HashMap();
            this.initCityMap(dbFile, arg0.getName());
        }

        String cityStr = this.citymap.get(arg0.getName());
        for (String str : cityStr.split(",")) {
            String gtrcStr = this.map.get(str);
            sDest = sDest + "," + gtrcStr + "DXC";
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

    private void initMap(String dbFile) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "select BUSI_CITY,SIMPLENAME from TCM_LOCAL_CITY_LIST";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

        for (Map temp : resultList) {
            this.map.put(temp.get("BUSI_CITY").toString(), temp.get("SIMPLENAME").toString());

        }
    }

}

/* Location:           C:\Users\Administrator\Desktop\wh\
 * Qualified Name:     com.boco.soap.variant.henan.local.shortnumber.gmsc.hw.HW_GMSS_CLDPREANA_CSCNAMEDXC
 * JD-Core Version:    0.6.2
 */