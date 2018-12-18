package com.boco.soap.variant.henan.local.cs.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;


/**
 * ��Ϊ�˾ֺ����ֹ�
 * 
 * @author
 * @Email
 * 
 */
public class CSCNameDXC extends VariantValueInvoke {
	private Map<String, String> map=null;
	private Map<String, String> citymap=null;
	
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {
		String sDest="";
		if (map == null) {
			map = new HashMap<String, String>();
			this.initMap(dbFile);
		}
		if (citymap == null) {
			citymap = new HashMap<String, String>();
			this.initCityMap(dbFile);
		}
		
		
		String cityStr = citymap.get(arg0.getName()) ;
		     for (String str : cityStr.split(",")) {  
		String gtrcStr = map.get(str) ;
		sDest+= ","+gtrcStr+"DXC" ;
		     } 
		
		return sDest.substring(1).split(",");
	}
	
	private void initCityMap(String dbFile) {
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "select DEVICENAME,COVER_CITY from TCM_HW_GMSS_MTB_REL";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

		for (Map<String, ?> temp : resultList) {
			citymap.put(temp.get("DEVICENAME").toString(), temp.get("COVER_CITY")
					.toString());

		}
	}

	private void initMap(String dbFile) {
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "select BUSI_CITY,SIMPLENAME from TCM_LOCAL_CITY_LIST";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

		for (Map<String, ?> temp : resultList) {
			map.put(temp.get("BUSI_CITY").toString(), temp.get("SIMPLENAME")
					.toString());

		}
	}
}