package com.boco.soap.variant.henan.local.shortnumber.gmsc.hw;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HW_MSS_CNACLD_RSNAME1254708 extends VariantValueInvoke {
    private Map<String, String> map=null;
    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1,
                              Map<String, ?> arg2, String arg3) {

        Object sCity = arg2.get("BUSI_CITY");
        String sRoute="";
        if (map == null) {
            map = new HashMap<String, String>();
            this.initMap(arg3);
        }
        if(null == sCity){
            return new String[] { "[NULL]" };
        } else {
            return new String[] { map.get(sCity) + "VOLTE" };
        }
    }

    private void initMap(String dbFile) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "select BUSI_CITY,SIMPLENAME from TCM_LOCAL_CITY_LIST";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

        for (Map<String, ?> temp : resultList) {
            map.put(temp.get("BUSI_CITY").toString(), temp.get("SIMPLENAME")
                    .toString());

        }
    }
}
