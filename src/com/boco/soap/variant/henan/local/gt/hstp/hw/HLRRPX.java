/*
 * Copyright 2014 BOCO Inter-Telecom (xi'an).
 * All rights reserved. 
 * project name: variant_sichuan
 * version V1.0
 * -------------------------------------------
 * author: lijixin
 * date: 2014-11-3
 * note: 
 */
package com.boco.soap.variant.henan.local.gt.hstp.hw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * gt LSTP 华为 sccpgt SCCP寻址策略的索引
 * 
 * @author lijixin
 * @Email lijixin2014@boco.com.cn
 * 
 */
public class HLRRPX extends VariantValueInvoke {
	
	private Map<String, String> map = null;
	private Map<String, String> spcMap = null;

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
		String result = "";
		String hlrName = data.get("HLR_NAME").toString();
		if (hlrName.indexOf("BNK") > 0) {
			String hlrSpc = data.get("HLR_DPC").toString();
			Pattern px1 = Pattern.compile("^\\s*(\\w+)[\\s+|,|，|、]*");
			Matcher m1 = px1.matcher(hlrSpc);
			if(m1.find()){
				result=getIdx(arg0, dbFile, m1.group(1));
			}else{
				result=getIdx(arg0, dbFile, hlrSpc.replaceAll(",|\\s+|、|，", ","));
			}
		} else if (hlrName.indexOf("BHW") > 0) {
			Pattern px = Pattern.compile("^\\s*(\\w+)[\\s+|,|，|、]*");
			Matcher m = px.matcher(hlrName);
			if(m.find()){
				hlrName=m.group(1);
			}
			String dpcStr = getSpc(hlrName, dbFile).replaceAll(",|\\s+|、|，", ",");
			result=getIdx(arg0, dbFile, dpcStr);
		} 
		return new String[]{result};
	}

	private String getIdx(INeElement arg0, String dbFile, String dpcStr) {
		if (map == null) {
			map = new HashMap<String, String>();
			this.initMap(dbFile);
		}
		String result="[NULL]";
		for (String  keyStr : map.keySet()) {
			String mainKey = keyStr.split("\\|")[0];
			String subKey = keyStr.split("\\|")[1];
			if (mainKey.equals(arg0.getName())&&(subKey.indexOf(dpcStr) >= 0)) {
				result = map.get(keyStr);
				break;
			}
		}
		return result;
	}

	private void initMap(String dbFile) {
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "SELECT M.DEVICENAME, M.IDX, ( SELECT T.INTERNAL_NW_CODE || ':' || M.DEST0_PRIORITY FROM HW_STP_N7DSP T WHERE T.DEST_SPC_IDX = M.DEST0_IDX AND T.DEVICENAME = M.DEVICENAME ) || ( SELECT ',' || T.INTERNAL_NW_CODE || ':' || M.DEST1_PRIORITY FROM HW_STP_N7DSP T WHERE T.DEST_SPC_IDX = M.DEST1_IDX AND T.DEVICENAME = M.DEVICENAME ) || ( CASE M.DEST2_PRIORITY WHEN '<NULL>' THEN '' ELSE ( SELECT ',' || T.INTERNAL_NW_CODE || ':' || M.DEST2_PRIORITY FROM HW_STP_N7DSP T WHERE T.DEST_SPC_IDX = M.DEST2_IDX AND T.DEVICENAME = M.DEVICENAME ) END ) SPC FROM HW_STP_SCCPADDRPL M WHERE DEVICENAME LIKE '%H%'";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
		for (Map<String, ?> temp : resultList) {
			String deviceName = temp.get("DEVICENAME").toString();
			String spc = temp.get("SPC").toString();
			String idx = temp.get("IDX").toString();
			map.put(deviceName+ "|" + spc, idx);
		}
	}
	/**
	 * @param name
	 * @param spcStr
	 * @param dbFile
	 * @return
	 */
	private String getSpc(String hlrName, String dbFile) {

		if (spcMap == null || spcMap.size() == 0) {
			spcMap = new HashMap<String, String>();
			this.initSPCMap(dbFile);
		}
		String result = "[NULL]";
		for (String keyStr : spcMap.keySet()) {
			String mainKey = keyStr.split("\\|")[0];
			String subKey = keyStr.split("\\|")[1];
			if (mainKey.equals(hlrName)) {
				result = spcMap.get(keyStr);
				break;
			}
			if (subKey.indexOf(hlrName) >= 0) {
				result = spcMap.get(keyStr);
				break;
			}

		}
		return result;
	}

	private void initSPCMap(String dbFile) {
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "select HLRNAME_M,SPC,HLRNAME_BACK from TCM_HLR_MTB_REL ";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
		for (Map<String, ?> temp : resultList) {
			String spc = temp.get("SPC").toString();
			String hlrName = temp.get("HLRNAME_BACK").toString();
			spcMap.put(temp.get("HLRNAME_M").toString() + "|" + hlrName, spc);

		}

	}

}
