package com.boco.soap.variant.henan.local.ims.agcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class CIRCUIT_TYPE extends VariantValueInvoke {

    private final Map<String, String> map = new HashMap<String, String>();

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {
        if ((this.map == null) || (this.map.size() == 0)) {
            this.initMap(arg3, arg0.getName());
        }
        return new String[] { this.map.get(data.get("TRUNK_GROUP").toString()) };
    }

    private void initMap(String dbFile, String deviceName) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "select TRUNK_GROUP,CIRCUIT_TYPE,TO_GROUP,EXPIRED_PERMISSION from HW_IMS_AGCF_TG where DEVICENAME='" + deviceName + "' ";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
        for (Map<String, ?> temp : resultList) {
            String trunk_group = temp.get("TRUNK_GROUP").toString();
            String circuit_type = temp.get("CIRCUIT_TYPE").toString();
            this.map.put(trunk_group, circuit_type);

        }

    }
}
