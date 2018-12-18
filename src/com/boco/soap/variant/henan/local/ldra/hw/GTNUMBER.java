package com.boco.soap.variant.henan.local.ldra.hw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class GTNUMBER extends VariantValueInvoke {
    private Map<String, String> map = null;

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {

        Object o = data.get("GT_NUMBER");
        String result = null;
        if (null == o) {
            result = "NULL";
        } else {
            result = o.toString();
            if (this.map == null) {
                this.map = new HashMap<String, String>();
                this.initMap(arg3);
            }
            if (!this.map.get("E164").contains(result)) {
                result = o.toString().trim();
            } else {
                return null;
            }
        }
        return new String[] { result };

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
