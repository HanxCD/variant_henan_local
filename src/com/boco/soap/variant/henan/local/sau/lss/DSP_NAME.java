package com.boco.soap.variant.henan.local.sau.lss;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class DSP_NAME extends VariantValueInvoke {
    private Map<String, String> map = null;

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {
        Object o = data.get("DPC_NAME");
        String result = null;
        if (o != null) {
            if ((this.map == null) || (this.map.size() == 0)) {
                this.initMap(arg3);
            }
            for (Map.Entry<String, String> entry : this.map.entrySet()) {
                if (entry.getKey().split(",")[0].equals(arg0.getName()) && (entry.getValue().indexOf(o.toString()) > -1)) {
                    result = entry.getValue();
                    break;
                }
            }
        }

        return new String[] { result };
    }

    private void initMap(String dbFile) {
        this.map = new HashMap<String, String>();
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "select DEVICENAME||','||DSP_NAME DEVICENAME,DSP_NAME from HW_SAU_SCCPDSP";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
        for (Map<String, ?> temp : resultList) {
            String deviceName = temp.get("DEVICENAME").toString();
            String dspName = temp.get("DSP_NAME").toString();
            this.map.put(deviceName, dspName);
        }
    }
}
