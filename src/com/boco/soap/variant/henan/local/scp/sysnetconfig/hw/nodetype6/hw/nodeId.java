/*
 * Copyright 2014 BOCO Inter-Telecom (xi'an).
 * All rights reserved.
 * project name: variant_sichuan
 * version V1.0
 * -------------------------------------------
 * author: lijixin
 * date: 2014-10-31
 * note:
 */
package com.boco.soap.variant.henan.local.scp.sysnetconfig.hw.nodetype6.hw;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 *
 */
public class nodeId extends VariantValueInvoke {

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {

        Object nodeId = data.get("NODE_ID");
        Object sauId = data.get("SAU_MODUL_ID");

        if (null == nodeId) {
            return new String[] { "[NULL]" };
        } else {
            return new String[] { "5" + nodeId + sauId };

            //			String[] sauIds = this.getSauID(nodeId.toString().trim(), dbFile);
            //			String[] scpNo = new String[sauIds.length];
            //			for(int i = 0 ; i < sauIds.length ;i++){
            //				scpNo[i]="5"+nodeId+sauIds[i];				
            //			}
            //			return scpNo;
        }
    }
    //	private String[] getSauID(String nodeId, String dbFile) {
    //		DataQueryUtils utils = DataQueryUtils.getInstance();
    //		String sql = "select SAU_MODUL_ID from TCM_SYS_NETCONFIG where NODE_ID='"+nodeId+"'; ";
    //		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
    //		String[] result = new String[resultList.size()];
    //		if (resultList.size() != 0) {
    //			for(int i = 0 ; i < resultList.size() ;i++){
    //				result[i] = resultList.get(i).get("SAU_MODUL_ID").toString();
    //			}
    //			return result;
    //		} 		
    //		 else {
    //			return new String[] { "[NULL]" };
    //		}	
    //	}
}