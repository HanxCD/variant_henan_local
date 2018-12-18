package com.boco.soap.variant.henan.local.nsnba.pa;


import java.util.Arrays;
import java.util.Map;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;
/**
 * 
 * @author zy
 * 属性分析
 *
 */
public class SUBANALYSISNAME extends VariantValueInvoke{

	
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {	
		Object o = data.get("SUBANALYSIS_NAME");
		String SUBANALYSIS_NAME = null;		
		if(null == o){
			return new String[]{"[NULL]"};
		} else {			
			SUBANALYSIS_NAME = o.toString();
		}
		Object o1=data.get("SPECIAL_NAME");
		String SPECIAL_NAME = null;
		if(null == o1){
			return new String[]{"[NULL]"};
		} else {
			SPECIAL_NAME = o1.toString();
		}
		String o2=arg0.getCorverAddr();
		String[] arrO = null;
		if(null == o2){
			return new String[]{"[NULL]"};
		} else {
			 arrO=o2.split(",");
		}

		return new String[] { SUBANALYSIS_NAME };
	}
	

}
