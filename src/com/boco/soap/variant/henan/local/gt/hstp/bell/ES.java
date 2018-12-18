package com.boco.soap.variant.henan.local.gt.hstp.bell;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class ES extends VariantValueInvoke {
	private Map<String, String> map = null;

	/*
	 * Editor:Jianwen Sha Edited time:2015-8-11 10:20:21 
	 * 查询H局局名。
	 */
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {

		Object o = data.get("PROVINCE");
		if (map == null) {
			map = new HashMap<String, String>();
			this.initMap(dbFile);
		}
		String dataNo = map.get(o.toString());
		if (dataNo == null)
			return new String[] { "[NULL]" };
		else {
			if (dataNo.contains("HSTP1")) {
				dataNo = dataNo.replace("HSTP1", "HSTP");
			} else {
				dataNo = dataNo.replace("H1", "HSTP");
			}
			return new String[] { dataNo };
		}

	}

	private void initMap(String dbFile) {
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "select BUREAU_DATA_NO,PROVINCE  from TCM_ALL_LSTPHSTP  WHERE  BUREAU_DATA_NO like '%H1%' OR BUREAU_DATA_NO like '%HSTP1%' ";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

		for (Map<String, ?> temp : resultList) {
			map.put(temp.get("PROVINCE").toString(), temp.get("BUREAU_DATA_NO")
					.toString());

		}
	}

}
