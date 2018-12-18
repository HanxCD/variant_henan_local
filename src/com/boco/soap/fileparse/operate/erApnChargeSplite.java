package com.boco.soap.fileparse.operate;

import java.util.List;
import java.util.Map;

public class erApnChargeSplite implements ISpliteOperate {
    @Override
    public List<Map<String, String>> operate(List<Map<String, String>> oldList) {
        for (Map<String, String> map : oldList) {
            String allowRuleSpace = map.get("ALLOWRULESPACE");
            if (allowRuleSpace != null) {
                allowRuleSpace = allowRuleSpace.replaceAll("\"|“|”|‘|'", "");
                if (allowRuleSpace.equals("rs-volte") || allowRuleSpace.equals("cmnet-rs") || allowRuleSpace.equals("cmwap-rs")) {
                    map.put("CHARGE", "计费");
                }
            } else {
                map.put("CHARGE", "不计费");
            }
        }
        return oldList;
    }
}