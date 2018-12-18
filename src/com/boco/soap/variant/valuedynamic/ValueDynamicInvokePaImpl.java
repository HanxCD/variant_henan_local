package com.boco.soap.variant.valuedynamic;

import java.util.List;
import java.util.Map;

import com.check.instruction.pojo.checkdata.IDataItem;
import com.check.instruction.pojo.checkdata.IValueDynamicInvoke;
import com.check.instruction.pojo.checkdata.impl.DataItemImpl;
import com.boco.soap.common.pojo.INeElement;

/**
 * @author ZY
 * */

public class ValueDynamicInvokePaImpl implements IValueDynamicInvoke {
	
//	@Override
//	public List<Map<String, IDataItem>> getDynamicValues(INeElement neObj,Map<String, String> standData,
//			String dbFile,List<Map<String, IDataItem>> parasOldData){
//		System.out.println("方案2016进入：ValueDynamicInvokePaImpl");
//		for(Map<String, IDataItem> mapItem:parasOldData){
//			IDataItem item =mapItem.get("DIG");
//			mapItem.put("DIG_C", item);
//		}
//		return parasOldData;
//	}
//	

	@Override
	public List<Map<String, IDataItem>> getDynamicValues(INeElement neObj,Map<String, String> standData,String dbFile,List<Map<String, IDataItem>> parasOldData){
		
		for(Map<String, IDataItem> mapItem:parasOldData){
			IDataItem item = 	mapItem.get("DIG");//138 ,139
			IDataItem item1 =    mapItem.get("DIG_C");//1,1
			if(item1!=null&&item!=null){
				IDataItem newItem = new	DataItemImpl(item1.getParam(), item.getEnglishValue(), item.getChineseValue());
				mapItem.put("DIG_C", newItem);
				item.setChineseValue("1");
				item.setEnglishValue("1");
				IDataItem newItem1 = new	DataItemImpl(item.getParam(), item.getEnglishValue(), item.getChineseValue());
				mapItem.put("DIG", newItem1);
			}
		}
		return parasOldData;
	}
}
