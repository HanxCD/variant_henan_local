package com.boco.soap.variant.henan.local.gt.hstp.hw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class ES extends VariantValueInvoke {
    private Map<String, String> map = null;

    /*
     * Editor:Jianwen Sha Edited time:2015-8-11 10:20:21 查询H局局名。
     */
    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {

        Object o = data.get("PROVINCE");
        if (this.map == null) {
            this.map = new HashMap<String, String>();
            this.initMap(dbFile);
        }
        if (o == null) {
            return new String[] { "[NULL]" };
        }
        String province = o.toString().trim();

        String dataNo = this.map.get(province);
        if (dataNo == null) {
            return new String[] { "[NULL]" };
        } else {
            dataNo = dataNo.replace(",", "&");
            dataNo = dataNo.toUpperCase().replace("HSTP1", "H1");
            dataNo = dataNo.toUpperCase().replace("HSTP2", "H2");

            return new String[] { dataNo };
        }

    }

    private void initMap(String dbFile) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "select PROVINCE,GROUP_CONCAT(BUREAU_DATA_NO) BUREAU_DATA_NO  from TCM_ALL_LSTPHSTP  WHERE  BUREAU_DATA_NO like '%H1%' OR BUREAU_DATA_NO like '%HSTP1%' OR BUREAU_DATA_NO like '%H2%' OR BUREAU_DATA_NO like '%HSTP2%' group by PROVINCE order by BUREAU_DATA_NO DESC";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
        for (Map<String, ?> temp : resultList) {
            this.map.put(temp.get("PROVINCE").toString(), temp.get("BUREAU_DATA_NO").toString());
        }
    }

}
