package com.boco.soap.variant.henan.local.cs.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * @ClassName:  CSCNameBy171NE
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: caozengran
 * @date:   2017年3月30日 下午5:35:52
 * @version: V1.0
 * @Copyright: 2017 www.boco.com.cn Inc. All rights reserved.
 */
public class CSCNameBy171NE extends VariantValueInvoke {
    private Map<String, String> map = null;
    private final Map<String, String> citymap = null;

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {
        if (this.map == null) {
            this.map = new HashMap<String, String>();
            this.initMap(dbFile, arg0.getName());
        }

        String sCscname = this.citymap.get(arg0.getName());

        return sCscname.split(",");
    }

    private void initMap(String dbFile, String sName) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "select DEVICENAME,CSCNAME from HW_GMSS_CLDPREANA where DEVICENAME='" + sName + "' and PFX='171'";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
        String sCscname = "";
        for (Map<String, ?> temp : resultList) {
            sCscname += "," + temp.get("CSCNAME").toString();
        }
        if (sCscname != "") {
            this.map.put(sName, sCscname.substring(1));
        }
    }
}