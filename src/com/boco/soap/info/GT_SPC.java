package com.boco.soap.info;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class GT_SPC extends VariantValueInvoke{
	private Map<String, String> map  =new HashMap<String, String>();
	
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {
		Object o = data.get("BUSI_CITY");
		String result="";
		String spcs="";
		if (map == null) {
			map = new HashMap<String, String>();
			this.initMap(dbFile);
		}
		if(null==o){
			result = "[NULL]";
		} else {
			if(map.containsKey(o.toString())){
				result=map.get(o.toString());
			}		
			
		}
		return new String[]{result};
	}

	private void initMap(String dbFile) {
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "select cover_city,stp1_spc,stp2_spc from TCM_LOCAL_LSTP ";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
		for (Map<String, ?> temp : resultList) {
			String spc = temp.get("stp1_spc").toString();
			String spc2 = temp.get("stp2_spc").toString();
			map.put(temp.get("cover_city").toString(), spc+"|"+spc2);

		}

	}

}
