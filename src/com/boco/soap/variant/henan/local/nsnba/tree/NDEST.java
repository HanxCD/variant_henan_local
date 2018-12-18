package com.boco.soap.variant.henan.local.nsnba.tree;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;
/**
 * 
 * @author zy
 * tree分析
 *
 */
public class NDEST extends VariantValueInvoke{

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {
		Object o = data.get("NDEST");
		String NDEST = null;		
		if(null == o){
			return new String[]{"[NULL]"};
		} else {
			NDEST = o.toString();
		}
		Object o1=data.get("ELE_NAME");
		String ELE_NAME = null;
		if(null == o1){
			return new String[]{"[NULL]"};
		} else {
			ELE_NAME = o1.toString();
		}
		
		return new String[] { NDEST };
	}
}