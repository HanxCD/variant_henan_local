package com.boco.soap.variant.henan.local.cs.cnacld.gmsc.huawei;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;




public class ROUTE extends VariantValueInvoke {
	private Map<String, String> map=null;
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> arg2, String arg3) {
		Object o = arg2.get("GT_TYPE");
		Object sCity = arg2.get("BUSI_CITY");
		String sRoute="";
		if (map == null) {
			map = new HashMap<String, String>();
			this.initMap(arg3);
		}
		if (null != o) {
			if(o.equals("铁通固网"))
				sRoute="INVALID";
			if(o.equals("联通固网"))
				sRoute=map.get(sCity)+"WT";
			if(o.equals("电信固网"))
				sRoute=map.get(sCity)+"DX";
			if(o.equals("IMS"))
				sRoute="ZZIMS-"+map.get(sCity);
			if(o.equals("专网"))
				sRoute=map.get(sCity)+"PBX1";
			return new String[] { sRoute };
		} else {
			return new String[] { "[NULL]" };
		}
	}
	
	private void initMap(String dbFile) {
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "select BUSI_CITY,SIMPLENAME from TCM_LOCAL_CITY_LIST";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

		for (Map<String, ?> temp : resultList) {
			map.put(temp.get("BUSI_CITY").toString(), temp.get("SIMPLENAME")
					.toString());

		}
	}
}
