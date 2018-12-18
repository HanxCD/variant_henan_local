package com.boco.soap.variant.volte.mgw.zte;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;
import java.util.List;
import java.util.Map;


public class SCTPID extends VariantValueInvoke
{
	  public String[] paramValue;
	  
	  public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> arg2, String arg3)
	  {
	    initStr(arg3, arg0.getName());
	    return this.paramValue;
	  }
	  
	  private void initStr(String dbFile, String NEName)
	  {
	    DataQueryUtils utils = DataQueryUtils.getInstance();
	    String sql = "SELECT t.sctp FROM ZTE_IMMGW_M3UASCTP t where DEVICENAME='" + NEName + "'";
	    List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
	    this.paramValue = new String[resultList.size()];
	    
	    int i = 0;
	    for (Map<String, ?> temp : resultList)
	    {
	      String tmp = (String)temp.get("SCTP");
	      this.paramValue[i] = tmp;
	      i++;
	    }
	  }
	}
