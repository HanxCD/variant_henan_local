package com.boco.soap.variant.henan.local.gt.mscserver.nokia;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * @ClassName:  MISDN_CHECK   
 * @Description:TODO(返回值取mss_ID 同一个LAC10下的任意一个数据。与现网数据匹配)   
 * @author: 
 * @date:   2017-4-6 下午05:36:31   
 * @version: V1.0  
 * @Copyright: 2017 www.boco.com.cn Inc. All rights reserved.
 */
public class MISDN_CHECK extends VariantValueInvoke {
    private Map<String, String> map=null;

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1,
            Map<String, ?> data, String dbFile) {
        String lac=data.get("LAC10").toString();
        if (this.map == null) {
            this.map = new HashMap<String, String>();
            this.initMap(dbFile,arg0.getName());
        }
        String mssIds=this.map.get(lac).replaceAll(",|，","|");
        return new String[]{mssIds};
    }


    private void initMap(String dbFile,String deviceName) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "SELECT GROUP_CONCAT(MSS_VLR) MSSID, LAC10, COUNT(1) LACNUM  FROM TCM_TAC_LAC_LIST GROUP BY LAC10";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
        for (Map<String, ?> temp : resultList) {
            this.map.put( temp.get("LAC10").toString().trim(),temp.get("MSSID").toString());
        }
    }

}
