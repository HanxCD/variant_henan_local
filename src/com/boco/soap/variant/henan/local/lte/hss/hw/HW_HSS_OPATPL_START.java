package com.boco.soap.variant.henan.local.lte.hss.hw;

import java.util.HashMap;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;
/**
 * 
 * @ClassName:  HW_HSS_OPATPL_START   
 * @Description:TODO(獲取OPATPL表里覆蓋地市區號)   
 * @author: mengningning 
 * @date:   2017-5-12 上午11:07:58   
 * @version: V1.0  
 * @Copyright: 2017 www.boco.com.cn Inc. All rights reserved.
 */
public class HW_HSS_OPATPL_START extends VariantValueInvoke {

    private final Map<String, String> areacodeMap = new HashMap<String, String>();

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1,
    		  Map<String, ?> data, String arg3) {

        Object o = data.get("START_GT");
        Object callType=data.get("CALLOTHER_TYPE");
        Object isCall=data.get("IS_CALLOTHER");
        String result = "";

        if (o == null) {
            result = "[NULL]";
        } else {
            if (this.areacodeMap.size() == 0) {
                this.init();
            }
            Object coverCity = arg0.getCorverAddr();//獲取覆蓋地市
            if (coverCity != null) {
                for (String addStr : coverCity.toString().split(",")) {
                	if(null!=callType){
	                    if (this.areacodeMap.containsKey(addStr.toString().trim())) {
	                      if(null!=isCall){
	                    	if(isCall.toString().equals("是")){
	                    		System.out.println("是允许互转-------------"+o.toString());
	                    	 if(callType.toString().equals("PLMN")){
	                    	  		System.out.println("是允许互转+----走的plmn");
	                    		 result += "," + "86" + o.toString();
	                    		 return result.substring(1).split(",");
	                    	}else if(callType.toString().equals("LOCAL")){
	                    		System.out.println("是允许互转+----走的local");
	                    		 result += "," + "86" + this.areacodeMap.get(addStr.toString().trim()) + o.toString();
	                    		 return result.substring(1).split(",");
	                    	}else{
	                    		System.out.println("是允许互转+----走的非local和plmn");
	                    		//return new String[] { "NULL"};
	                    		 return null;
	                    		// result="NULL";
	                    	}
	                      }else{
	                        	 System.out.println("不允许互转-------------");
	                        	 return null;
	                         }
	                      }else{
	                    	  return null;
	                      }
	                    }
                	}else{
                		return null;
                	}
                	
                }
               
            }

        }
        return new String[] { result };
    }

    private void init() {
        this.areacodeMap.put("商丘", "370");
        this.areacodeMap.put("郑州", "371");
        this.areacodeMap.put("安阳", "372");
        this.areacodeMap.put("新乡", "373");
        this.areacodeMap.put("许昌", "374");
        this.areacodeMap.put("平顶山", "375");
        this.areacodeMap.put("潢川", "376");
        this.areacodeMap.put("信阳", "376");
        this.areacodeMap.put("南阳", "377");
        this.areacodeMap.put("洛阳", "379");
        this.areacodeMap.put("焦作", "391");
        this.areacodeMap.put("鹤壁", "392");
        this.areacodeMap.put("濮阳", "393");
        this.areacodeMap.put("周口", "394");
        this.areacodeMap.put("漯河", "395");
        this.areacodeMap.put("驻马店", "396");
        this.areacodeMap.put("三门峡", "398");
        //        this.areacodeMap.put("济源", "391");
        this.areacodeMap.put("开封", "371");

    }

}
