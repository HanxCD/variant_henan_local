package com.boco.soap.variant.henan.local.shortnumber.gmsc.hw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * 本地区号+裸号
 * 
 * @author changquanzhou
 * 
 */
public class HW_GMSS_CNACLD_LC_AREACODE_PFX extends VariantValueInvoke {
	private Map<String, String> areacodeMap = new HashMap<String, String>();
	
	private Map<String, String> map = new HashMap<String, String>();// 设备覆盖地市map
	private Map<String, String> deviceMap = new HashMap<String, String>();// 设备主备map
	@Override
	public String[] getValues(INeElement ne, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {
		init();
		initMap(dbFile);
		Object o = data.get("START_GT");
		String result = null;
		if (null == o) {
			result = "[NULL]";
		} else {
			result = o.toString();
			if(!(result.startsWith("037")||result.startsWith("039")))
			{
				return null;
			}
			
		}
		Object oAreaCode = data.get("AREA_CODE");
		String areaCode = oAreaCode!=null?oAreaCode.toString():"";
		Object oCallType = data.get("CALL_TYPE");// 如果拨号方式为 直拨+接入地区号
		if (oCallType != null) {
			String callType = oCallType.toString();
			if (!callType.contains("本地区号")) {
				return null;
			}
			
			String covercity = this.map.get(ne.getName());//驻马店,郑州
			String backNeName =  this.deviceMap.get(ne.getName());
			String backNeCovercity = this.map.get(backNeName);
			//获取主备地市的区号
			String areaCodes = "";
			String[] citys = covercity.split(",");
			for (String _city : citys) {
				areaCodes+=","+_city;
			}
			String[] backCitys = backNeCovercity.split(",");
			for (String _city : backCitys) {
				areaCodes+=","+_city;
			}
			//如果主备网元都没有包含此条标准数据，则过滤掉
			if (!areaCodes.contains(areaCode)) {
				return null;
			}
		}
		return new String[] { result };

	}

	private void initMap(String dbFile) {
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "SELECT A.COVER_CITY AS COVER_CITY,A.DEVICENAME AS DEVICENAME,B.DEVICENAME AS DEVICENAME_BACK,B.COVER_CITY AS COVER_CITY_BACK FROM (SELECT COVER_CITY, DEVICENAME, GROUP_NAME FROM TCM_HW_GMSS_MTB_REL) A LEFT JOIN TCM_HW_GMSS_MTB_REL B ON A.GROUP_NAME = B.GROUP_NAME AND A.DEVICENAME != B.DEVICENAME";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

		for (Map<String, ?> temp : resultList) {
			map.put(temp.get("DEVICENAME").toString(), temp.get("COVER_CITY")
					.toString());
			deviceMap.put(temp.get("DEVICENAME").toString(), temp.get(
					"DEVICENAME_BACK").toString());
		}
	}
	
	private void init() {
		areacodeMap.put("商丘", "370");
		areacodeMap.put("郑州", "371");
		areacodeMap.put("安阳", "372");
		areacodeMap.put("新乡", "373");
		areacodeMap.put("许昌", "374");
		areacodeMap.put("平顶山", "375");
		areacodeMap.put("潢川", "376");
		areacodeMap.put("信阳", "376");
		areacodeMap.put("南阳", "377");
		areacodeMap.put("洛阳", "379");
		areacodeMap.put("焦作", "391");
		areacodeMap.put("鹤壁", "392");
		areacodeMap.put("濮阳", "393");
		areacodeMap.put("周口", "394");
		areacodeMap.put("漯河", "395");
		areacodeMap.put("驻马店", "396");
		areacodeMap.put("三门峡", "398");
		areacodeMap.put("济源", "391");
		areacodeMap.put("开封", "371");

	}
}