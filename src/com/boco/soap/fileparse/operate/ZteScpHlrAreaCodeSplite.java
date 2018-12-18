package com.boco.soap.fileparse.operate;

/**
 * 吉林的
 * @author guyufei
 */

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZteScpHlrAreaCodeSplite implements ISpliteOperate {
	private Logger log = LoggerFactory.getLogger(ZteScpHlrAreaCodeSplite.class);

	@Override
	public List<Map<String, String>> operate(List<Map<String, String>> oldList) {
		if (log.isDebugEnabled()) {
			log.debug("正则匹配到的数据：" + oldList.toString());
		}

		for (Map<String, String> map : oldList) {
			String cocode = map.get("COCODE").trim();
			String hlr = map.get("HLR").trim();
			String start_gt="111";
			if(cocode != null && !cocode.equals(""))
			{	
				if(hlr.length()==1)
				{
					start_gt=cocode+"000"+hlr;
					map.put("START_GT", start_gt);
				}
				else if(hlr.length()==2)
				{
					start_gt=cocode+"00"+hlr;
					map.put("START_GT", start_gt);
				}
				else if(hlr.length()==3)
				{
					start_gt=cocode+"0"+hlr;
					map.put("START_GT", start_gt);
				}
				else if(hlr.length()==4)
				{
					start_gt=cocode+hlr;
					map.put("START_GT", start_gt);
				}				
			}
		}

		return oldList;
	}
}

