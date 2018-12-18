package com.boco.soap.variant.henan.local.ims.agcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class EXPIRED_PERMISSION extends VariantValueInvoke {

    private final Map<String, String> map = new HashMap<String, String>();

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {
        String result = "NULL";
        if ((this.map == null) || (this.map.size() == 0)) {
            this.initMap(arg3, arg0.getName());
        }
        if (String.valueOf(this.map.get(data.get("TRUNK_GROUP").toString())).contains("PRA")) {
            result = "NULL";
        } else {
            //直接返回现网的呼出权限
            if (this.map.containsKey(data.get("TRUNK_GROUP").toString())) {
                result = this.map.get(data.get("TRUNK_GROUP").toString()).split(",")[1];
            }
        }
        return new String[] { result };
    }

    private void initMap(String dbFile, String deviceName) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "select TRUNK_GROUP,CIRCUIT_TYPE,TO_GROUP,EXPIRED_PERMISSION from HW_IMS_AGCF_TG where DEVICENAME='" + deviceName + "' ";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
        for (Map<String, ?> temp : resultList) {
            String trunk_group = temp.get("TRUNK_GROUP").toString();
            String circuit_type = temp.get("CIRCUIT_TYPE").toString();
            String expired_permission = temp.get("EXPIRED_PERMISSION").toString();
            this.map.put(trunk_group, circuit_type + "," + expired_permission);

        }

    }
}
