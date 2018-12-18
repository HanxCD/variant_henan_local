package com.boco.soap.variant.henan.local.router.cmnet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class IP_CE_NET extends VariantValueInvoke{
	private Map<String, String> ARmap = null;
	private Map<String, String> CEmap = null;
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {
		// TODO Auto-generated method stub
		String result="";
		Object Vpn=data.get("VPNNAME");
		Object NET=data.get("ADDNETWORK");
		Object Mask=data.get("MASK");
		if(this.ARmap==null){
			ARmap=new HashMap();
			initiMap(dbFile);
		}
		if(this.CEmap==null){
			CEmap=new HashMap();
			initiMap1(dbFile);
		}
		if(null==Vpn){
			return null;
		}else{
			if(ARmap.containsKey(Vpn.toString().trim())&&ARmap.containsValue(NET)){
				return null;
			}
			else if(ARmap.containsKey(Vpn.toString().trim())&& !ARmap.containsValue(NET.toString().trim())){}		
			  if(CEmap.containsKey(Vpn.toString().trim())){
				  return null;
			  }else if(!CEmap.containsKey(Vpn.toString().trim())){
				  result=NET.toString().trim();
				  return new String[] { result };
			  }
		}
		return new String[] { result };
	}
	
	
	private void initiMap(String dbFile) {

		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "SELECT A.VPNNAME,A.NET FROM HW_CMNET_AR_VPN A  ";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

		for (Map<String, ?> temp : resultList) {
			this.ARmap.put(temp.get("VPNNAME").toString().trim(), temp.get("NET").toString().trim());
		}
	}
	private void initiMap1(String dbFile) {

		DataQueryUtils utils = DataQueryUtils.getInstance();
		String sql = "SELECT A.VPNNAME,A.NET FROM HW_CMNET_CE_VPN A  ";
		List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

		for (Map<String, ?> temp : resultList) {
			this.CEmap.put(temp.get("VPNNAME").toString().trim(), temp.get("NET").toString().trim());
		}
	}
	


}
