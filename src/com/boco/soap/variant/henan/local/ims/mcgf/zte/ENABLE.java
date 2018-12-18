package com.boco.soap.variant.henan.local.ims.mcgf.zte;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class ENABLE extends VariantValueInvoke {

	/**
	 * @author Sha Jianwen
	 * @time 2016年3月30日 上午10:49:34
	 * VOLTE-IMS-MGCF VOLTE参数核查
	 * 最大通话时长：LONGTHD
	 * SET CHARGE:LONGTHD=0 ;
	 * 
	 * 需做标准数据过滤：
	 * 将标准数据中的主键设置为空引用(null) 即可过滤掉不参与核查。
	 */

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
		String sql = "SELECT t.PARAM_VALUE FROM TCM_ALL_CS_VOLTEPARAM   t WHERE  t.ne_type='MGCF' AND t.VENDOR_NAME='中兴' AND t.ACT_TBNAME='SIPSESSTIM' AND t.param_name='会话刷新开关'";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

		for (Map<String, ?> temp : resultList) {
			paramVue=temp.get("PARAM_VALUE").toString();
		}
	}
}
