
package com.boco.soap.variant.henan.local.gt.mscserver.nokia;


import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;




import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RESULT extends VariantValueInvoke
{
	private Map<String, String> map = new HashMap<String, String>();

	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {
		String ele_type = data.get("ELE_TYPE")==null?"":data.get("ELE_TYPE").toString();
		if(ele_type.equals("用户号段"))
		{
			String busi_type = data.get("BUSI_TYPE")==null?"":data.get("BUSI_TYPE").toString();
			if(arg0.getCoverCities().values().toString().indexOf(busi_type)<0)
			{
				return null;
			}
		}
		String resultCodeStr="[NULL]";
		if(ele_type.equals("用户号段"))
		{
		String spcStr = data.get("HSS_NAME")==null?"":data.get("HSS_NAME").toString();
		if (map==null||map.size() == 0) {
			this.initMap(dbFile);
		}
		
		for (String keyStr : map.keySet()) {
			if (keyStr.indexOf(spcStr)!=-1) {
				resultCodeStr = map.get(keyStr);
				break;
			}
		}
		}
		else
		{
			resultCodeStr=data.get("RESULT")==null?"":data.get("RESULT").toString();
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