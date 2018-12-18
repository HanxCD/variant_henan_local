
package com.boco.soap.variant.henan.local.gt.mscserver.nokia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class SPC1 extends VariantValueInvoke
{
	private Map<String, String> map = null;
	
	
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {
		String  neName = arg0.getName();
		String resStr = data.get("GT_RESULT")==null?"":data.get("GT_RESULT").toString();
		String hlrName = data.get("HSS")==null?"":data.get("HSS").toString();
		String result="[NULL]";
		if(resStr.equals("191")&&(arg0.getPhysicalAddr().contains("济源")||arg0.getPhysicalAddr().contains("焦作"))){
			String spctemp = data.get("DPC")==null?"":data.get("DPC").toString();
			String spcStr = spctemp.replaceAll(",|\\s+|、|，", ",");
			return new String[] { spcStr };
		}
		if (map == null||map.isEmpty()) {
			map = new HashMap<String, String>();
			this.initMap(dbFile);
		}
		if (map.get(neName) != null) {
			result = map.get(neName).toString();
		}
		int num=Integer.valueOf(resStr.substring(resStr.length()-1));
		if ((num%2==0)&&(hlrName.indexOf("BHW")>-1)) {
			String[] spcArr=result.split(",|\\s+|、|，");
			ArrayUtils.reverse(spcArr);
			result=StringUtils.join(spcArr,",");
		}
		return new String[]{result};
	}

	private void initMap(String dbFile) {
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "SELECT DEVICENAME,SPC FROM TCM_MSC_LOCAL_ZONE";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
		if(resultList.size()>0){
			for (Map<String, ?> temp : resultList) {
				String devicename = temp.get("DEVICENAME")==null?"":temp.get("DEVICENAME").toString();
				String spc = temp.get("SPC")==null?"":temp.get("SPC").toString();
				map.put(devicename,spc);
			}
		}
	}
}