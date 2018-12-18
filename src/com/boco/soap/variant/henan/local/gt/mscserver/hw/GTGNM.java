/*
 * Copyright 2014 BOCO Inter-Telecom (xi'an).
 * All rights reserved. 
 * project name: variant_sichuan
 * version V1.0
 * -------------------------------------------
 * author: lijixin
 * date: 2014-10-31
 * note: 
 */
package com.boco.soap.variant.henan.local.gt.mscserver.hw;

import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * gt mscServer�˾� ��Ϊ sccpgt GTȺ����
 * 
 * @author lijixin
 * @Email lijixin2014@boco.com.cn
 * 
 */
public class GTGNM extends VariantValueInvoke {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.boco.soap.check.standvalue.valueinvoke.IValueInvoke#getValues(com
	 * .boco.soap.common.pojo.INeElement,
	 * com.boco.soap.common.pojo.solution.IInstructionParameter, java.util.Map,
	 * java.lang.String)
	 */
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {
		String spcStr = data.get("HLR_SPC_16").toString();

		String gtgnmStr = this.getGTGNM(arg0.getName(), spcStr, dbFile);
		return new String[] { gtgnmStr };

	}

	/**
	 * @param name
	 * @param spcStr
	 * @param dbFile
	 * @return
	 */
	@SuppressWarnings("static-method")
	private String getGTGNM(String neName, String spcStr, String dbFile) {
		DataQueryUtils utils = DataQueryUtils.getInstance();

		String sql = "select GT_GROUP_NAME  from HW_MSS_SCCPGTG   where  devicename='"
				+ neName+ "'";

		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

		if (resultList.size() != 0) {

			return (String) resultList.get(0).get("GT_GROUP_NAME");

		} else {
			return "[NULL]";
		}
	}

}
