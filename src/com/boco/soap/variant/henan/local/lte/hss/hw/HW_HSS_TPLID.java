package com.boco.soap.variant.henan.local.lte.hss.hw;

import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;


public class HW_HSS_TPLID extends VariantValueInvoke{

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {
		// TODO Auto-generated method stub
		
		
		
        String TPLID = "";
        
        TPLID=this.getTPLID(dbFile);
        
        return new String[] { TPLID}; 
	}
	
	 private String getTPLID( String dbFile) {
	        DataQueryUtils utils = DataQueryUtils.getInstance();

	        String sql = "select max(TPLID) from HW_HSS_LTE_APN ";
	       
	        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
	        String[] result = new String[resultList.size()];
	        String TPLID = null;
	        if (resultList.size() != 0) {
	            for (int i = 0; i < resultList.size(); i++) {
	            	TPLID = resultList.get(i).get("TPLID").toString();
	                
	            }
	            return TPLID;
	        } else {
	            return "[NULL]";
	        }
	    }

}
