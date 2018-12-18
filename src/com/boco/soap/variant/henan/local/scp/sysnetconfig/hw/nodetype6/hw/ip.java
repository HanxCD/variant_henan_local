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
public class ip extends VariantValueInvoke {

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {

        Object sauIp = data.get("SAU_MODUL_IP");

        if (null == sauIp) {
            return new String[] { "[NULL]" };
        } else {
            return new String[] { sauIp.toString().trim() };
            //			String[] sauIds = this.getSauID(nodeId.toString().trim(), dbFile);	
            //			String[] ip = new String[sauIds.length];
            //			for(int i = 0 ; i < sauIds.length ;i++){
            //				ip[i]= this.getSauIP(nodeId.toString(),sauIds[i].toString().trim(), dbFile);
            //			}
            //			return ip;
        }
    }
    //	private String[] getSauID(String nodeId,String dbFile) {
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
    //	private String getSauIP(String nodeId, String sauId, String dbFile) {
    //		DataQueryUtils utils = DataQueryUtils.getInstance();
    //		String sql = "select SAU_MODUL_IP from TCM_SYS_NETCONFIG where NODE_ID='"+nodeId+"' and SAU_MODUL_ID='"+sauId+"'; ";
    //		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
    //		if(resultList.size()!=0){			
    //			return resultList.get(0).get("SAU_MODUL_IP").toString();
    //		}else{
    //			return "[NULL]";
    //		}
    //	}
}