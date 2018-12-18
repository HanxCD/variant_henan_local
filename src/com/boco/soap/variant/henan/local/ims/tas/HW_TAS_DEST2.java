package com.boco.soap.variant.henan.local.ims.tas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class HW_TAS_DEST2 extends VariantValueInvoke {
    private Map<String, String> map = null;

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {
        String dest = "";
        if (this.map == null) {
            this.map = new HashMap();
            this.initMap(dbFile, arg0.getName());
        }
        Object coverCity = arg0.getCorverAddr();
        if (coverCity != null) {
            for (String addStr : coverCity.toString().split(",")) {
                if (this.map.containsKey(addStr.toString().trim())) {
                    dest = dest + "," + this.map.get(addStr.toString().trim());
                }
            }
            if (dest.length() > 1) {
                return dest.substring(1).split(",");
            }
        }
        return new String[] { dest };
    }

    private void initMap(String dbFile, String deviceName) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "select LOCALPFXNAME,LOCALPFX from HW_TAS_LDNSET WHERE DEVICENAME='" + deviceName + "'";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

        for (Map temp : resultList) {
            this.map.put(temp.get("LOCALPFXNAME").toString(), temp.get("LOCALPFX").toString());
        }
    }
}
