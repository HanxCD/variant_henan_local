package com.boco.soap.fileparse.operate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.MatchResult;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternMatcherInput;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;

public class JuniperCmnetRtSplite implements ISpliteOperate {
    private static final String REGEX_IP = "(\\d+\\.\\d+\\.\\d+\\.\\d+/\\d+);";

    @Override
    public List<Map<String, String>> operate(List<Map<String, String>> oldList) {
        List<Map<String, String>> newList = new ArrayList<Map<String, String>>();
        Perl5Matcher matcher = new Perl5Matcher();
        for (Map<String, String> map : oldList) {
            if (StringUtils.isNotBlank(map.get("RTNAME"))) {
                Pattern pattern;
                try {
                    pattern = new Perl5Compiler().compile(REGEX_IP);
                    PatternMatcherInput matcherInput = new PatternMatcherInput(map.get("IP"));

                    while (matcher.contains(matcherInput, pattern)) {
                        MatchResult matchResult = matcher.getMatch();
                        Map<String, String> map2 = new HashMap<String, String>();
                        map2.put("RTNAME", map.get("RTNAME").trim());
                        map2.put("IP", matchResult.group(1));
                        newList.add(map2);
                    }
                } catch (MalformedPatternException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return newList;
    }
}