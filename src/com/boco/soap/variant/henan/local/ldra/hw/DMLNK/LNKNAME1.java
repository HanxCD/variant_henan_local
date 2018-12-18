package com.boco.soap.variant.henan.local.ldra.hw.DMLNK;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class LNKNAME1 extends VariantValueInvoke {
	/**
	 * @author Sha Jianwen
	 * @time 2015-11-18 10:23:28 
	 * 链路名称 执行命令时，不区分大小写,链路名称不可重复。
	 */
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {

		Object o = data.get("LNKID");
		Object name = data.get("DN");
		String ip=data.get("PIP1").toString().split("\\.")[3];
		String result = "";
		if (null == o) {
			if (null == name)
//				result = "NULL";
				result = null;
			else {
				for(int i=0;i<15;i++){
					result+=","+ name.toString().trim()+"-c"+i+ "-"+ip+"_LNK01";
				}
				if (result.length()>1) {
					return result.substring(1).split(",");
				}
			}
		} else {
			if (null == name){
				result = "NULL" + "_LNK" + o.toString().trim();
			}
			else {
//				Byte indexByte = 1;
//				try {
//					indexByte = Byte.parseByte(o.toString().trim());
//				} catch (Exception ex) {
//					indexByte = 0;
//				}
				for(int i=0;i<15;i++){
					result+=","+ name.toString().trim()+"-c"+i+ "-"+ip+"_LNK01";
				}
				if (result.length()>1) {
	            	//result=result.toString().substring(1);
//					return result.toString().split(",");
					return result.substring(1).split(",");
				}
				// % 占位符 0 代表前面补充0 2代表长度为2 d 代表参数为正数型
				//result = name.toString().trim() + "_LNK" + String.format("%02d", indexByte);
			}
		}
		return new String[] { result };
	}
}
