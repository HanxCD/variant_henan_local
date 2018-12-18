package com.boco.soap.variant.henan.local.ldra.hw.RTOHOST;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class ORIGINHOST extends VariantValueInvoke {

	/**
	 * @author Sha Jianwen
	 * @time 2015-11-24 19:04:34
	 * RTOHOST-ORIGINHOST:源端主机名	
	 * 需要做RTOHOST的主机名的设备：目前只有PGW
	 * 用于路由的源主机标识
	 * 在SPS系统中，该参数统一转换为小写形式存储在配置数据库。
	 * 取值范围：长度不超过127的字符串。
	 * “eeeeeeee”（必须为8个e）为通配符，表示所有的目的主机名，优先级最低
	 * Diameter消息中没有携带目的主机名，系统使用字符串“null”查询目的主机路由配置信息。
	 */
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {

		Object o = data.get("HN");
		String result = null;
		if (null == o) {
			result = "null";
		} else {
			result = o.toString().toLowerCase().trim();
		}

		return new String[] { result };

	}
}
