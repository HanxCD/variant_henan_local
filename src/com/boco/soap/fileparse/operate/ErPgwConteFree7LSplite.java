package com.boco.soap.fileparse.operate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErPgwConteFree7LSplite implements ISpliteOperate {
    private final Logger log = LoggerFactory.getLogger(ErPgwConteFree7LSplite.class);
    private static final String REGEX_NAME = "name=\"(\\S+)\"";
    private static final String REGEX_PAYLOAD = "payload=(\\d+)";
    private static final String REGEX_STARTSWITH = "startsWith=\"(\\S+)\"";
    private static final String REGEX_CONTAINS = "contains=\"(\\S+)\"";
    private static final String REGEX_IS_STRING = "is=\"(\\S+)\"";
    private static final String REGEX_ENDSWITH = "endsWith=\"(\\S+)\"";
    private static final String REGEX_EDITCONTENT = "editContent=\"(\\S+)\"";

    @Override
    public List<Map<String, String>> operate(List<Map<String, String>> oldList) {
        if (this.log.isDebugEnabled()) {
            this.log.debug("正则匹配到的数据：" + oldList.toString());
        }
        List newList = new ArrayList();
        for (Map map : oldList) {
            String termContent = (String) map.get("TERM_CONTENT");
            if ((termContent != null) && (!termContent.equals(""))) {
                Map<String, Map<String, String>> temp = this.GetContentFreeList(map);
                for (Map.Entry entry : temp.entrySet()) {
                    Map newMap = (Map) entry.getValue();
                    newList.add(newMap);
                }
            }

        }

        return newList;
    }

    private Map<String, Map<String, String>> GetContentFreeList(Map<String, String> map) {
        Map result = new HashMap();
        String type = "";
        String busiCode = "";
        String rlueName = map.get("RULE_NAME");
        String rlueType = map.get("RULE");
        String term = map.get("TERM");
        String name = "";
        String editContent = "";
        List<String> endsWithUrlList = new ArrayList();
        List<String> isUrlList = new ArrayList();
        List<String> startsWithUrlList = new ArrayList();
        List<String> contaisUrlList = new ArrayList();
        List<String> endsWithHostList = new ArrayList();
        List<String> isHostList = new ArrayList();
        List<String> startsWithHostList = new ArrayList();
        List<String> contaisHostList = new ArrayList();
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
        Pattern pEditContent = Pattern.compile(REGEX_EDITCONTENT);
        Matcher matEditContent = pEditContent.matcher(termContent);
        while (matEditContent.find()) {
            String val = matEditContent.group(1);
            editContent = val;
        }
        Pattern pName = Pattern.compile(REGEX_NAME);
        Matcher matName = pName.matcher(termContent);
        while (matName.find()) {
            name = matName.group(1);
        }
        if (map.get("RULE").equals("HttpWspRule")) {
            if ((name.contains("x-online")) || (name.contains("X-Online-Host"))) {
                type = "七层host";
            } else {
                type = "七层Url";
            }
        } else {
            type = "七层Url";
        }
        Pattern pUrl = Pattern.compile(REGEX_STARTSWITH);
        Matcher matUrl = pUrl.matcher(termContent);
        while (matUrl.find()) {
            String val = matUrl.group(1);
            if (type.equals("七层host")) {
                startsWithHostList.add(val);
            } else if (type.equals("七层Url")) {
                startsWithUrlList.add(val);
            }
        }

        Pattern pUrlC = Pattern.compile(REGEX_CONTAINS);
        Matcher matUrlC = pUrlC.matcher(termContent);
        while (matUrlC.find()) {
            String val = matUrlC.group(1);
            if (type.equals("七层host")) {
                contaisHostList.add(val);
            } else if (type.equals("七层Url")) {
                contaisUrlList.add(val);
            }
        }
        Pattern pUrlIs = Pattern.compile(REGEX_IS_STRING);
        Matcher matUrlIs = pUrlIs.matcher(termContent);
        while (matUrlIs.find()) {
            String val = matUrlIs.group(1);
            if (type.equals("七层host")) {
                isHostList.add(val);
            } else if (type.equals("七层Url")) {
                isUrlList.add(val);
            }
        }
        Pattern pUrlEndsWith = Pattern.compile(REGEX_ENDSWITH);
        Matcher matUrlEndsWith = pUrlEndsWith.matcher(termContent);
        while (matUrlEndsWith.find()) {
            String val = matUrlEndsWith.group(1);
            if (type.equals("七层host")) {
                endsWithHostList.add(val);
            } else if (type.equals("七层Url")) {
                endsWithUrlList.add(val);
            }

        }

        if (type.equals("七层Url")) {
            for (String url : isUrlList) {
                Map newMap = new HashMap();
                newMap.put("RULE_NAME", rlueName);
                newMap.put("RULE_TYPE", rlueType);
                newMap.put("TERM", term);
                newMap.put("NAME", name);
                newMap.put("URL", url);
                newMap.put("URL_TYPE", "is");
                newMap.put("BUSICODE", busiCode);
                newMap.put("EDITCONTENT", editContent);
                this.mergeUrl(result, newMap);
            }
            for (String url : contaisUrlList) {
                Map newMap = new HashMap();
                newMap.put("RULE_NAME", rlueName);
                newMap.put("RULE_TYPE", rlueType);
                newMap.put("TERM", term);
                newMap.put("NAME", name);
                newMap.put("URL", url);
                newMap.put("URL_TYPE", "contains");
                newMap.put("BUSICODE", busiCode);
                newMap.put("EDITCONTENT", editContent);
                this.mergeUrl(result, newMap);
            }
            for (String url : startsWithUrlList) {
                Map newMap = new HashMap();
                newMap.put("RULE_NAME", rlueName);
                newMap.put("RULE_TYPE", rlueType);
                newMap.put("TERM", term);
                newMap.put("NAME", name);
                newMap.put("URL", url);
                newMap.put("URL_TYPE", "startsWith");
                newMap.put("BUSICODE", busiCode);
                newMap.put("EDITCONTENT", editContent);
                this.mergeUrl(result, newMap);
            }
            for (String url : endsWithUrlList) {
                Map newMap = new HashMap();
                newMap.put("RULE_NAME", rlueName);
                newMap.put("RULE_TYPE", rlueType);
                newMap.put("TERM", term);
                newMap.put("NAME", name);
                newMap.put("URL", url);
                newMap.put("URL_TYPE", "endsWith");
                newMap.put("BUSICODE", busiCode);
                newMap.put("EDITCONTENT", editContent);
                this.mergeUrl(result, newMap);
            }
        }
        if (type.equals("七层host")) {
            for (String url : isHostList) {
                Map newMap = new HashMap();
                newMap.put("RULE_NAME", rlueName);
                newMap.put("RULE_TYPE", rlueType);
                newMap.put("TERM", term);
                newMap.put("NAME", name);
                newMap.put("X_ONLINE_HOST", url);
                newMap.put("URL_TYPE", "is");
                newMap.put("BUSICODE", busiCode);
                newMap.put("EDITCONTENT", editContent);
                this.mergeUrlHost(result, newMap);
            }
            for (String url : contaisHostList) {
                Map newMap = new HashMap();
                newMap.put("RULE_NAME", rlueName);
                newMap.put("RULE_TYPE", rlueType);
                newMap.put("TERM", term);
                newMap.put("NAME", name);
                newMap.put("X_ONLINE_HOST", url);
                newMap.put("URL_TYPE", "contains");
                newMap.put("BUSICODE", busiCode);
                newMap.put("EDITCONTENT", editContent);
                this.mergeUrlHost(result, newMap);
            }
            for (String url : startsWithHostList) {
                Map newMap = new HashMap();
                newMap.put("RULE_NAME", rlueName);
                newMap.put("RULE_TYPE", rlueType);
                newMap.put("TERM", term);
                newMap.put("NAME", name);
                newMap.put("X_ONLINE_HOST", url);
                newMap.put("URL_TYPE", "startsWith");
                newMap.put("BUSICODE", busiCode);
                newMap.put("EDITCONTENT", editContent);
                this.mergeUrlHost(result, newMap);
            }
            for (String url : endsWithHostList) {
                Map newMap = new HashMap();
                newMap.put("RULE_NAME", rlueName);
                newMap.put("RULE_TYPE", rlueType);
                newMap.put("TERM", term);
                newMap.put("NAME", name);
                newMap.put("X_ONLINE_HOST", url);
                newMap.put("URL_TYPE", "endsWith");
                newMap.put("BUSICODE", busiCode);
                newMap.put("EDITCONTENT", editContent);
                this.mergeUrlHost(result, newMap);
            }
        }

        return result;
    }

    private void mergeUrl(Map<String, Map<String, String>> result, Map<String, String> newMap) {
        String key = newMap.get("RULE_NAME") + "★" + newMap.get("BUSICODE") + "★" + newMap.get("TERM");
        String urlTypeNew = newMap.get("URL_TYPE");
        if (result.containsKey(key)) {
            Map map = result.get(key);
            String urlType = (String) map.get("URL_TYPE");
            String url = "";
            if (urlTypeNew.contains("startsWith")) {
                if (newMap.get("URL").equals("http://")) {
                    url = newMap.get("URL") + (String) map.get("URL");
                } else {
                    url = newMap.get("URL") + "*" + (String) map.get("URL");
                }

            } else if (((String) map.get("URL")).equals("http://")) {
                url = (String) map.get("URL") + newMap.get("URL");
            } else {
                url = (String) map.get("URL") + "*" + newMap.get("URL");
            }

            result.remove(key);
            Map _newMap = new HashMap();
            _newMap.put("RULE_NAME", map.get("RULE_NAME"));
            _newMap.put("RULE_TYPE", map.get("RULE_TYPE"));
            _newMap.put("TERM", map.get("TERM"));
            _newMap.put("NAME", map.get("NAME"));
            _newMap.put("URL", url);
            newMap.put("URL_TYPE", "");
            _newMap.put("BUSICODE", map.get("BUSICODE"));
            _newMap.put("EDITCONTENT", map.get("EDITCONTENT"));
            result.put(key, _newMap);
        } else {
            result.put(key, newMap);
        }
    }

    private void mergeUrlHost(Map<String, Map<String, String>> result, Map<String, String> newMap) {
        String key = newMap.get("RULE_NAME") + "★" + newMap.get("BUSICODE") + "★" + newMap.get("TERM");
        String urlTypeNew = newMap.get("URL_TYPE");
        if (result.containsKey(key)) {
            Map map = result.get(key);
            String urlType = (String) map.get("URL_TYPE");
            String url = "";
            if (urlTypeNew.contains("startsWith")) {
                if (urlType.contains("contains")) {
                    url = newMap.get("X_ONLINE_HOST") + "*" + (String) map.get("X_ONLINE_HOST") + "*";
                } else if (urlType.contains("endsWith")) {
                    url = newMap.get("X_ONLINE_HOST") + "*" + (String) map.get("X_ONLINE_HOST");
                }

            } else if (urlTypeNew.contains("contains")) {
                url = (String) map.get("X_ONLINE_HOST") + "*" + newMap.get("X_ONLINE_HOST") + "*";
            } else if (urlTypeNew.contains("endsWith")) {
                url = (String) map.get("X_ONLINE_HOST") + "*" + newMap.get("X_ONLINE_HOST");
            }

            result.remove(key);
            Map _newMap = new HashMap();
            _newMap.put("RULE_NAME", map.get("RULE_NAME"));
            _newMap.put("RULE_TYPE", map.get("RULE_TYPE"));
            _newMap.put("TERM", map.get("TERM"));
            _newMap.put("NAME", map.get("NAME"));
            _newMap.put("X_ONLINE_HOST", url);
            newMap.put("URL_TYPE", "");
            _newMap.put("BUSICODE", map.get("BUSICODE"));
            _newMap.put("EDITCONTENT", map.get("EDITCONTENT"));
            result.put(key, _newMap);
        } else {
            result.put(key, newMap);
        }
    }
}
