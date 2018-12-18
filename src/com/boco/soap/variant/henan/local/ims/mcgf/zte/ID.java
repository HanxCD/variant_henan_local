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
package com.boco.soap.variant.henan.local.ims.mcgf.zte;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * gt gmsc�˾� ��Ϊ   sccpgt E164
 * 
 * @author lijixin
 * @Email lijixin2014@boco.com.cn
 * 
 */

public class ID extends VariantValueInvoke{

	private Map<String,String> map=null;
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {
		
		
		Object objNeType = data.get("NE_TYPE");
		Object objActTbName = data.get("ACT_TBNAME");
		Object paraName = data.get("PARAM_NAME");
		String result = "[NULL]";
	
		if (objNeType.toString().trim().toUpperCase().equals("MGCF")
			&& objActTbName.toString().trim().toUpperCase().equals("SIPLNK")
			&& paraName!=null) {
			if (map==null||map.size()==0) {
				this.initMap(arg3);
			}
			if (map.containsKey(arg0.getName())) {
				String[] strArr=map.get(arg0.getName()).split(",");
				return strArr;
			}
			return new String[]{result};
		}
			return null;
	}
	
	private void initMap(String dbFile) {
		map=new HashMap<String, String>();
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "SELECT DEVICENAME,GROUP_CONCAT(ID) IDENTIFIERS FROM ZTE_MGCF_SIPLNK GROUP BY DEVICENAME";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

		for (Map<String, ?> temp : resultList) {
			String deviceName=temp.get("DEVICENAME").toString();
			String identifiers=temp.get("IDENTIFIERS").toString();
			
			map.put(deviceName, identifiers);
		}
	}
}