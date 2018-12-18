package com.boco.soap.variant.henan.local.shortnumber.gmsc.hw;/**
 /**
 * 该变量处理短号码外省集中接入，移动铁通+方式一二三+拨打全国任意区号的场景，
 * 其中在关口局的覆盖平顶山的地市中，CLDPREANN表中做3条CSCNAME分别为：ZZVOLTE-PDS、
 * ZZA-PDS、PDSMSC的，PFX为0375+短号码裸号的数据，以95066为例
 *
 * @author wanghao
 * @Email wanghao2009.01@gmai.com.cn
 * @Date 2017/03/29
 *
 */

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;
import java.util.Map;

public class CLDPREANA_PFXADD0375
        extends VariantValueInvoke {
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3)
    {
        Object o = data.get("START_GT");
        String result = null;
        if (o == null) {
            result = "[NULL]";
        } else {
            result = "0375" + o.toString().trim();
        }
        return new String[] { result };
    }
}