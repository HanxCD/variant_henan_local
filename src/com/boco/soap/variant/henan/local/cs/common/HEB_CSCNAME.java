package com.boco.soap.variant.henan.local.cs.common;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;
import java.util.Map;
/*
 * Copyright 2017 BOCO Inter-Telecom (he'nan).
 * All rights reserved. 
 * project name: variant_henan_local
 * version V1.0
 * -------------------------------------------
 * author: wanghao
 * date: 2017-07-21
 * note:运营商NDC联通电信大号段在关口局的被叫号码预分析表中CLDPREANA,ZZDS5和ZZDS6中，做比其他的关口局多做几条
 * HEB带-P的数据
 */
public class HEB_CSCNAME
  extends VariantValueInvoke
{
  public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile)
  {
    String sDest = "";
    if ((arg0.getName().equals("ZZDS5")) || (arg0.getName().equals("ZZDS6"))) {
      sDest = "HEBDXC-P,HEBDX-P,HEBLT-P,HEBWT-P";
    } 
    return sDest.split(",");
  }
}
