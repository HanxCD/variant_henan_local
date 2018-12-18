package com.boco.soap.variant.henan.local.lte.mme.hw.volte;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class CBRCONFLICTMODE extends VariantValueInvoke {
	
	public String paramVue="";
	@Override
	
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {
		
		if (StringUtils.isEmpty(paramVue)) {
			initStr(arg3);
		}

		return new String[] { paramVue };
	}
	
	private void initStr(String dbFile) {
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "SELECT t.PARAM_VALUE FROM TCM_ALL_VOLTEPARAM   t WHERE  t.ne_type='MME' AND t.VENDOR_NAME='华为'  AND t.param_name='CBR冲突处理模式'";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

		for (Map<String, ?> temp : resultList) {
			paramVue=temp.get("PARAM_VALUE").toString();
		}
	}
}
