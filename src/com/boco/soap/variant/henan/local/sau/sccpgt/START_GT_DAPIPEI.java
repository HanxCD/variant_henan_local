package com.boco.soap.variant.henan.local.sau.sccpgt;

import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class START_GT_DAPIPEI extends VariantValueInvoke {
    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> arg2, String arg3) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "SELECT GROUP_CONCAT(START_GT) START_GT FROM TCM_SAU_SCCPGT_CONF ";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, arg3);
        String result = null;
        if ((resultList != null) && (resultList.size() > 0)) {
            return resultList.get(0).get("START_GT").toString().split(",");
        }
        return new String[] { result };
    }
}
