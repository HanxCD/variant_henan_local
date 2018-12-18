package com.boco.soap.variant.henan.local.gt.mscserver.nokia;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * @ClassName:  MISDN   
 * @Description:TODO(取MSSID数据，算法是：取同一个lac10
 * 里面的mss_vlrID的个数，然后用LAC10/个数取余，若正好除尽，
 * 则取mss_vlr数据 排序之后的第一位，否则，余数是几，就取第几位。)   
 * @author: mengningning 
 * @date:   2017-4-6 下午04:31:46   
 * @version: V1.0  
 * @Copyright: 2017 www.boco.com.cn Inc. All rights reserved.
 */
public class MISDN extends VariantValueInvoke
{
  private Map<String, String> map = null;
  private Map<String, String> mapList = null;

  public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile)
  {
    String lac = data.get("LAC10").toString();
    if (this.map == null) {
      this.map = new HashMap();
      this.mapList = new HashMap();
      initMap(dbFile, arg0.getName());
    }

    int num = Integer.valueOf(lac).intValue() % Integer.valueOf(((String)this.mapList.get(lac)).toString()).intValue();
    String[] mssIds = ((String)this.map.get(lac)).split("\\,");
    Arrays.sort(mssIds);
    return new String[] { mssIds[num] };
  }
 
  private void initMap(String dbFile, String deviceName)
  {
    DataQueryUtils utils = DataQueryUtils.getInstance();
    String sql = "SELECT GROUP_CONCAT(MSS_VLR) MSSID, LAC10, COUNT(1) LACNUM  FROM TCM_TAC_LAC_LIST GROUP BY LAC10";
    List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
    for (Map<String, ?> temp : resultList) {
      this.map.put(temp.get("LAC10").toString().trim(), temp.get("MSSID").toString());
      this.mapList.put(temp.get("LAC10").toString().trim(), temp.get("LACNUM").toString().trim());
    }
  }
}