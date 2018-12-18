package com.boco.soap.variant.volte.mgw.zte;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class MODULE extends VariantValueInvoke {
	public String [] paramValue;
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> arg2, String arg3) {
		// TODO Auto-generated method stub
		initStr(arg3,arg0.getName());
		return paramValue;
	}
	 private void initStr(String dbFile,String NEName) {
		    DataQueryUtils utils = DataQueryUtils.getInstance();
		    String sql = "SELECT t.module FROM zte_volte_mgw_enetportpara t where DEVICENAME='"+NEName+"'";
		    List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
		    paramValue=new String [resultList.size()];
//		    int i = 0; 
//		    Iterator<?> iter = resultList.iterator();	
//		    while(iter.hasNext()) 
//		    { 
//		     Map temp = (Map)iter.next();
//		     paramValue[i] = temp.keySet();
//		     i++; 
//		    } 
//		    for (Iterator localIterator = resultList.iterator(); localIterator.hasNext(); ) { 
//		    	Map temp = (Map)localIterator.next();
//		      this.paramValue = temp.get("PARAM_VALUE").toString();
//		    }
		   int i=0;
		   for(Map<String, ?> temp : resultList){
			   String tmp = (String)temp.get("MODULE");
			   paramValue[i] = tmp;
			   i++;   
		   } 
		  }
}
