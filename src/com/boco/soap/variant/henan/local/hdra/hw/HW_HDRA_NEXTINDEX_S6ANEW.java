package com.boco.soap.variant.henan.local.hdra.hw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class HW_HDRA_NEXTINDEX_S6ANEW extends VariantValueInvoke {
    private Map<String, String> map = null;

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {
        Object hlr_name = data.get("HLR_NAME");
        String result = "NULL";
        if (hlr_name == null) {
            result = "NULL";
        } else {
            if (this.map == null) {
                this.map = new HashMap();
                this.initMap(dbFile);
            }
            if (this.map.containsKey(hlr_name)) {
                result = this.map.get(hlr_name);
            }
        }
        return new String[] { result };
    }

    private void initMap(String dbFile) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "select HLR_NAME,HDRA_INDEX from HW_DRA2HSS_INDEX";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
        for (Map<String, ?> temp : resultList) {
            this.map.put(temp.get("HLR_NAME").toString(), temp.get("HDRA_INDEX").toString());
        }
    }

}