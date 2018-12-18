package com.boco.soap.variant.henan.local.ims.agcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class Trunk_GROUP extends VariantValueInvoke {
    private final Map<String, String> map = new HashMap<String, String>();

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {
        //制作对应AGCF的数据
        if (!arg0.getName().equals(data.get("PRINCIPAL_ATTRIBUTION").toString())) {
            return null;
        }
        if ((this.map == null) || (this.map.size() == 0)) {
            this.initMap(arg3, arg0.getName());
        }
        //过滤中继群过滤表中的数据
        if (this.map.containsKey(data.get("TRUNK_GROUP").toString())) {
            return null;
        }
        return new String[] { data.get("TRUNK_GROUP").toString() };
    }

    private void initMap(String dbFile, String deviceName) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "select TRUNK_GROUP from TCM_IMS_AGCF_TRUNGROP_FITER where PRINCIPAL_ATTRIBUTION='" + deviceName + "'";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
        for (Map<String, ?> temp : resultList) {
            String trunk_group = temp.get("TRUNK_GROUP").toString();
            this.map.put(trunk_group, trunk_group);

        }

    }
}
