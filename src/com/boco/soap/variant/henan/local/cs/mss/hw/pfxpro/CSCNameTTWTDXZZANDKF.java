package com.boco.soap.variant.henan.local.cs.mss.hw.pfxpro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * ��Ϊ�˾ֺ����ֹ�
 *
 * @author
 * @Email
 *
 */
public class CSCNameTTWTDXZZANDKF extends VariantValueInvoke {
    private Map<String, String> map = null;

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {
        Object o = data.get("BUSI_CITY");
        Map<String, String> citys = arg0.getCoverCities();
        Set<String> set = citys.keySet();
        if (set.toString().contains("郑州") && o.toString().equals("开封")) {
            o = "郑州";
        }
        if (set.toString().contains("开封") && o.toString().equals("郑州")) {
            o = "开封";
        }

        if (this.map == null) {
            this.map = new HashMap<String, String>();
            this.initMap(dbFile);
        }
        String gtrcStr = this.map.get(o.toString()) + "DX," + this.map.get(o.toString()) + "TT," + this.map.get(o.toString()) + "WT";
        if (o.toString().equals("鹤壁")) {
            gtrcStr = this.map.get(o.toString()) + "DX," + this.map.get(o.toString()) + "TT," + this.map.get(o.toString()) + "WT," + "HEBPBX,矿务局-HEB";
        }
        if (o.toString().equals("三门峡")) {
            gtrcStr = this.map.get(o.toString()) + "DX," + this.map.get(o.toString()) + "TT," + this.map.get(o.toString()) + "WT," + "SMXPBX";
        }

        return gtrcStr.split(",");
    }

    private void initMap(String dbFile) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "select BUSI_CITY,SIMPLENAME from TCM_LOCAL_CITY_LIST";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

        for (Map<String, ?> temp : resultList) {
            this.map.put(temp.get("BUSI_CITY").toString(), temp.get("SIMPLENAME").toString());

        }
    }
}