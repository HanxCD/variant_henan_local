package com.boco.soap.info;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class CSCNameDXTTWTDXCS extends VariantValueInvoke {
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
			this.initCityMap(dbFile,arg0.getName());
		}
		
		String cityStr = citymap.get(arg0.getName()) ;
		for (Entry<String, String> entityEntry : citymap.entrySet()) {
			if (entityEntry.getKey().contains(arg0.getName())) {
				cityStr=entityEntry.getValue();
				break;
			}
		}
		
		
		
		
		
		
		return null;
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
	
	private void initCityMap(String dbFile,String name) {
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "select '" + name + "' DEVICENAME,COVER_CITY from TCM_HW_GMSS_MTB_REL where GROUP_NAME in (select GROUP_NAME from TCM_HW_GMSS_MTB_REL where DEVICENAME='" + name + "')";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

		  String Sbcity = "";
	        for (Map<String, ?> temp : resultList) {
	            Sbcity += "," + temp.get("COVER_CITY").toString();
	        }
	        this.citymap.put(name, Sbcity.substring(1));
	}

}
