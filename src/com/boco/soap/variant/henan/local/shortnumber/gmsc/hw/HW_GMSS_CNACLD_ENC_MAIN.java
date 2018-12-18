package com.boco.soap.variant.henan.local.shortnumber.gmsc.hw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 *   Ϊ ˾ֺ    ֹ
 *
 * @author  wanghao
 * @Email   wanghao2009.01@google.cn
 *
 */
public class HW_GMSS_CNACLD_ENC_MAIN extends VariantValueInvoke {
    private Map<String, String> map = null;
    private Map<String, String> citymap = null;

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {
        String sDest = "";
        Object o = data.get("START_GT");
        if (o == null) {
            return null;
        } else {
            if (this.map == null) {
                this.map = new HashMap<String, String>();
                this.initMap(dbFile);
            }
            if (this.citymap == null) {
                this.citymap = new HashMap<String, String>();
                this.initCityMap(dbFile);
            }

            String cityStr = this.citymap.get(arg0.getName());
            for (String temp : cityStr.split(",")) {
                if (this.map.containsKey(temp)) {
                    sDest += "," + this.map.get(temp) + o.toString();
                }
            }
            if (sDest == "") {
                return null;
            }
            return sDest.substring(1).split(",");
        }
    }

    private void initCityMap(String dbFile) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "   SELECT  DEVICENAME,GROUP_NAME,(SELECT COVER_CITY FROM TCM_HW_GMSS_MTB_REL B WHERE A.Group_Name=B.Group_Name AND A.Devicename!=B.Devicename )COVER_CITY from TCM_HW_GMSS_MTB_REL A ";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

        for (Map<String, ?> temp : resultList) {
            this.citymap.put(temp.get("DEVICENAME").toString(), temp.get("COVER_CITY").toString());

        }
    }

    private void initMap(String dbFile) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "select distinct(BUSI_CITY) BUSI_CITY,AREA from HW_GMSC_CITY_RELATION";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

        for (Map<String, ?> temp : resultList) {
            this.map.put(temp.get("BUSI_CITY").toString(), temp.get("AREA").toString());

        }
    }
}
