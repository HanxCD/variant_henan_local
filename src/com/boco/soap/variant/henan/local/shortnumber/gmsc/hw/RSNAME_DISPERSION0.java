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
package com.boco.soap.variant.henan.local.shortnumber.gmsc.hw;

import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * 短号码 gmsc 路由选择名称（RSNAME_DISPERSION0）
 * 
 * @author cqz
 * @Email changquanzhou@boco.com.cn
 * @description 在分散接入时，根据网元归属地市，号首0对应的路由
 */
public class RSNAME_DISPERSION0 extends VariantValueInvoke {

	@Override
	public String[] getValues(INeElement ne, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {
		Object oArea = data.get("BUSI_CITY");// 地市
		String area = oArea != null ? oArea.toString() : "";
		Object oProvince = data.get("PROVINCE");// 运营商名称
		String neName = ne != null ? ne.getName() : "";
		String route = "";
		if (oProvince != null) {
			String province = oProvince.toString();
			if (province.equals("电信")) {
				route = "DX";
			} else if (province.equals("铁通")) {
				route = "TT";
			} else if (province.equals("联通")) {
				route = "LT";
			}
		}
		String routeArea = this.getAreaName(dbFile, neName, area);
		if (!routeArea.equals("")) {
			String[] routeAreas = routeArea.split("|");
			if (routeAreas.length > 1) {
				if (routeAreas[1].equals(area)) {
					route = routeAreas[0] + route;
				} else {
					return null;
				}
			}
		}
		return new String[] { route };

	}

	private String getAreaName(String dbFile, String neName, String area) {
		String route = "";
		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "SELECT distinct a.devicename as NE1,a.cover_city as CITY1,a.cover_city_eng as CITY_ENG1,b.devicename as NE2,b.cover_city as CITY2,b.cover_city_eng as CITY_ENG2 FROM TCM_HW_GMSS_MTB_REL a join TCM_HW_GMSS_MTB_REL b on a.group_name = b.group_name and a.devicename <> b.devicename and a.devicename='"
				+ neName + "'";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
		if (resultList != null && resultList.size() > 0) {
			String cityStr = resultList.get(0).get("CITY1").toString().trim();
			String cityeEngStr = resultList.get(0).get("CITY_ENG1").toString()
					.trim();
			String[] citys = cityStr.split(",");
			String[] cityEngs = cityeEngStr.split(",");
			if (citys.length == cityEngs.length && citys.length > 0) {
				for (int i = 0; i < citys.length; i++) {
					if (area.equals(citys[i])) {
						route = cityEngs[i] + "|" + citys[i];
						break;
					}
				}
			}
		}
		return route;
	}
}