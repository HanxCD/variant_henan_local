package com.boco.soap.fileparse.operate;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class HW_IMS_AGCF_TG implements ISpliteOperate {

    @Override
    public List<Map<String, String>> operate(List<Map<String, String>> oldList) {
        for (Map<String, String> map : oldList) {
            if (StringUtils.isNotBlank(map.get("INTERCEPT_INCOMING2")) && StringUtils.isBlank(map.get("INTERCEPT_INCOMING"))) {
                map.put("INTERCEPT_INCOMING", map.get("INTERCEPT_INCOMING2").substring(0, 1));
            }
        }
        return oldList;
    }

}
