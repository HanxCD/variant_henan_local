package com.boco.soap.variant.henan.local.shortnumber.gmsc.hw;

import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * 短号码 gmsc 根据区号获取DCN
 * 
 * @author changquanzhou
 * 
 */
public class HW_GMSS_DNC_DCN extends VariantValueInvoke {
	@Override
	public String[] getValues(INeElement ne, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {
		String neName = ne.getName();//网元名称
		Object o = data.get("AREA_CODE");
		String result = null;
		if (null == o) {
			result = "[NULL]";
		} else {
			result = this.getDcn(neName,"0"+o.toString(),dbFile);
		}
		
		return new String[] { result };

	}
	/**
	 * 
	 * @param neName 网元名称
	 * @param areaCode 区号
	 * @return
	 */
	private String getDcn(String neName,String areaCode,String dbFile)
	{
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "SELECT DCN FROM HW_GMSS_DNC WHERE ND = '"+areaCode+"' AND DEVICENAME = '"+neName+"'";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
		String dcn ="";
		if (resultList != null && resultList.size() > 0) {
			dcn = resultList.get(0).get("DCN").toString().trim();
		}
		return dcn;
	}
}