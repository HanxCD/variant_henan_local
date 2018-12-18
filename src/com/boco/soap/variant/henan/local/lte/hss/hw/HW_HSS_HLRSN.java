package com.boco.soap.variant.henan.local.lte.hss.hw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class HW_HSS_HLRSN  extends VariantValueInvoke{
	
	 private Map<String, String> map = null; 
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {
		String neName=arg0.getName();//网元名称、
		String result=null;;
		 if (null == neName) {
	           // result = "[NULL]";
	        } else {
	            if (this.map == null) {
	                this.map = new HashMap<String, String>();
	                this.initMap(dbFile);
	            }
	                if(map.containsKey(neName)){
	                 result=map.get(neName).toString();
	                
	                }
	           }
	        	return result.split(","); 
	}
	
	 private void initMap(String dbFile) {
	        DataQueryUtils utils = DataQueryUtils.getInstance();
	        String sql = "select devicename,group_concat(hlrsn) from tcm_hss_info group by devicename";
	        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

	        for (Map<String, ?> temp : resultList) {
	            this.map.put(temp.get("DEVICENAME").toString(), temp.get("HLRSN").toString());
	        }
	    }
	

	

}
