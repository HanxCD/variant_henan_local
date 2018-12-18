package com.boco.soap.variant.henan.local.ims.tas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class HW_TAS_DEST1 extends VariantValueInvoke {
    private Map<String, String> map = null;

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {
        Object city = data.get("BUSI_CITY");
        String dest = "NULL";
        if (this.map == null) {
            this.map = new HashMap();
            this.initMap(dbFile);
        }
        if (this.map.containsKey(city.toString().trim())) {
            dest = this.map.get(city.toString().trim());
        } else {
            dest = this.map.get("CallSrc");
        }
        return new String[] { dest };
    }

    private void initMap(String dbFile) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "select LOCALPFXNAME,LOCALPFX from HW_TAS_LDNSET";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

        for (Map temp : resultList) {
            this.map.put(temp.get("LOCALPFXNAME").toString(), temp.get("LOCALPFX").toString());
        }
    }
}

/* Location:           C:\Users\Administrator.USER-20161220XY\Desktop\variant_volte_henan\
 * Qualified Name:     com.boco.soap.variant.henan.local.ims.tas.HW_TAS_DEST1
 * JD-Core Version:    0.6.2
 */