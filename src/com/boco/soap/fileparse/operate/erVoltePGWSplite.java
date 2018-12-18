package com.boco.soap.fileparse.operate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class erVoltePGWSplite implements ISpliteOperate {
    @Override
    public List<Map<String, String>> operate(List<Map<String, String>> oldList) {
        List<Map<String, String>> newList = new ArrayList<Map<String, String>>();
        Map<String, String> newMap = new HashMap<String, String>();
        int i = 0;
        for (Map<String, String> map : oldList) {

            if (map.get("FAILUREACTION") != null) {
                newMap.put("FAILUREACTION", map.get("FAILUREACTION".toString()));
            }
            if (map.get("NETLOC") != null) {
                newMap.put("NETLOC", map.get("NETLOC").toString());
            }
            if (map.get("PCCRELEASE") != null) {
                newMap.put("PCCRELEASE", map.get("PCCRELEASE").toString());
            }
            if (map.get("SUBSCRIPTIONID") != null) {
                if (i == 0) {
                    newMap.put("SUBSCRIPTIONID", map.get("SUBSCRIPTIONID").toString());
                    i++;
                    continue;
                } else {
                    newMap.put("SUBSCRIPTIONID2", map.get("SUBSCRIPTIONID").toString());
                }
            }
        }
        newList.add(newMap);
        return newList;
    }
}