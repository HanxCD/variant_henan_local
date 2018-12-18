package com.boco.soap.variant.henan.local.gt.mscserver.nokia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class ResultCode extends VariantValueInvoke {
	private Map<String, String> map = new HashMap<String, String>();

	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {
		String spcStr = data.get("HLR_NAME")==null?"":data.get("HLR_NAME").toString();
		if (map==null||map.size() == 0) {
			this.initMap(dbFile);
		}
		String resultCodeStr="[NULL]";
		for (String keyStr : map.keySet()) {
			if (keyStr.indexOf(spcStr)!=-1) {
				resultCodeStr = map.get(keyStr);
				break;
			}
		}
		
		return new String[] { resultCodeStr };
	}

	private void initMap(String dbFile) {
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "select HSS,GT_RESULT from NK_GT_RESULT ";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
		if(resultList.size()>0){
			for (Map<String, ?> temp : resultList) {
				String gt_temp = temp.get("GT_RESULT")==null?"":temp.get("GT_RESULT").toString();
				String gt_result=gt_temp.replaceAll("^0*", "");
				String hss = temp.get("HSS")==null?"":temp.get("HSS").toString();
				map.put(hss,gt_result );
			}
		}
	}
}