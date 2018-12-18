package com.boco.soap.variant.henan.local.ldra.hw.DMLNK;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class ADDRID1_STD extends VariantValueInvoke {

	private Map<String, Map<String, String>> _addrnameMap = new HashMap<String, Map<String, String>>();

	/**
	 * @author Sha Jianwen
	 * @time 2015-11-18 10:23:28
	 * DA IP地址名称1
	 * SCTP支持使用多IP来建立链路，第一个IP地址必须提供。该参数必须由ADD IPADDR或者ADD SRVIP命令定义，然后才能在此处引用。
	 * 执行命令时，不区分大小写。
	 * 关联现网表IPADDR:HW_LDRA_IPADDR  根据IP版本类型、IP地址查询ADDRNAME"IP地址名称"
	 * 参数现网值为IP地址  
	 * 指令参数值为：ADDRNAME，字符串名称。ADDRID1="0-3-Signal-IP1"
	 */
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {

		String devNameString = arg0.getName();
		// IP版本类型
		Object _iptpoObject = data.get("IPTP");
		String _iptpString = null;

		Object o = data.get("ADDRID1");
		String result = null;

		if (null == o || _iptpoObject == null) {
			result = "";
		} else if (o.toString().trim().toUpperCase().equals("NULL")) {
			result = "";
			// 在指令生成时，需要调用<nullable>
		} else {
			_iptpString = _iptpoObject.toString().trim();
			if (!_addrnameMap.containsKey(devNameString)) {
				intiAddrNameMap(dbFile, devNameString, _iptpString);
			}
			result = _addrnameMap.get(devNameString).get(o.toString().trim());
		}
		if (null == result) {
			result = "现网表IPADDR中无对应IP地址名称";
		}
		return new String[] { result };
	}

	private void intiAddrNameMap(String dbFile, String devNameString, String IPVER) {
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "SELECT ADDRNAME,IPV41,IPV61 FROM HW_LDRA_IPADDR WHERE DEVICENAME='" + devNameString + "'";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

		Map<String, String> _tempMap = new HashMap<String, String>();

		for (Map<String, ?> temp : resultList) {
			if (temp.get("IPV41") != null && !temp.get("IPV41").toString().toUpperCase().equals("NULL")) {
				_tempMap.put(temp.get("IPV41").toString().trim(), temp.get("ADDRNAME").toString().trim());
			} else {
				_tempMap.put(temp.get("IPV61").toString().trim(), temp.get("ADDRNAME").toString().trim());
			}
		}
		_addrnameMap.put(devNameString, _tempMap);
	}
}
