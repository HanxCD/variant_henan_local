package com.boco.soap.variant.henan.local.shortnumber.gmsc.hw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * 路由ZZIMS3-地市简拼 
 * 
 * @author changquanzhou
 * 
 */
public class HW_GMSS_CNACLD_RSNAME_ZZIMS3 extends VariantValueInvoke {
	private Map<String, String> map = null;// 设备覆盖地市map
	private Map<String, String> deviceMap = new HashMap<String, String>();// 设备主备map
	@Override
	public String[] getValues(INeElement ne, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {
		if (map == null) {
			map = new HashMap<String, String>();
			this.initMap(dbFile);
		}
		Object oCity = data.get("BUSI_CITY");
		String route = null;
		if (null == oCity) {
			route = "[NULL]";
		} else {
			String routeName = (data.get("ROUTE_NAME") != null && !data.get(
					"ROUTE_NAME").toString().equals("")) ? data.get(
					"ROUTE_NAME").toString() : "";
			route = this.getCity(oCity.toString(), dbFile);
			Object oAccessArea = data.get("ACCESS_AREA");
			String accessArea = oAccessArea != null ? oAccessArea.toString()
					: "";
			
			String covercitys = map.get(ne.getName());
			// 如果覆盖地市包含接入地
			if (!accessArea.equals("") && (accessArea.contains(oCity.toString())||oCity.toString().equals("全省")) && (covercitys!=null && covercitys.contains(accessArea))) {
				if (!routeName.equals("")) {
					route = routeName;
				} else {
					route = "ZZIMS3-" + route;
				}
			} else {
				route = "ZZA";
			}

		}

		return new String[] { route };

	}

	private String getCity(String cityChi, String dbFile) {
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "SELECT * FROM TCM_LOCAL_CITY_LIST WHERE BUSI_CITY = '"
				+ cityChi + "'";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
		String cityStr = "";
		if (resultList != null && resultList.size() > 0) {
			cityStr = resultList.get(0).get("SIMPLENAME").toString().trim();
		}
		return cityStr;
	}
	
	private void initMap(String dbFile) {
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "SELECT A.COVER_CITY AS COVER_CITY,A.DEVICENAME AS DEVICENAME,B.DEVICENAME AS DEVICENAME_BACK,B.COVER_CITY AS COVER_CITY_BACK FROM (SELECT COVER_CITY, DEVICENAME, GROUP_NAME FROM TCM_HW_GMSS_MTB_REL) A LEFT JOIN TCM_HW_GMSS_MTB_REL B ON A.GROUP_NAME = B.GROUP_NAME AND A.DEVICENAME != B.DEVICENAME";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

		for (Map<String, ?> temp : resultList) {
			map.put(temp.get("DEVICENAME").toString(), temp.get("COVER_CITY")
					.toString());
			deviceMap.put(temp.get("DEVICENAME").toString(), temp.get(
					"DEVICENAME_BACK").toString());
		}
	}
}