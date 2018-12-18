package com.boco.soap.variant.henan.local.lte.mme.hw.volte;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class CSFBNOTPREIND extends VariantValueInvoke {

	public Map<String, Object> map=null;
	@Override
	
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {
		Object objNeType = data.get("NE_TYPE");
		Object objVendorName = data.get("VENDOR_NAME");
		String result="[NULL]";
		if (objNeType.toString().trim().toUpperCase().equals("MME") 
				&& objVendorName.toString().trim().toUpperCase().equals("华为")) {
			if(map==null||map.size()==0){
				map=new HashMap<String, Object>();
				initMap(arg3);
			}
			if(map.containsKey(String.valueOf(data.get("ACT_TBNAME")))){
				result=String.valueOf(map.get(data.get("ACT_TBNAME").toString())); 
			}
			return new String[]{result};
		}

		return null;
	}
	
	private void initMap(String dbFile) {
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "SELECT t.PARAM_VALUE,t.ACT_TBNAME FROM TCM_ALL_VOLTEPARAM   t WHERE  t.ne_type='MME' AND t.VENDOR_NAME='华为'  AND t.param_name='CS Fallback not preferred指示'";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

		for (Map<String, ?> temp : resultList) {
			String act_tbName=String.valueOf(temp.get("ACT_TBNAME"));
			map.put(act_tbName, temp.get("PARAM_VALUE"));
		}
	}
}
