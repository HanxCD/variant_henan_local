package com.boco.soap.variant.henan.local.scp.msglocation.hw;

/**
 * 2017-03-15
 *
 * @author wanghao
 * @Email wanghao2009.01@gmail.com
 *该变量通过HW_SCPAS_SSPCONF表中的MSCID号码，查询出对应的SCPASNUM号码，然后用该号码去独立放音设备
 *号码表中查询SCP号码，并且只查询独立IP放音省份的数据
 */

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class sspConf_nodeId
  extends VariantValueInvoke
{
  private Map<String, String> map = null;
  private Map<String, String> map1 = null;
  
  public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile)
  {
    Object mscId = data.get("MSCID");
    String provinceStr = String.valueOf(data.get("PROVINCE"));
    String nodeId = "";
    if (this.map == null)
    {
      this.map = new HashMap();
      initMap(dbFile);
    }
    if (this.map1 == null)
    {
      this.map1 = new HashMap();
      initMap1(dbFile);
    }
    if (mscId == null) {
      return new String[] { "[NULL]" };
    }
    if (!this.map1.get("PROVINCE").contains(provinceStr)) {
      return null;
    }
    nodeId = (String)this.map.get(mscId.toString().trim());
    if (nodeId == null) {
      return new String[] { "HW_SCPAS_SSPCONF表中没有该MSCID:" + mscId.toString() };
    }
    return new String[] { nodeId };
  }
  
  private void initMap(String dbFile)
  {
    DataQueryUtils utils = DataQueryUtils.getInstance();
    String sql = "select SCPASNUM,GTADDRESS from HW_SCPAS_SSPCONF; ";
    List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
    for (Map<String, ?> temp : resultList) {
      this.map.put(temp.get("GTADDRESS").toString().trim(), temp.get("SCPASNUM").toString().trim());
    }
  }
  
  private void initMap1(String dbFile) {
      DataQueryUtils utils = DataQueryUtils.getInstance();
      String sql = "select GROUP_CONCAT(PROVINCE) PROVINCE from TCM_IP_PROVINCE";
      List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
      for (Map<String, ?> temp : resultList) {
          this.map1.put("PROVINCE", temp.get("PROVINCE").toString().trim());
      }
  }
}

