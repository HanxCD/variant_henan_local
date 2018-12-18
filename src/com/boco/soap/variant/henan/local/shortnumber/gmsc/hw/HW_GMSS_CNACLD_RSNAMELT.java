package com.boco.soap.variant.henan.local.shortnumber.gmsc.hw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class HW_GMSS_CNACLD_RSNAMELT extends VariantValueInvoke {
    private final Map<String, String> map = new HashMap();

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {
        this.initMap(dbFile);
        Object o = data.get("ROUTE_NAME");
        Object sCity = data.get("BUSI_CITY");

        String result = null;
        if (o == null) {
            result = "[NULL]";
        } else {
            if (o.toString().equals("电信")) {
                result = this.map.get(sCity) + "DX";
            }
            if ((o.toString().equals("网通")) || (o.toString().equals("联通"))) {
                result = this.map.get(sCity) + "LT";
            }
            if (o.toString().equals("铁通")) {
                result = this.map.get(sCity) + "TT";
            }
        }
        return new String[] { result };
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
 * Qualified Name:     com.boco.soap.variant.henan.local.shortnumber.gmsc.hw.HW_GMSS_CNACLD_RSNAMELT
 * JD-Core Version:    0.6.2
 */