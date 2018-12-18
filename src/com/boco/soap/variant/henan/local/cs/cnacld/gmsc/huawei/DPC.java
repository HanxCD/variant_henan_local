package com.boco.soap.variant.henan.local.cs.cnacld.gmsc.huawei;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**

 *
 * @author wanghao
 * @Email
 *
 */

public class DPC extends VariantValueInvoke {
    private Map<String, String> map = null;;

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
        String sql = "SELECT BUSI_CITY,WM_GR(SERVERSPCCODE) SERVERSPCCODE FROM TCM_HW_SSA_SPCCODE  GROUP BY BUSI_CITY";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
        for (Map<String, ?> temp : resultList) {
            String data1 = temp.get("BUSI_CITY").toString();
            String data3 = temp.get("SERVERSPCCODE").toString();
            this.map.put(data1, data3);
        }
    }

}