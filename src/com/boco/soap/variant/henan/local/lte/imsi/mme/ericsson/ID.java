package com.boco.soap.variant.henan.local.lte.imsi.mme.ericsson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class ID extends VariantValueInvoke{

	private Map<String,String> map=null;
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {
		
		
		Object objNeType = data.get("NE_TYPE");
		Object objActTbName = data.get("ACT_TBNAME");
		Object paraName = data.get("PARAM_NAME");
		String result = "[NULL]";
	
		if (objNeType.toString().trim().toUpperCase().equals("MME")
			&& objActTbName.toString().trim().toUpperCase().equals("VLBO")
			&& paraName!=null) {
			if (map==null||map.size()==0) {
				this.initMap(arg3);
			}
			if (map.containsKey(arg0.getName())) {
				String[] strArr=map.get(arg0.getName()).split(",");
				return strArr;
			}
			return new String[]{result};
		}
			return null;
	}
	
	private void initMap(String dbFile) {
		map=new HashMap<String, String>();
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "SELECT GROUP_CONCAT(IDENTIFIERS) IDENTIFIERS ,DEVICENAME FROM LTE_ER_MME_IMSINS GROUP BY DEVICENAME";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

		for (Map<String, ?> temp : resultList) {
			String deviceName=temp.get("DEVICENAME").toString();
			String identifiers=temp.get("IDENTIFIERS").toString();
			
			map.put(deviceName, identifiers);
		}
	}
}
