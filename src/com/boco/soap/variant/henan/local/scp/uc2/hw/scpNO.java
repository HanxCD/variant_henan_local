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
package com.boco.soap.variant.henan.local.scp.uc2.hw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 *
 */
public class scpNO extends VariantValueInvoke {

    private Map<String, String> map = null;

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {

        Object pro = data.get("PROVINCE");
        if (this.map == null) {
            this.map = new HashMap<String, String>();
            this.initMap(dbFile);
        }
        String scpNo = this.map.get(pro.toString().trim());
        return new String[] { scpNo };
    }

    private void initMap(String dbFile) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "select PROVINCE,SCPID from TCM_UC2_SCP";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

        for (Map<String, ?> temp : resultList) {
            this.map.put(temp.get("PROVINCE").toString(), temp.get("SCPID").toString());

        }
    }
    //	private String getScpNo(String pro,String dbFile) {
    //		DataQueryUtils utils = DataQueryUtils.getInstance();
    //
    //		String sql = "select SCPID from TCM_UC2_SCP where PROVINCE='"+pro+"';";
    //
    //		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
    //	
    //		if (resultList.size() != 0) {
    //			return (String) resultList.get(0).get("SCPID");
    //
    //		} else {
    //			return "[NULL]";
    //		}
    //	}
}