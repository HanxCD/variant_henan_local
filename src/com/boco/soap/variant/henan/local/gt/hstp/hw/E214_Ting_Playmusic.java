package com.boco.soap.variant.henan.local.gt.hstp.hw;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;
import com.boco.soap.variant.common.NumplanConvert;

/**
 * @author wanghao
 * @date 2018-01-23 
 * @note 物联网号段中只核查制作标准表中是否放音为是的号段
 *
 */
public class E214_Ting_Playmusic extends VariantValueInvoke {

    /* (non-Javadoc)
     * @see com.boco.soap.check.standvalue.valueinvoke.IValueInvoke#getValues(com.boco.soap.common.pojo.INeElement, com.boco.soap.common.pojo.solution.IInstructionParameter, java.util.Map, java.lang.String)
     */
    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {
        Object o = data.get("E164");
        Object p = data.get("ISORNOTPALYMUSIC");
        String result = "[NULL]";
        if (!String.valueOf(p).equals("是")){
			return null;
		} else {
        if (null != o) {
            result = NumplanConvert.E164ToE214(o.toString());
        }
		}
        return new String[] { result };
    }

}