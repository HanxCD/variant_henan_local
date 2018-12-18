package com.boco.soap.variant.valuedynamic;

import com.boco.soap.common.pojo.INeElement;
import com.check.instruction.pojo.checkdata.IDataItem;
import com.check.instruction.pojo.checkdata.IValueDynamicInvoke;

import java.util.List;
import java.util.Map;

/**
 *
 * @author caozengran
 * @time 2017年3月29日14:28:22
 *
 */

public class HW_GMSS_CLDPREANA_CLDNCN_InvokePaImpl implements IValueDynamicInvoke {

    @Override
    public List<Map<String, IDataItem>> getDynamicValues(INeElement neObj, Map<String, String> standData, String dbFile, List<Map<String, IDataItem>> parasOldData) {

        for (Map<String, IDataItem> mapItem : parasOldData) {
            IDataItem cscnameItem = mapItem.get("CSCNAME");//138 ,139
            IDataItem cldncnItem = mapItem.get("CLDNCN");//138 ,139
            IDataItem csItem = mapItem.get("CS");
            if (cscnameItem != null) {
                String cscnameStr = cscnameItem.getChineseValue();
                String clsncnStr = cldncnItem.getChineseValue();
                if (cscnameStr.equals("JIZMSC") || cscnameStr.equals("HEBPBX") || cscnameStr.equals("SMXMSC") || cscnameStr.equals("SMXPBX")) {
                    clsncnStr = "DEFAULT";
                    cldncnItem.setChineseValue(clsncnStr);
                    cldncnItem.setEnglishValue(clsncnStr);
                }
            }
            if (csItem != null) {
                String csItemStr = csItem.getEnglishValue();
                csItem.setEnglishValue(csItemStr.replace("中继侧", ""));
            }
        }
        return parasOldData;
    }
}
