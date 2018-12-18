package com.boco.soap.variant.henan.local.ims.tas;

import java.util.HashMap;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class HW_TAS_DEST extends VariantValueInvoke {
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> arg2, String arg3)
	  {
	    String city = arg2.get("BUSI_CITY").toString();
	    String result = getrsname(city);
	    return new String[] { result };
	  }
	  
	  public String getrsname(String city)
	  {
	    String rsname = "[NULL]";
	    switch (City.getCity(city))
	    {
	    case 安阳: 
	      rsname = "372";
	      break;
	    case 鹤壁: 
	      rsname = "392";
	      break;
	    case 焦作: 
	      rsname = "391";
	      break;
	    case 开封: 
	      rsname = "378";
	      break;
	    case 濮阳: 
	      rsname = "393";
	      break;
	    case 商丘: 
	      rsname = "370";
	      break;
	    case 三门峡: 
	      rsname = "398";
	      break;
	    case 新乡: 
	      rsname = "373";
	      break;
	    case 济源: 
		  rsname = "391";
		  break;  
	    }
	    return rsname;
	  }
	  
	  static enum City
	  {
	   安阳, 鹤壁,  焦作,  开封,  漯河,  洛阳,  南阳,  平顶山,  濮阳,  商丘,  三门峡,  许昌,  信阳,  新乡, 周口, 驻马店, 郑州,济源;
	    
	    public static City getCity(String city)
	    {
	      return valueOf(city);
	    }
	  }
	
}
