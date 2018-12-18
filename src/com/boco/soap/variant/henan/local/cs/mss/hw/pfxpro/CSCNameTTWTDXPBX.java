package com.boco.soap.variant.henan.local.cs.mss.hw.pfxpro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * ��Ϊ�˾ֺ����ֹ�
 * 
 * @author
 * @Email
 * 
 */
public class CSCNameTTWTDXPBX extends VariantValueInvoke {
	private Map<String, String> map=null;

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {
		Object o = data.get("BUSI_CITY");
		if (map == null) {
			map = new HashMap<String, String>();
			this.initMap(dbFile);
		}
		String gtrcStr = map.get(o.toString()) + "DX,"+map.get(o.toString()) + "TT,"+map.get(o.toString()) + "WT";
		if(o.toString().equals("鹤壁"))
			gtrcStr = map.get(o.toString()) + "DX,"+map.get(o.toString()) + "TT,"+map.get(o.toString()) + "WT,"+"HEBPBX,矿务局-HEB";
		if(o.toString().equals("三门峡"))
			gtrcStr = map.get(o.toString()) + "DX,"+map.get(o.toString()) + "TT,"+map.get(o.toString()) + "WT,"+"SMXPBX";
		return gtrcStr.split(",") ;
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