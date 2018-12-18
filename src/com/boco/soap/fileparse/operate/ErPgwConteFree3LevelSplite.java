package com.boco.soap.fileparse.operate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErPgwConteFree3LevelSplite implements ISpliteOperate {
    private final Logger log = LoggerFactory.getLogger(ErPgwConteFree3LevelSplite.class);
    private static final String REGEX_NETWORKADDRESS = "(networkAddress|networkPrefix)=\"(\\d+.\\d+.\\d+.\\d+|\\d+.\\d+.\\d+.\\d+/\\d+)\"";
    private static final String REGEX_PAYLOAD = "payload=(\\d+)";
    private static final String REGEX_NETWORKPORT = "networkPort=\"(\\S+)\"";
    private static final String REGEX_THREEFLOW = "name=\"(\\S+)\"";
    private static final String REGEX_HTTPWSPRULESET = "httpWspRuleSet=\"(\\S+)\"";
    private static final String REGEX_PROTOCOLINSPECTION = "ProtocolInspection=(\\S+)";

    @Override
    public List<Map<String, String>> operate(List<Map<String, String>> oldList) {
        if (this.log.isDebugEnabled()) {
            this.log.debug("正则匹配到的数据：" + oldList.toString());
        }
        System.out.println("111111111111111111111111111");
        List newList = new ArrayList();
        for (Map map : oldList) {
            String termContent = (String) map.get("TERM_CONTENT");
            if ((termContent != null) && (!termContent.equals(""))) {
                List temp = this.GetContentFreeList(map);
                newList.addAll(temp);
            }
        }

        return newList;
    }

    private List<Map<String, String>> GetContentFreeList(Map<String, String> map) {
        List result = new ArrayList();
        String type = "";
        String busiCode = "";
        String threeFlow = "";
        String httpWspRuleSet = "";
        String protocolInspection = "";
        String rlueName = map.get("RULE_NAME");
        String term = map.get("TERM");

        List<String> addressList = new ArrayList();
        ArrayList<String> portList = new ArrayList();
        String termContent = map.get("TERM_CONTENT");

        Pattern pPayLoad = Pattern.compile(REGEX_PAYLOAD);
        Matcher matPayLoad = pPayLoad.matcher(termContent);
        while (matPayLoad.find()) {
            String val = matPayLoad.group(1);
            busiCode = val;
        }
        if (busiCode == "") {
            return result;
        }
        Pattern pFlow = Pattern.compile(REGEX_THREEFLOW);
        Matcher mFlow = pFlow.matcher(termContent);
        while (mFlow.find()) {
            threeFlow = mFlow.group(1);
        }
        Pattern pRuleSet = Pattern.compile(REGEX_HTTPWSPRULESET);
        Matcher mRuleSet = pRuleSet.matcher(termContent);
        while (mRuleSet.find()) {
            httpWspRuleSet = mRuleSet.group(1);
        }
        Pattern pProtocolInspection = Pattern.compile(REGEX_PROTOCOLINSPECTION);
        Matcher mProtocolInspection = pProtocolInspection.matcher(termContent);
        while (mProtocolInspection.find()) {
            protocolInspection = mProtocolInspection.group(1);
        }
        Pattern pAddress;
        if (map.get("RULE").equals("HeaderRule")) {
            type = "三四层内容计费";
            pAddress = Pattern.compile(REGEX_NETWORKADDRESS);
            Matcher matAddress = pAddress.matcher(termContent);
            while (matAddress.find()) {
                String val = matAddress.group(2);
                addressList.add(val);
            }

            Pattern pPort = Pattern.compile(REGEX_NETWORKPORT);
            Matcher matPort = pPort.matcher(termContent);
            while (matPort.find()) {
                String val = matPort.group(1);
                portList.add(val);
            }
        }

        if (type.equals("三四层内容计费")) {
            if (addressList.size() == 0) {
                for (String port : portList) {
                    Map newMap = new HashMap();
                    newMap.put("RULE_NAME", rlueName);
                    newMap.put("TERM", term);
                    newMap.put("ADDRESS", "");
                    newMap.put("PORT", port);
                    newMap.put("BUSICODE", busiCode);
                    newMap.put("THREEFLOW", threeFlow);
                    newMap.put("PROTOCOLINSPECTION", protocolInspection);
                    newMap.put("HTTPWSPRULESET", httpWspRuleSet);
                    result.add(newMap);
                }
            } else {
                for (String address : addressList) {
                    if (portList.size() == 0) {
                        HashMap newMap = new HashMap();
                        newMap.put("RULE_NAME", rlueName);
                        newMap.put("TERM", term);
                        newMap.put("ADDRESS", address);
                        newMap.put("BUSICODE", busiCode);
                        newMap.put("THREEFLOW", threeFlow);
                        newMap.put("PROTOCOLINSPECTION", protocolInspection);
                        newMap.put("HTTPWSPRULESET", httpWspRuleSet);
                        result.add(newMap);
                    } else {
                        for (String port : portList) {
                            Map newMap = new HashMap();
                            newMap.put("RULE_NAME", rlueName);
                            newMap.put("TERM", term);
                            newMap.put("ADDRESS", address);
                            newMap.put("PORT", port);
                            newMap.put("BUSICODE", busiCode);
                            newMap.put("THREEFLOW", threeFlow);
                            newMap.put("PROTOCOLINSPECTION", protocolInspection);
                            newMap.put("HTTPWSPRULESET", httpWspRuleSet);
                            result.add(newMap);
                        }
                    }
                }
            }
        }
        return result;
    }
}