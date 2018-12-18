package com.boco.soap.variant.henan.local.volte.mscserver.nokia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class PARAMETER_CLASS extends VariantValueInvoke {
	private Map<String, String> map = new HashMap<String, String>();
	
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {
		
		if (map == null || map.size() == 0) {
			this.initMap(arg3);
		}
		String result = "[NULL]";
		String pramKey=arg0.getName()+"|"+data.get("PARAM_NAME").toString();
		if (map.containsKey(pramKey)) {
			result=map.get(pramKey);
		}
		return new String[] { result };
	}
	

	private void initMap(String dbFile) {
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "SELECT DEVICENAME,PARAMETER,PARAMETER_CLASS FROM NK_EMSC_ZWOI ";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
		for (Map<String, ?> temp : resultList) {
			String parameter = temp.get("PARAMETER").toString();
			String parameterClass = temp.get("PARAMETER_CLASS").toString();
			map.put(temp.get("DEVICENAME").toString() + "|" + parameter, parameterClass);
		}

	}

}
