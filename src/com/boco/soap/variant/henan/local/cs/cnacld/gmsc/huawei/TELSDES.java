package com.boco.soap.variant.henan.local.cs.cnacld.gmsc.huawei;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class TELSDES extends VariantValueInvoke {
    private Map<String, String> map = null;

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {
        Object o = data.get("BUSI_CITY");
        if (this.map == null) {
            this.map = new HashMap<String, String>();
            this.initMap(dbFile);
        }
        String gtrcStr = this.map.get(o.toString());
        return new String[] { gtrcStr };
    }

    private void initMap(String dbFile) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "select distinct(BUSI_CITY),TELSDES from TCM_HW_SSA_SPCCODE";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
        for (Map<String, ?> temp : resultList) {
            this.map.put(temp.get("BUSI_CITY").toString(), temp.get("TELSDES").toString());
        }
    }
}