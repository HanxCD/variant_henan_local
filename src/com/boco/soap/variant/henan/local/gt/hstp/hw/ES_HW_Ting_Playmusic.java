package com.boco.soap.variant.henan.local.gt.hstp.hw;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * @author wanghao
 * @date 2018-01-24 
 * @note 物联网号段中省份的指向为北方省份指向北京，南方省份指向广州，上海的86147660~9这
 * 10个号段指向上海
 * 
 */
public class ES_HW_Ting_Playmusic extends VariantValueInvoke {
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {
		String city=data.get("PROVINCE").toString();
		String e164=data.get("E164").toString();
		
		if (String.valueOf(city).equals("河南")||String.valueOf(city).equals("北京")
				||String.valueOf(city).equals("甘肃")||String.valueOf(city).equals("河北")
				||String.valueOf(city).equals("黑龙江")||String.valueOf(city).equals("新疆")
				||String.valueOf(city).equals("吉林")||String.valueOf(city).equals("江苏")
				||String.valueOf(city).equals("内蒙古")||String.valueOf(city).equals("辽宁")
				||String.valueOf(city).equals("山东")||String.valueOf(city).equals("山西")
				||String.valueOf(city).equals("陕西")||String.valueOf(city).equals("天津")) {
			return new String[] { "BJH1&BJH2" };
		} else if(String.valueOf(city).equals("上海")&&String.valueOf(e164).contains("8614766")){
			return new String[] { "SHH1&SHH2" };
		} else {
			return new String[] { "GZH1&GZH2" };
		}
	}
}