package com.boco.soap.variant.henan.local.sau.sccpgt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class START_GT0 extends VariantValueInvoke {
    private Map<String, String> map = null;

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> arg2, String arg3) {
        String result = "[NULL]";
        if (this.map == null) {
            this.map = new HashMap<String, String>();
            this.initMap(arg3);
        }
        for (Map.Entry<String, String> entry : this.map.entrySet()) {
            if (entry.getKey().indexOf(arg0.getName()) > -1) {
                result = entry.getValue();
                return result.split(",");
            }
        }
        return new String[] { result };
    }

    private void initMap(String dbFile) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = " SELECT OFFICE_NAME,MSCID FROM TCM_SCP_VC_MSCID ";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

        for (Map<String, ?> temp : resultList) {
            this.map.put(temp.get("OFFICE_NAME").toString(), temp.get("MSCID").toString());
        }
    }
}
