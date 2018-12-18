package com.boco.soap.variant.henan.local.sau.lss;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class BOARDINFO extends VariantValueInvoke {
    private Map<String, String> map = null;

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {
        Object objNeType = data.get("PANELTYPE");
        String result = "[NULL]";

        if (objNeType.toString().trim().toUpperCase().equals("SAU")) {
            if ((this.map == null) || (this.map.size() == 0)) {
                this.initMap(arg3);
            }
            if (this.map.containsKey(arg0.getName())) {
                String[] strArr = this.map.get(arg0.getName()).split(",");
                return strArr;
            }
            return new String[] { result };
        }

        return null;
    }

    private void initMap(String dbFile) {
        this.map = new HashMap<String, String>();
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "SELECT DEVICENAME,GROUP_CONCAT(BOARDINFO) BOARDINFO FROM v_hw_sau_lss GROUP BY DEVICENAME";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
        for (Map<String, ?> temp : resultList) {
            String deviceName = temp.get("DEVICENAME").toString();
            String identifiers = temp.get("BOARDINFO").toString();

            this.map.put(deviceName, identifiers);
        }
    }
}
