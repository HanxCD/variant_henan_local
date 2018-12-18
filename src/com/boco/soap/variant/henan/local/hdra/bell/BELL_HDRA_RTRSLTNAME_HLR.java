package com.boco.soap.variant.henan.local.hdra.bell;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class BELL_HDRA_RTRSLTNAME_HLR extends VariantValueInvoke {

	// 保存BELL厂家省际DRA路由设置
	private Map<String, String> _draMap = new HashMap<String, String>();

	/*
	 * Author:Sha Jianwen Edited time:2015-11-11 16:31:36
	 * DESC:获取BELL_HDRA设备IMSI数据路由结果名称: 非归属本省从《DRA号段省际路由表》表，关联BELL厂家的省份查询
	 * 归属本省号段归属HSS加后缀-rs即是路由集名称
	 */
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {
		String result = null;
		Object _province = data.get("PROVINCE").toString();
		if (_province == null) {
			result = "[NULL]";
		} else {
			/*
			 * 山西号段归属河南 归属河南的IMSI，路由名称为归属主HSS名称后加-rs STD:TCM_ALL_LTE_GT_LIST
			 */
			if (_province.toString().contains("河南") || _province.toString().contains("山西")) {
				Object o = data.get("HLR_NAME");
				if (null == o) {
					result = "[NULL]";
				} else {
					// ZZHSS01FE01BNK-rs
					result = o.toString() + "-rs";
				}
			}
			// 省际IMSI，归属覆盖大区的DRA路由，从《DRA号段省际路由表》表，关联BELL厂家的省份查询
			else {
				if (_draMap.size() == 0) {
					initMap(dbFile);
				}
				result = _draMap.get(_province);
			}
		}
		return new String[] { result };
	}

	private void initMap(String dbFile) {
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "SELECT A.PROVINCE,  A.RTRSLTNAME  FROM TCM_DRA_IMSI_ROUTESET A WHERE A.VENDOR_NAME='BELL'";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

		for (Map<String, ?> temp : resultList) {
			_draMap.put(temp.get("PROVINCE").toString(), temp.get("RTRSLTNAME").toString());

		}
	}
}
