package com.boco.soap.variant.henan.local.router.cmnet;

import java.util.HashMap;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class R_RTNAME extends VariantValueInvoke{

	private  Map<String, String> areacodeMap = new HashMap<String, String>();
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String arg3) {
		String result="";
		
		Object rtName=data.get("STRANAME");//获取策略名称
		Object city=data.get("BUSI_CITY");//获取地市
		if (this.areacodeMap.size() == 0) {
            this.init();
        }
		System.out.println("rtName:===="+rtName);
		if(null==rtName){
			result="NULL";
		}else if(rtName.toString().trim().equals("ICP备案")){
			result = "ICP-all-JT";
		}else if(rtName.toString().trim().equals("分公司专线")){
			if (city != null) {
		           for (String addStr : city.toString().split(",")) {
		               if(this.areacodeMap.containsKey(city.toString().trim())) {
		                  result+=","  +"ZX-JT-"+ this.areacodeMap.get(addStr.toString().trim());
		         		 return result.substring(1).split(",");
		                }
		            }
		        }
		}else if(rtName.toString().trim().equals("投诉外部地址")){
			result="henan-guzhang2016";
		}else if(rtName.toString().trim().equals("目的投诉")){
			result="Destination-to-NAT-D3F";
		}
		System.out.println("result:===="+result);
		return  new String[] { result };
	}
	
	

    private void init() {
        this.areacodeMap.put("商丘", "SHQ");
        this.areacodeMap.put("郑州", "ZZ");
        this.areacodeMap.put("安阳", "ANY");
        this.areacodeMap.put("新乡", "XIX");
        this.areacodeMap.put("许昌", "XCH");
        this.areacodeMap.put("平顶山", "PDS");
        this.areacodeMap.put("济源", "JIY");
        this.areacodeMap.put("鹤壁", "HEB");
        this.areacodeMap.put("信阳", "XIY");
        this.areacodeMap.put("南阳", "NAY");
        this.areacodeMap.put("洛阳", "LUY");
        this.areacodeMap.put("焦作", "JIZ");
        this.areacodeMap.put("濮阳", "PUY");
        this.areacodeMap.put("周口", "ZHK");
        this.areacodeMap.put("漯河", "LUH");
        this.areacodeMap.put("驻马店", "ZMD");
        this.areacodeMap.put("三门峡", "SMX");
        this.areacodeMap.put("开封", "KAF");

    }
    
}
