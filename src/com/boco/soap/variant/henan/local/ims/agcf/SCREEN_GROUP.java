package com.boco.soap.variant.henan.local.ims.agcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * @ClassName:  SCREEN_GROUP
 * @Description:甄别组号
 * @author: caozengran
 * @date:   2017年10月31日 上午11:36:55
 * @version: V1.0
 * @Copyright: 2017 www.boco.com.cn Inc. All rights reserved.
 */
public class SCREEN_GROUP extends VariantValueInvoke {

    private final Map<String, String> map = new HashMap<String, String>();
    private static SCREEN_GROUP group = null;

    public static SCREEN_GROUP getInstance() {
        if (group == null) {
            group = new SCREEN_GROUP();
        }
        return group;

    }

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {
        //制作对应AGCF的数据
        if (!arg0.getName().equals(data.get("PRINCIPAL_ATTRIBUTION").toString())) {
            return null;
        }
        String result = "";
        if ((this.map == null) || (this.map.size() == 0)) {
            this.initMap(arg3, arg0.getName());
        }
        //从现网获取甄别组号
        if (this.map.containsKey(data.get("TRUNK_GROUP"))) {
            result = String.valueOf(this.map.get(data.get("TRUNK_GROUP")));
        } else {
            return null;
        }
        return new String[] { result };
    }

    private void initMap(String dbFile, String deviceName) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "SELECT DISTINCT TRUNK_GROUP,DSP FROM HW_IMS_AGCF_TGDSG m where trunk_group not in  (SELECT n.trunk_group FROM TCM_IMS_AGCF_TRUNGROP_FITER n WHERE n.principal_attribution=m.devicename) and DEVICENAME='" + deviceName + "' ";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
        for (Map<String, ?> temp : resultList) {
            String trunk_group = temp.get("TRUNK_GROUP").toString();
            String dsp = temp.get("DSP").toString();
            this.map.put(trunk_group, dsp);

        }

    }
}
