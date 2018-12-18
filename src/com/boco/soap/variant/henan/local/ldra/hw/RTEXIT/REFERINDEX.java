package com.boco.soap.variant.henan.local.ldra.hw.RTEXIT;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class REFERINDEX extends VariantValueInvoke {

	private Map<String, Map<String, String>> _rtexitStdMap = new HashMap<String, Map<String, String>>();
	private Map<String, String> _neRtexitMap = new HashMap<String, String>();

	/**
	 * @author Sha Jianwen
	 * @time 2015年11月26日 下午5:10:41
	 * RTEXIT-REFERINDEX:参考号
	 * “参考号”唯一标识一条灵活路由出口配置信息数据记录
	 * 指向具体PCRF（路由出口索引从2开始，预留至50）
	 * 指向具体HSS（路由出口索引从51开始）
	 * 鉴于新增入网设备时，RTAPN-下一路由参考号和RTEXIT-参考号 无法同时做，变量间相互独立，无关联，无法保证RTEXIT的参考号顺序
	 * 解决方案：设备入网前，用户将RTEXIT表的全量正确数据作为标准数据，更新至《LDRA灵活路由出口标准表-TCM_LDRA_RTEXIT》
	 * 所有涉及RTEXIT中的参考号、路由结果名称，均从此标准出。
	 * LDRA灵活路由出口标准表 与  DRA局向调测工单  的关联字段为  路由结果名称 RTRSLTNAME
	 */
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {

		Object _devTypeObject = data.get("DEVTYPE");
		String _devTypeString = null;
		String _deviceNameString = arg0.getName();
		Object o = data.get("RTRSLTNAME");
		String result = null;

		if (null == o || _devTypeObject == null) {
			result = "《DRA局向调测工单》未填写路由结果名称或设备类型";
		} else {
			_devTypeString = _devTypeObject.toString().trim().toUpperCase();

			if (!_rtexitStdMap.containsKey(_deviceNameString)) {
				initiMap(dbFile, _deviceNameString);
				_neRtexitMap = _rtexitStdMap.get(_deviceNameString);
			}
			if (_neRtexitMap.containsKey(o.toString().trim())) {

				result = _neRtexitMap.get(o.toString().trim());

				/*// 指向具体PCRF（路由出口索引从2开始，预留至50）
				if (_devTypeString.equals("PCRF")) {
					if (Integer.valueOf(result) > 51) {
						result = "PCRF路由出口不符合集团规范：指向具体PCRF(路由出口索引从2开始，预留至50)";
					}
				} else if (_devTypeString.equals("HSS")) {
					// 指向具体HSS（路由出口索引从51开始）
					if (Integer.valueOf(result) < 51) {
						result = "HSS路由出口不符合集团规范：指向具体HSS(路由出口索引从51开始)";
					}
				}*/

			} else {
				result = "《LDRA灵活路由出口标准表》未精确匹配:路由结果名称" + o.toString().trim();
			}
		}
		return new String[] { result };
	}

	private void initiMap(String dbFile, String deviceName) {

		DataQueryUtils utils = DataQueryUtils.getInstance();

		String sql = "SELECT RTRSLTNAME, REFERINDEX FROM TCM_LDRA_RTEXIT WHERE DEVICENAME='" + deviceName + "'";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

		Map<String, String> _tempMap = new HashMap<String, String>();
		for (Map<String, ?> temp : resultList) {
			_tempMap.put(temp.get("RTRSLTNAME").toString().trim(), temp.get("REFERINDEX").toString().trim());
		}
		_rtexitStdMap.put(deviceName, _tempMap);
	}
}
