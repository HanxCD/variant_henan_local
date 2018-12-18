package com.boco.soap.variant.henan.local.scp.msglocation.hw;

//智能网业务中,涉及到SCP的msglocation\sspinfo;I2000设备中的SSP配置和路由放音配置的节点号，用户提前给分配好
//DATE:2017-10-11  MADE BY : wanghao

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;
import java.util.Map;

public class SSPINDEX
  extends VariantValueInvoke
{
  public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3)
  {
    Object o = data.get("SSPINDEX");
    
    String result = null;
    if (null == o) {
      result = "[NULL]";
    } else {
      result = o.toString().trim();
    }
    return new String[] { result };
  }
}