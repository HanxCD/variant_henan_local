package com.boco.soap.variant.henan.local.gt.mscserver.nokia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class GT164_HLR extends VariantValueInvoke {
    private Map<String, String> map = null;

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {
        String ele_type = data.get("ELE_TYPE") == null ? "" : data.get("ELE_TYPE").toString();

        if (ele_type.equals("用户号段")) {
            String busi_type = data.get("BUSI_CITY") == null ? "" : data.get("BUSI_CITY").toString();
            if (arg0.getCoverCities().values().toString().indexOf(busi_type) < 0) {
                return null;
            }
            Object o = data.get("START_GT");
            String result = null;
            if (o == null) {
                result = "NULL";
            } else {
                result = o.toString();
                if (this.map == null) {
                    this.map = new HashMap();
                    this.initMap(arg3);
                }
                if (this.map.containsKey(result)) {
                    result = o.toString().trim();
                } else {
                    return null;
                }
            }
            return new String[] { result };
        }
        return null;
    }

    private void initMap(String dbFile) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "SELECT GROUP_CONCAT(E164) E164 FROM TCM_LOCAL_HLR_23G_TEL WHERE DESCR='自助换卡号段'";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
        for (Map<String, ?> temp : resultList) {
            this.map.put("E164", temp.get("E164").toString());
        }
    }
}