package com.boco.soap.variant.henan.local.cs.cnacld.gmsc.huawei;
import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 该变量根据短号码中到省份，到TCM_SSA_OFC表找出省份对应到RSC路由号
 * 
 * @author wanghao
 * @Email wanghao2009.01@gmai.com.cn
 * @Date 2017/03/29
 * 
 */

public class RSC_CONUTRY  extends VariantValueInvoke
{
   private Map<String, String>map = null;
   private Map<String, String>Citymap = null;
   public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile)
   {
 	  Object o = data.get("PROVINCE");
 	  Object name=data.get("ACCESS_AREA");
 	  
 	  String gtrcStr="";
 	  
 	  if(o.toString().equals("河南")){
 		  if(!name.toString().equals("全省")|| name!=null){
 			  if(this.Citymap==null){
 				  this.Citymap = new HashMap();
 				     getBusCity(dbFile);
 			   }
 			 
 			  gtrcStr = (String)this.Citymap.get(name.toString());
 		  }
 		  
 	  }else{//如果省份是外省的话，则取辅助表对应省份的路由号
 		 if (this.map == null)
 	 	  {
 	 		this.map = new HashMap();
 	 		initMap(dbFile);
 	 	  }  
 		 gtrcStr = (String)this.map.get(o.toString());
 	  }
 	    return new String[] { gtrcStr };
   }


private  void initMap(String dbFile){
	DataQueryUtils utils = DataQueryUtils.getInstance();
	String sql = "select distinct(PROVINCE),RSC from TCM_SSA_OFC";
    List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
    for (Map<String, ?> temp : resultList) {
    	this.map.put(temp.get("PROVINCE").toString(), temp.get("RSC").toString());
	}
}

private  void getBusCity(String dbFile){
	DataQueryUtils utils = DataQueryUtils.getInstance();
	String sql = "select BUSI_CITY,RSC from TCM_SSA_OFC";
    List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
    for (Map<String, ?> temp : resultList) {
    	this.Citymap.put(temp.get("BUSI_CITY").toString(), temp.get("RSC").toString());
	}
}
}