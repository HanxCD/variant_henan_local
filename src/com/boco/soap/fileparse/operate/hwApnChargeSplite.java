package com.boco.soap.fileparse.operate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class hwApnChargeSplite implements ISpliteOperate {
    @Override
    public List<Map<String, String>> operate(List<Map<String, String>> oldList) {
        List<Map<String, String>> newList = new ArrayList<Map<String, String>>();
        Map<String, String> newMap = new HashMap<String, String>();
        int i = 0;
        for (Map<String, String> map : oldList) {

            if ((map.get("CHARGE_BINDING") != null) && (map.get("CHARGE_BINDING").length() > 0)) {
                map.put("CHARGE", "计费");
            } else {
                map.put("CHARGE", "不计费");
            }
        }
        return oldList;
    }
}