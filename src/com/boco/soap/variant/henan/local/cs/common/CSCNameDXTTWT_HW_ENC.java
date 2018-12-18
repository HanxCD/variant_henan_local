package com.boco.soap.variant.henan.local.cs.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class CSCNameDXTTWT_HW_ENC extends VariantValueInvoke {
	private Map<String, String> map=null;
	private Map<String, String> citymap=null;
	
	private Logger logger = LoggerFactory.getLogger(CSCNameDXTTWT_HW_ENC.class);
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
		for (Entry<String, String> entityEntry : citymap.entrySet()) {
			if (entityEntry.getKey().contains(arg0.getName())) {
				cityStr=entityEntry.getValue();
				break;
			}
		}
		
		for (String str : cityStr.split(",")) {
			String gtrcStr = map.get(str);
			// 三门峡多个SMXPBX
			// 鹤壁多2个：HEBPBX、 矿务局-HEB
			sDest += "," + gtrcStr + "DX," + gtrcStr + "TT," + gtrcStr + "WT";
			if (str.equals("三门峡"))
				sDest += ",SMXPBX";
			if (str.equals("鹤壁"))
				sDest += ",HEBPBX,矿务局-HEB";
			if (str.equals("郑州"))
				sDest += ",ZYTX";
		}
		
		logger.debug("--------------------------------------------CSCNameDXTTWT:"+sDest+"---------------------------------------");
		return sDest.substring(1).split(",");
	}
	
	private void initCityMap(String dbFile) {
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "   SELECT  DEVICENAME,GROUP_NAME,COVER_CITY from TCM_HW_GMSS_MTB_REL ";
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