package com.boco.soap.variant.volte.mgw.zte;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class NEGOTIATE extends VariantValueInvoke {
    public String paramVue = "";

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {
        if ("".equals(this.paramVue)) {
            this.initStr(arg3);
        }
        return new String[] { this.paramVue };
    }

    private void initStr(String dbFile) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "SELECT t.PARAM_VALUE FROM TCM_ALL_CS_VOLTEPARAM  t WHERE  t.ne_type='IMSMGW' AND t.VENDOR_NAME='中兴' AND t.ACT_TBNAME='ENETPORTPARA' AND t.param_name='NEGOTIATE'";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

        for (Iterator<Map<String, ?>> localIterator = resultList.iterator(); localIterator.hasNext();) {
            Map<?, ?> temp = localIterator.next();
            this.paramVue = temp.get("PARAM_VALUE").toString();
        }
    }
}
