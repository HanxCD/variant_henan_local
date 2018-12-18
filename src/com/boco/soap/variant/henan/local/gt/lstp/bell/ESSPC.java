package com.boco.soap.variant.henan.local.gt.lstp.bell;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class ESSPC extends VariantValueInvoke {
	private Map<String, String> map = new HashMap<String, String>();

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
		Pattern p = Pattern.compile("^\\s*(\\w+)[\\s+|,|，|、](\\w+)");
		Matcher m = p.matcher(hlrName);
		// HLR名称有多个，为诺西的，直接取信令
		// HLR名称有多个，为诺西的，直接取信令
		if (m.find() || (hlrName.indexOf("BNK") > 0)) {
			result = getSpc(hlrName, dbFile).replaceAll(",|\\s+|，|、", ":1,")
					.concat(":1");
			// result=data.get("HLR_DPC").toString().replaceAll(",|\\s+|、|，",
			// ":1,").concat(":1");
		} else if (hlrName.indexOf("BHW") > 0) {
			result = getSpc(hlrName, dbFile).replaceAll(",|\\s+|，|、", ",")
					.replace(":1", ":2").replace(":0", ":1");
		} else {
			result = data.get("HLR_DPC").toString().replaceAll(",|\\s+|，|、",
					",");
		}

		return new String[] { result };
	}

	/**
	 * @param name
	 * @param spcStr
	 * @param dbFile
	 * @return
	 */
	private String getSpc(String hlrName, String dbFile) {

		if (map == null || map.size() == 0) {
			this.initMap(dbFile);
		}
		String result = "[NULL]";
		for (String keyStr : map.keySet()) {
			String mainKey = keyStr.split("\\|")[0];
			String subKey = keyStr.split("\\|")[1];
			if (mainKey.equals(hlrName)) {
				result = map.get(keyStr);
				break;
			}
			if (subKey.indexOf(hlrName) >= 0) {
				result = map.get(keyStr);
				break;
			}

		}
		return result;
	}

	private void initMap(String dbFile) {
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "select HLRNAME_M,SPC,HLRNAME_BACK from TCM_HLR_MTB_REL ";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
		for (Map<String, ?> temp : resultList) {
			String spc = temp.get("SPC").toString();
			String hlrName = temp.get("HLRNAME_BACK").toString();
			map.put(temp.get("HLRNAME_M").toString() + "|" + hlrName, spc);

		}

	}

}
