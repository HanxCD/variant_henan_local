package com.boco.soap.variant.henan.local.ldra.hw.DMLNK;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class MID extends VariantValueInvoke {

    /**
     * @author caozengran
     * @time 2016年12月20日 下午5:19:06
     */

    private final Map<String, String> map = new HashMap<String, String>();

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {

        Object o = data.get("PIP2");
        String result = null;
        if (null == o) {
            result = "NULL";
        } else {
            String ip = o.toString();
            if (this.map.size() == 0) {
                this.InitMap(arg3);
            }

            for (Entry<String, String> entry : this.map.entrySet()) {
                if (entry.getKey().indexOf(o.toString()) > -1) {
                    String[] spcArr = entry.getValue().split("\r\n|,|\\s+");
                    System.out.println(Arrays.toString(spcArr));
                    System.out.println(spcArr);
                    result = spcArr[new Random().nextInt(spcArr.length)];
                    break;
                }
            }
        }
        return new String[] { result };
    }

    private void InitMap(String dbFile) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "SELECT GROUP_CONCAT(IP) IP, GROUP_CONCAT(BSG) BSG FROM TCM_DRA_IPLDRA1 GROUP BY FRAME";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
        for (Map<String, ?> temp : resultList) {
            String ip = temp.get("IP").toString();
            String bsg = temp.get("BSG").toString();
            this.map.put(ip, bsg);
        }

    }
}
