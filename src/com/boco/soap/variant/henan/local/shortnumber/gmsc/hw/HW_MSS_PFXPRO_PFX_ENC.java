package com.boco.soap.variant.henan.local.shortnumber.gmsc.hw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 *   Ϊ ˾ֺ    ֹ 
 * 
 * @author  wanghao
 * @Email
 * 
 */

public class HW_MSS_PFXPRO_PFX_ENC extends VariantValueInvoke {
	private Map<String, String> map = null;

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {
		String pfxString = data.get("START_GT").toString();
		String sDest = "";
		if (map == null) {
			map = new HashMap<String, String>();
			this.initMap(dbFile);
		}
		for (String key : map.keySet()) {
			if (key.indexOf(arg0.getName()+"||") > -1) {
				sDest += ","+map.get(key) + pfxString;
			}
		}
		return sDest.substring(1).split(",");
	}

	private void initMap(String dbFile) {
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "SELECT  BUSI_CITY,SERVER, AREA from HW_GMSC_CITY_RELATION";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
		for (Map<String, ?> temp : resultList) {
			map.put(temp.get("SERVER").toString()+ "||"+temp.get("BUSI_CITY").toString() , temp.get("AREA").toString());

		}
	}
}