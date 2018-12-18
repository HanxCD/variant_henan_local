package com.boco.soap.variant.henan.local.ldra.hw;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class EPC_RTEXITNEXTINDEX extends VariantValueInvoke {

	private Map<String, Map<String, String>> _rtexitStdMap = new HashMap<String, Map<String, String>>();

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {

		/**
		 * @author Sha Jianwen
		 * @time 2015年11月26日 下午6:44:52
		 * RTEXIT-REFERINDEX:参考号
		 * 下一路参考号：指向HSS-IMS域参考号
		 * 标准表《中国移动LTE用户号段全量汇总表》TCM_ALL_LTE_GT_LIST
		 * TCM_ALL_LTE_GT_LIST.RTRSLTNAME:为归属HSS名称
		 * 华为HSS命名规则：KAFHSS104FE10BHW-EPC/KAFHSS112FE10BHW-IMS
		 * 诺西HSS命名规则：KAFHSS01FE23BNK/ZZHSS03FE02BNK (FE2代表EPC域，FE0代表IMS域)
		 */
		Object o = data.get("RTRSLTNAME");
		String _rtrsltnameString = null;
		String result = "NULL";
		String deviceNameString = arg0.getName();
		if (null == o) {
			result = "《中国移动LTE用户号段全量汇总表》未匹配 归属HSS";
		} else {
			_rtrsltnameString = o.toString().trim().toUpperCase();

			if (!_rtexitStdMap.containsKey(deviceNameString)) {
				initiMap(dbFile, deviceNameString);
			}
			Iterator<Entry<String, String>> iter = _rtexitStdMap.get(deviceNameString).entrySet().iterator();

			if (_rtrsltnameString.contains("BHW")) {
				// 华为HSS命名规则：KAFHSS104FE10BHW-EPC/KAFHSS112FE10BHW-IMS
				while (iter.hasNext()) {
					Entry<String, String> entry = (Entry<String, String>) iter.next();
					if (entry.getKey().equals(_rtrsltnameString + "-EPC")) {
						result = entry.getValue();
						break;
					}
				}
			} else {
				// 诺西HSS命名规则：KAFHSS01FE23BNK/ZZHSS03FE02BNK
				// (FE2代表EPC域，FE0代表IMS域)
				while (iter.hasNext()) {
					Entry<String, String> entry = (Entry<String, String>) iter.next();
					if (entry.getKey().equals(_rtrsltnameString.replaceAll("FE0", "FE2"))) {
						result = entry.getValue();
						break;
					}
				}
			}

		}

		return new String[] { result };

	}

	private void initiMap(String dbFile, String deviceName) {

		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "SELECT A.RTRSLTNAME,A.REFERINDEX FROM TCM_LDRA_RTEXIT A WHERE A.DEVICENAME='" + deviceName + "' AND A.RTRSLTNAME LIKE '%HSS%'";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

		Map<String, String> _tempMap = new HashMap<String, String>();
		for (Map<String, ?> temp : resultList) {
			_tempMap.put(temp.get("RTRSLTNAME").toString().trim(), temp.get("REFERINDEX").toString().trim());
		}
		_rtexitStdMap.put(deviceName, _tempMap);
	}
}
