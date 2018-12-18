package com.boco.soap.variant.henan.local.uap.scp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class IPADR extends VariantValueInvoke {
    private Map<String, String> map = null;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {
        String result = "[NULL]";
        Object o = data.get("MSCID");
        if (o != null) {

            if ((this.map == null) || (this.map.size() == 0)) {
                this.initMap(arg3);
            }
            if (this.map.containsKey(arg0.getName() + "," + o.toString())) {
                result = this.map.get(arg0.getName() + "," + o.toString());
                return result.split(",");
            } else {
                result = String.format("%s无法在SCPMAP实际数据中取得SCP内部编号", o.toString());
            }
        }

        return new String[] { result };
    }

    private void initMap(String dbFile) {
        this.map = new HashMap<String, String>();
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "SELECT  DEVICENAME||','||SCPEXGT DEVICENAME,SCPINGT  FROM HW_UAP_SCPMAP";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
        for (Map<String, ?> temp : resultList) {
            String deviceName = temp.get("DEVICENAME").toString();
            String identifiers = temp.get("SCPINGT").toString();

            this.map.put(deviceName, identifiers);
        }
    }
}