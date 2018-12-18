package com.boco.soap.variant.henan.local.ims.scscf.zte;


//参数核查中横表多行中兴SCSCF的show TELANA的,取TELANA中ID变量的方法

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P_TELANA_NO
  extends VariantValueInvoke
{
  private Map<String, String> map = null;
  
  public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3)
  {
    Object objNeType = data.get("NE_TYPE");
    Object objActTbName = data.get("ACT_TBNAME");
    Object paraName = data.get("PARAM_NAME");
    String result = "[NULL]";
    if ((objNeType.toString().trim().toUpperCase().equals("SCSCF")) && 
      (objActTbName.toString().trim().toUpperCase().equals("TELANA")) && (paraName != null))
    {
      if ((this.map == null) || (this.map.size() == 0)) {
        initMap(arg3);
      }
      if (this.map.containsKey(arg0.getName()))
      {
        String[] strArr = ((String)this.map.get(arg0.getName())).split(",");
        return strArr;
      }
      return new String[] { result };
    }
    return null;
  }
  
  private void initMap(String dbFile)
  {
    this.map = new HashMap();
    DataQueryUtils utils = DataQueryUtils.getInstance();
    String sql = "SELECT DEVICENAME,GROUP_CONCAT(TELID) TELID FROM ZTE_VOLTE_CSCF_TELANA GROUP BY DEVICENAME";
    List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
    for (Map<String, ?> temp : resultList)
    {
      String deviceName = temp.get("DEVICENAME").toString();
      String identifiers = temp.get("TELID").toString();
      
      this.map.put(deviceName, identifiers);
    }
  }
}