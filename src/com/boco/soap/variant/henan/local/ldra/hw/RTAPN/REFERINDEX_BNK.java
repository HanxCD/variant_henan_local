package com.boco.soap.variant.henan.local.ldra.hw.RTAPN;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class REFERINDEX_BNK extends VariantValueInvoke {

	private Map<String, Map<String, Integer>> _rtapnReferMap = new HashMap<String, Map<String, Integer>>();
	private Map<String, Integer> _tmpmMap = new HashMap<String, Integer>();
	private Map<String, Map<String, String>> _rtapnPcrfMap = new HashMap<String, Map<String, String>>();
	private Map<String, String> _tmpPcrfMap = new HashMap<String, String>();

	/**
	 * @author Sha Jianwen
	 * @time 2015-11-24 19:04:34
	 * RTAPN-REFERINDEX:参考号
	 * 该参数的配置原则是：“参考号”可配置为相同，但参考号和APN的组合不能重复。
	 * 该参数，不核查，只制作
	 * 漏做-新增指令，按参考号规范递增；
	 * 错做-先删后增，需沿用已分配参考号；
	 * 参考号规划原则：
	 * HW地区漫游至HW的PCRF 参考号：1-20；
	 * HW-HWPCRF:1-20;
	 * HW-BNKPCRF:21-30；
	 * HW-BERPCRF:31-100;
	 * NK-HWPCRF:211-220;
	 * NK-BNKPCRF:221-230;
	 * NK-BERPCRF:231-300;
	 * ER-HWPCRF:311-320;
	 * ER-BNKPCRF:321-330;
	 * ER-BERPCRF:331+;
	 */
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {

		Object o = data.get("DN");
		String result = null;
		String _dnString = null;
		String _deviceNameString = arg0.getName();

		if (null == o) {
			result = "null";
		} else {
			_dnString = o.toString().trim().toUpperCase();
			if (!_rtapnReferMap.containsKey(_deviceNameString)) {
				getReferIndex(dbFile, _deviceNameString);
				_tmpmMap = _rtapnReferMap.get(_deviceNameString);
				_tmpPcrfMap = _rtapnPcrfMap.get(_deviceNameString);
			}

			Iterator<Entry<String, String>> iter = _tmpPcrfMap.entrySet().iterator();
			while (iter.hasNext()) {
				Entry<String, String> entry = (Entry<String, String>) iter.next();
				// 判断现网是否已存在该APN参考号
				if (entry.getKey().startsWith("BNK") && entry.getKey().contains(_dnString)) {
					result = entry.getValue();
					return new String[] { result };
				}
			}

			// 现网未做APN，漏做新增APN，参考号按规则递增
			if (_dnString.endsWith("BHW")) {// 漫游至PCRFBHW
				_tmpmMap.put("_toHWReferIndex", (_tmpmMap.get("_toHWReferIndex") + 1));
				result = String.valueOf(_tmpmMap.get("_toHWReferIndex"));
			} else if (_dnString.endsWith("BNK")) {// 漫游至PCRFBNK
				_tmpmMap.put("_toBNKReferIndex", (_tmpmMap.get("_toBNKReferIndex") + 1));
				result = String.valueOf(_tmpmMap.get("_toBNKReferIndex"));
			} else if (_dnString.endsWith("BER")) {// 漫游至PCRFBER
				_tmpmMap.put("_toBERReferIndex", (_tmpmMap.get("_toBERReferIndex") + 1));
				result = String.valueOf(_tmpmMap.get("_toBERReferIndex"));
			}
		}
		return new String[] { result };
	}

	/**
	 * 制作新增脚本时，应使用的参考号
	 * NK-HWPCRF:211-220;
	 * NK-BNKPCRF:221-230;
	 * NK-BERPCRF:231-300;
	 * @param dbFile	文件数据库地址
	 * @param deviceName	网元名称
	 */
	private void getReferIndex(String dbFile, String deviceName) {
		DataQueryUtils utils = DataQueryUtils.getInstance();

		Map<String, Integer> _tempMap = new HashMap<String, Integer>();

		// 参考号：NK-HWPCRF:211-220;
		String sql = "SELECT MAX(CAST(REFERINDEX AS INT)) MAX_REFERINDEX  FROM HW_LDRA_RTAPN  WHERE DEVICENAME='" + deviceName
				+ "' AND CAST(REFERINDEX AS INT) >= 211  AND CAST(REFERINDEX AS INT) <= 220";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
		int _toHWReferIndex = Integer.valueOf(resultList.get(0).get("MAX_REFERINDEX").toString().trim());
		_tempMap.put("_toHWReferIndex", _toHWReferIndex);

		// 参考号：NK-BNKPCRF:221-230;
		sql = "SELECT MAX(CAST(REFERINDEX AS INT)) MAX_REFERINDEX  FROM HW_LDRA_RTAPN  WHERE DEVICENAME='" + deviceName + "' AND CAST(REFERINDEX AS INT) >= 221  AND CAST(REFERINDEX AS INT) <= 230";
		resultList = utils.getLocalData(sql, dbFile);
		int _toBNKReferIndex = Integer.valueOf(resultList.get(0).get("MAX_REFERINDEX").toString().trim());
		_tempMap.put("_toBNKReferIndex", _toBNKReferIndex);

		// 参考号： NK-BERPCRF:231-300;
		sql = "SELECT MAX(CAST(REFERINDEX AS INT)) MAX_REFERINDEX  FROM HW_LDRA_RTAPN  WHERE DEVICENAME='" + deviceName + "'  AND CAST(REFERINDEX AS INT) >= 231  AND CAST(REFERINDEX AS INT) <= 300";
		resultList = utils.getLocalData(sql, dbFile);
		int _toBERReferIndex = Integer.valueOf(resultList.get(0).get("MAX_REFERINDEX").toString().trim());
		_tempMap.put("_toBERReferIndex", _toBERReferIndex);

		_rtapnReferMap.put(deviceName, _tempMap);

		// 若现网错做，需要沿用之前已分配的参考号
		sql = "SELECT distinct APN_NAME, REFERINDEX FROM HW_LDRA_RTAPN WHERE DEVICENAME='" + deviceName + "' ORDER BY REFERINDEX ASC";
		resultList = utils.getLocalData(sql, dbFile);
		for (Map<String, ?> temp : resultList) {
			_tmpPcrfMap.put(temp.get("APN_NAME").toString(), temp.get("REFERINDEX").toString());
		}
		_rtapnPcrfMap.put(deviceName, _tmpPcrfMap);

		// 若现网错做，需要沿用之前已分配的参考号
		sql = "SELECT distinct APN_NAME, REFERINDEX FROM HW_LDRA_RTAPN WHERE DEVICENAME='" + deviceName + "' ORDER BY REFERINDEX ASC";
		resultList = utils.getLocalData(sql, dbFile);
		for (Map<String, ?> temp : resultList) {
			_tmpPcrfMap.put(temp.get("APN_NAME").toString(), temp.get("REFERINDEX").toString());
		}
		_rtapnPcrfMap.put(deviceName, _tmpPcrfMap);
	}
}