package com.boco.soap.variant.volte.mgw.zte;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;
//import java.util.Iterator;
import java.util.List;
import java.util.Map;
public class PORTCLASS extends VariantValueInvoke {
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
		    String sql = "SELECT t.portclass FROM zte_volte_mgw_enetportpara t where DEVICENAME='"+NEName+"'";
		    List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
		    paramValue=new String [resultList.size()];
//		    int i = 0; 
//		    Iterator<?> iter = resultList.iterator();	
//		    while(iter.hasNext()) 
//		    { 
//		     paramValue[i] =(String) iter.next(); 
//		     i++; 
//		    } 
		    int i=0;
			   for(Map<String, ?> temp : resultList){
				   String tmp = (String)temp.get("PORTCLASS");
				   paramValue[i] = tmp;
				   i++;   
			   } 
		  }
}
