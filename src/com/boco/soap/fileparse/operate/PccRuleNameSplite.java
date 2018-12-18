package com.boco.soap.fileparse.operate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PccRuleNameSplite implements ISpliteOperate {
    private final Logger log = LoggerFactory.getLogger(PccRuleNameSplite.class);
    private String pccRuleName="";

    @Override
    public List<Map<String, String>> operate(List<Map<String, String>> oldList) {
        List<Map<String, String>> newList = new ArrayList<Map<String, String>>();
        for (Map<String, String> map : oldList) {
            if ( map.get("PCC_RULE_NAME")!=null&&(!map.get("PCC_RULE_NAME").trim().equals(""))){
                pccRuleName=map.get("PCC_RULE_NAME").trim();
            }else {
                map.remove("PCC_RULE_NAME");
                map.put("PCC_RULE_NAME", pccRuleName);
            }
            String precedence = map.get("PRECEDENCE");
            if ((precedence == null) || precedence.equals("")) {
                map.remove("PRECEDENCE");
                map.put("PRECEDENCE", "-1");
            }
            String l7Uri = map.get("L7_URI");

            if ((l7Uri != null) && (!l7Uri.equals(""))) {
                map.remove("L7_URI");
                if (l7Uri.startsWith("^")) {
                    map.put("L7_URI", l7Uri);
                } else {
                    if ((l7Uri.length() > 2) && l7Uri.startsWith("/")) {
                        map.put("L7_URI", "^" + l7Uri);
                    } else {
                        map.put("L7_URI", l7Uri);
                    }
                }
            }
            newList.add(map);
        }
        if (this.log.isDebugEnabled()) {
            this.log.debug(newList.toString());
        }
        List<Map<String, String>> newList2 = new ArrayList<Map<String, String>>();
        for (Map<String, String> map : newList) {
            String num = map.get("PORT_LIST");
            if ((num != null) && (!num.equals(""))) {
                String[] arrSpc = num.trim().split("\\s+|,|，|\\.|、");
                Arrays.sort(arrSpc);
                map.remove("PORT_LIST");
                map.put("PORT_LIST", Arrays.toString(arrSpc).replaceAll("\\[|\\]", ""));
            }
            newList2.add(map);
        }
        return newList2;
    }
}
