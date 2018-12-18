package com.boco.soap.variant.henan.local.scpasvolte.sspconf;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 该变量核查智能网SCPAS中VOLTE设备的独立放音element表的
 * 放音设备组ID列；只做端局关口局设备号码表中的独立放音省份的数据
 * 
 * @author wanghao
 * @Email  wanghao2009.01@gmail.com
 * @Date 2017-03-16
 * 
 * 
 */

public class elementId extends VariantValueInvoke{
	private Map<String, String> map1 = null;
	  private Map<String, String> map2 = null;
	  
	  public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile)
	  {
	    Object mscId = data.get("MSCID");
	    
	    String msgId = "";
	    String pro = "";
	    if (this.map1 == null)
	    {
	      this.map1 = new HashMap();
	      initMap1(dbFile);
	    }
	    if (this.map2 == null)
	    {
	      this.map2 = new HashMap();
	      initMap2(dbFile);
	    }
	    if (mscId == null) {
	      return new String[] { "没有该MSCID" };
	    }
	    pro = (String)this.map2.get(mscId.toString().trim());
	    if (pro == null) {
	      return new String[] { "没有该省份" };
	    }
	    msgId = (String)this.map1.get(pro.toString().trim());
	    if (msgId == null) {
	      return new String[] { "TCM_IP_PROVINCE表中没有该省份" };
	    }
	    return new String[] { msgId };
	  }
	  
	  private void initMap1(String dbFile)
	  {
	    DataQueryUtils utils = DataQueryUtils.getInstance();
	    String sql = "select PROVINCE,ELEMENTGROUPID from TCM_IP_PROVINCE;";
	    List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
	    for (Map<String, ?> temp : resultList) {
	      this.map1.put(temp.get("PROVINCE").toString().trim(), temp.get("ELEMENTGROUPID").toString().trim());
	    }
	  }
	  
	  private void initMap2(String dbFile)
	  {
	    DataQueryUtils utils = DataQueryUtils.getInstance();
	    String sql = "select PROVINCE,MSCID from TCM_MSC_TRANSLATE ";
	    List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
	    for (Map<String, ?> temp : resultList) {
	      this.map2.put(temp.get("MSCID").toString().trim(), temp.get("PROVINCE").toString().trim());
	    }
	  }

}
