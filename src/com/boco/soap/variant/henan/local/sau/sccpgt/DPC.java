package com.boco.soap.variant.henan.local.sau.sccpgt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;
import com.boco.soap.variant.common.SPC16;

public class DPC extends VariantValueInvoke {
    private Map<String, String> map = null;

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> arg2, String arg3) {
        Object o = arg2.get("BUSI_CITY");

        String result = null;
        if (o != null) {
            String busiStr = o.toString();
            if (busiStr.indexOf("济源") > -1) {
                busiStr = "焦作";
            }
            if (this.map == null) {
                this.map = new HashMap<String, String>();
                this.initMap(arg3);
            }
            if (this.map.get(busiStr) != null) {
                result = this.map.get(busiStr).toString();
            }
        }
        return new String[] { result };
    }

    private void initMap(String dbFile) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "select COVER_CITY,STP1_SPC,STP2_SPC from TCM_LOCAL_LSTP ";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

        for (Map<String, ?> temp : resultList) {
            this.map.put(temp.get("COVER_CITY").toString(),
                    SPC16.spc10to16(temp.get("STP1_SPC").toString()) + "|" + SPC16.spc10to16(temp.get("STP2_SPC").toString()));
        }
    }
}
