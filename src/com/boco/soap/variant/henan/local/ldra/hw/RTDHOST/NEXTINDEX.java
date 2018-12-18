package com.boco.soap.variant.henan.local.ldra.hw.RTDHOST;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class NEXTINDEX extends VariantValueInvoke {

	private Map<String, Map<String, Integer>> _rtexitMap = new HashMap<String, Map<String, Integer>>();
	private Map<String, Integer> _neRtexitMap = new HashMap<String, Integer>();

	/*
	 * // 现网PCRF设备ReferIndex最大值； private static int _maxPCRFreferIndex =
	 * Integer.MIN_VALUE; // 现网HSS设备ReferIndex最大值； private static int
	 * _maxHSSreferIndex = Integer.MIN_VALUE;
	 */

	/**
	 * @author Sha Jianwen
	 * @time 2015-11-24 15:52:47
	 * RTDHOST-NEXTINDEX：下一路由参考号
	 * 用于指定下一路由规则的查询范围。进行进一步路由查询时只能在该参考号的范围内查询。
	 * 核查或制作前，用户会将调整RTEXIT数据并作为标准导入《LDRA灵活路由出口标准表》TCM_LDRA_RTEXIT作为标准数据使用；
	 * 与PGW对接时，选择Gx:
	 * 				PGW目的主机名（省内后缀）（用于端局故障时，LDRA直接回失败，如果不配置，消息将根据8个e通配符号路由重选发往HDRA回路由失败）
	 * 				参考号：1
	 * 				下一路由规则：灵活路由出口
	 * 				下一路由参考号：指向C链路（路由出口索引为1），集团已固定下一路由参考号为1.
	 * 				(若核查设备为ZZDRA01BHW，则C链为ZZDRA02BHW；ZZDRA02BHW-C链ZZDRA01BHW)
	 * 与PCRF对接时，选择Gx和Rx:
	 * 				PCRF主机名（本省）
	 * 				参考号：1
	 * 				下一路由规则：灵活路由出口
	 * 				下一路由参考号：指向具体PCRF（路由出口索引从2开始，预留至50）
	 * 与HSS对接时，选择S6a、Cx、Sh、Zh、SLh
	 * 				HSS主机名（本省）
	 * 				参考号：CX:2；Sh:3
	 * 				下一路由规则：灵活路由出口
	 * 				下一路由参考号：Cx、Sh的下一路由参考号一致，指向具体HSS（路由出口索引从51开始）	
	 *  与I/S-CSCF对接时，选择Cx
	 * 			参考号：2
	 * 			目的主机名：S-CSCF目的主机名（本省主机名后缀）
	 * 			下一路由规则：灵活路由出口
	 * 			下一路由参考号：指向C链路（本省固定为1）
	 * 与TAS对接时，选择Sh
	 * 			参考号：3
	 * 			目的主机名：AS主机名（集中部署的AS主机名的后缀） 
	 * 			下一路由规则：灵活路由出口
	 * 			下一路由参考号：指向本省HDRA；本省HDRA固定为0
	 */
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {

		Object _devtypeObject = data.get("DEVTYPE");
		String _devTypeString = null;
		String _deviceNameString = arg0.getName();
		Object _rtrsltnameObject = data.get("RTRSLTNAME");
		String _rtrsltnameString = null;

		String result = null;
		if (null == _devtypeObject) {
			result = "";
		} else {
			_devTypeString = _devtypeObject.toString().trim().toUpperCase();
		}

		if (_devTypeString.equals("PGW")) {
			result = "1";
		} else if (_devTypeString.equals("PCRF") && _rtrsltnameObject != null) {

			_rtrsltnameString = _rtrsltnameObject.toString().trim();

			// 下一路由参考号：指向具体PCRF（路由出口索引从2开始，预留至50）
			if (!_rtexitMap.containsKey(_deviceNameString)) {
				initiRtexitMap(_deviceNameString, dbFile);
				_neRtexitMap = _rtexitMap.get(_deviceNameString);
			}

			if (_neRtexitMap.containsKey(_rtrsltnameString)) {
				/*if (_neRtexitMap.get(_rtrsltnameString) >= 51) {
					result = "指向具体PCRF（路由出口索引从2开始，预留至50）";
				} else {
					result = String.valueOf(_neRtexitMap.get(_rtrsltnameString));
				}*/
				result = String.valueOf(_neRtexitMap.get(_rtrsltnameString));

			} else {
				result = "《LDRA灵活路由出口标准表》未配置" + _rtrsltnameString + "路由出口参考号";
			}
		} else if (_devTypeString.equals("HSS") && _rtrsltnameObject != null) {

			_rtrsltnameString = _rtrsltnameObject.toString().trim();

			// 下一路由参考号：指向具体HSS（路由出口索引从51开始）
			if (!_rtexitMap.containsKey(_deviceNameString)) {
				initiRtexitMap(_deviceNameString, dbFile);
				_neRtexitMap = _rtexitMap.get(_deviceNameString);
			}

			if (_neRtexitMap.containsKey(_rtrsltnameString)) {
				/*if (_neRtexitMap.get(_rtrsltnameString) < 51) {
					result = "指向具体HSS（路由出口索引从51开始）";
				} else {
					result = String.valueOf(_neRtexitMap.get(_rtrsltnameString));
				}*/
				result = String.valueOf(_neRtexitMap.get(_rtrsltnameString));

			} else {
				result = "《LDRA灵活路由出口标准表》未配置" + _rtrsltnameString + "路由出口参考号";
			}
		} else if (_devTypeString.equals("I/S-CSCF")) {
			result = "1";
		} else if (_devTypeString.equals("TAS")) {
			result = "0";
		}

		return new String[] { result };
	}

	private void initiRtexitMap(String deviceName, String dbFile) {
		DataQueryUtils utils = DataQueryUtils.getInstance();

		String sql = "SELECT RTRSLTNAME, REFERINDEX FROM TCM_LDRA_RTEXIT  WHERE DEVICENAME='" + deviceName + "'";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

		Map<String, Integer> _tempMap = new HashMap<String, Integer>();
		for (Map<String, ?> temp : resultList) {
			_tempMap.put(temp.get("RTRSLTNAME").toString().trim(), Integer.valueOf(temp.get("REFERINDEX").toString().trim()));
		}

		_rtexitMap.put(deviceName, _tempMap);
	}
}
