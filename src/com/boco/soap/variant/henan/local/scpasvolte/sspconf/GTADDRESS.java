package com.boco.soap.variant.henan.local.scpasvolte.sspconf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * 2017-03-15
 *
 * @author caozengran
 * @Email caozengran@boco.com.cn
 *该变量查询智能网SCPAS中VOLTE设备的SSP配置表中的数据，以端局关口局设备号码为依据，只查那些
 *独立放音省份的MSCID号码
 */

public class GTADDRESS extends VariantValueInvoke {
    private Map<String, String> map = null;

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {
        Object mscId = data.get("MSCID");
        String provinceStr = String.valueOf(data.get("PROVINCE"));
        String result = "";
        if (mscId == null) {
            result = "NULL";
        } else {
            if (this.map == null) {
                this.map = new HashMap<String, String>();
                this.initMap(dbFile);
            }
            if (!this.map.get("PROVINCE").contains(provinceStr)) {
                return null;
            }
            result = mscId.toString();
        }

        return new String[] { result };

    }

    private void initMap(String dbFile) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "select GROUP_CONCAT(PROVINCE) PROVINCE from TCM_IP_PROVINCE";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
        for (Map<String, ?> temp : resultList) {
            this.map.put("PROVINCE", temp.get("PROVINCE").toString().trim());
        }
    }

}