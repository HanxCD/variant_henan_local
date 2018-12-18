package com.boco.soap.variant.henan.local.shortnumber.gmsc.hw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class HW_GMSS_CNACLD_ENC extends VariantValueInvoke {
    private Map<String, String> map = null;
    private Map<String, String> citymap = null;

    private final Logger logger = LoggerFactory.getLogger(HW_GMSS_CNACLD_ENC.class);

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {
        String sDest = "";
        Object o = data.get("START_GT");
        if (o == null) {
            return null;
        }
        if (this.map == null) {
            this.map = new HashMap();
            this.initMap(dbFile);
        }
        if (this.citymap == null) {
            this.citymap = new HashMap();
            this.initCityMap(dbFile);
        }

        String cityStr = this.citymap.get(arg0.getName());
        for (String temp : cityStr.split(",")) {
            if (this.map.containsKey(temp)) {
                sDest = sDest + "," + this.map.get(temp) + o.toString();
            }
        }
        if (sDest == "") {
            return null;
        }

        return sDest.substring(1).split(",");
    }

    private void initCityMap(String dbFile) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "   SELECT  DEVICENAME,GROUP_NAME,COVER_CITY from TCM_HW_GMSS_MTB_REL ";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

        for (Map temp : resultList) {
            this.citymap.put(temp.get("DEVICENAME").toString(), temp.get("COVER_CITY").toString());
        }
    }

    private void initMap(String dbFile) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "select distinct(BUSI_CITY),AREA from HW_GMSC_CITY_RELATION";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

        for (Map temp : resultList) {
            this.map.put(temp.get("BUSI_CITY").toString(), temp.get("AREA").toString());
        }
    }
}