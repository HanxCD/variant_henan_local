package com.boco.soap.variant.henan.local.ldra.hw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class LDRA_RTAPN_REFERINDEX_HW extends VariantValueInvoke {

	/**
	 * @author Sha Jianwen
	 * @time 2016-2-20 11:48:18
	 * 标准表《省内HLR号段汇总表》 TCM_LOCAL_GTT_LIST
	 *  TCM_LOCAL_GTT_LIST.BUSI_CITY:为归属地市
	 * 标准表中没有地市归属PCRF信息，需从《本省PCRF分区表》中查询地市对应的APN参考号， ADD RTMSISDN:
	 * REFERINDEX=2, 华为区号段漫游至其他厂家POOL片区;
	 * REFERINDEX=3, 诺西区号段漫游至其他厂家POOL片区;
	 * REFERINDEX=4, 爱立信区号段漫游至其他厂家POOL片区;
	 * GX接口：下一路由规则：RTAPN(APN路由)
	 */

	// 漫游地为华为POOL区的APN参考号
	private Map<String, String> _cityPcrfMap = new HashMap<String, String>();

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {
		Object _cityObject = data.get("BUSI_CITY");
		String result = "NULL";
		if (null != _cityObject) {
			if (_cityPcrfMap.size() == 0) {
				initiCityPcrfMap(dbFile);
			}
			if (_cityPcrfMap.containsKey(_cityObject)) {
				// 避免标准工单中出现 “郑州市”、“郑 州”等字样，与数据库配置表“郑州”匹配不上
				result = _cityPcrfMap.get(_cityObject.toString().replace("市", "").replace(" ", "").trim());
			} else {
				return new String[] { "《本省PCRF分区表》中未配置" + _cityObject.toString() + "漫游区APN参考号" };
			}
		}
		return new String[] { result };
	}

	/**
	 * 漫游地为华为POOL区的RTAPN参考号
	 * 
	 * @param dbFile
	 *            文件数据库路径
	 */
	private void initiCityPcrfMap(String dbFile) {
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sqlString = "SELECT BUSI_CITY,APN_NEXTINDEX_HW FROM TCM_HN_PCRF_AREA ";

		List<Map<String, ?>> resultList = utils.getLocalData(sqlString, dbFile);
		for (Map<String, ?> itemMap : resultList) {
			_cityPcrfMap.put(itemMap.get("BUSI_CITY").toString().trim(), itemMap.get("APN_NEXTINDEX_HW").toString().trim());
		}
	}

}