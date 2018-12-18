package com.boco.soap.variant.henan.local.ldra.hw.RTAPN;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class NEXTINDEX_ALL_CMNET extends VariantValueInvoke {

	private Map<String, Map<String, String>> _rtexitReferIndexMap = new HashMap<String, Map<String, String>>();

	/**
	 * @author Sha Jianwen
	 * @time 2015年11月26日 上午11:57:59
	 * RTAPN-NEXTINDEX:下一路由参考号
	 * 下一路由参考规则：
	 * 			CMNET/cmnet/CMWAP/cmwap 指向归属PCRF的灵活路由出口表参考号(RTEXIT-REFERINDEX)；
	 * 			IMS/ims/SOS/sos	  指向漫游地PCRF—POOL的灵活路由参考号。
	 * 			河南规划：BHWPCRFPOOL1:10	BNKPCRFPOOL1:11		BERPCRFPOOL1:12
	 */
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {

		Object _dnObject = data.get("DN");
		String _dnString = "";
		String _deviceNameString = arg0.getName();
		String result = null;

		if (_dnObject == null) {
			result = "NULL";
		} else {

			_dnString = _dnObject.toString().trim().toUpperCase();

			if (!_rtexitReferIndexMap.containsKey(_deviceNameString)) {
				initiMap(_deviceNameString, dbFile);
			}
			if (_rtexitReferIndexMap.get(_deviceNameString).containsKey(_dnString)) {
				result = _rtexitReferIndexMap.get(_deviceNameString).get(_dnString);
			} else {
				result = "《LDRA灵活路由出口标准表》中未配置" + _dnString + "参考号";
			}
		}

		return new String[] { result };
	}

	private void initiMap(String deviceName, String dbFile) {

		DataQueryUtils utils = DataQueryUtils.getInstance();

		String sql = "SELECT A.RTRSLTNAME,A.REFERINDEX FROM TCM_LDRA_RTEXIT A WHERE A.DEVICENAME='" + deviceName + "'";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

		Map<String, String> _tempMap = new HashMap<String, String>();
		for (Map<String, ?> temp : resultList) {
			_tempMap.put(temp.get("RTRSLTNAME").toString().trim(), temp.get("REFERINDEX").toString().trim());
		}
		_rtexitReferIndexMap.put(deviceName, _tempMap);
	}
}
