package com.boco.soap.variant.henan.local.cs.cnacld.mss.huawei;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 关口局去掉备用数据
 *
 * @author lihao
 * @Email
 *
 */
public class PFX_1254703_MAIN extends VariantValueInvoke {

	private Map<String, String> map=null;
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
							  Map<String, ?> data, String arg3) {

		Object o = data.get("E164");
		Object sCity = data.get("BUSI_CITY");
		String sNetName = arg0.getName();
		String result = null;

		if (map == null) {
			map = new HashMap<String, String>();
			this.initMap(arg3);
		}

		String coverCity = map.get(sNetName);
		if (null == o) {
			result = "[NULL]";
		} else if(coverCity.indexOf(sCity.toString()) >= 0) {
			result = o.toString().trim();
			if (result.startsWith("86")){
				result = "1254703" + result.substring(2);
			}

		}

		return new String[] { result };

	}

	private void initMap(String dbFile) {
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "select COVER_CITY,DEVICENAME from TCM_HW_GMSS_MTB_REL";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

		for (Map<String, ?> temp : resultList) {
			map.put(temp.get("DEVICENAME").toString(), temp.get("COVER_CITY")
					.toString());

		}
	}
}