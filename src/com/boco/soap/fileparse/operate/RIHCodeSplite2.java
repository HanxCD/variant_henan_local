package com.boco.soap.fileparse.operate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RIHCodeSplite2 implements ISpliteOperate {
    Logger log = LoggerFactory.getLogger(RIHCodeSplite2.class);

    @Override
    public List<Map<String, String>> operate(List<Map<String, String>> oldList) {
        this.log.info("oldList" + oldList.toString());

        List<Map<String, String>> newList = new ArrayList();
        List<Map<String, String>> resultList = new ArrayList();
        for (Map map : oldList) {
            String code = (String) map.get("DIGITS");

            String[] newCodes = this.splite(code);
            if (newCodes.length != 1) {
                List newCodeList = this.copy(map, newCodes, "DIGITS");
                this.log.info(newCodeList.toString());
                newList.addAll(newCodeList);
            } else {
                newList.add(map);
            }
        }

        for (Map map : newList) {
            String dig = (String) map.get("DIGITS");
            String tree = (String) map.get("TREE");
            if (dig.length() > 3) {
                resultList.add(map);
            }
        }

        return resultList;
    }

    private List<Map<String, String>> copy(Map<String, String> map, String[] newCodes, String name) {
        List result = new ArrayList();
        for (String newCode : newCodes) {
            Map newMap = new HashMap();
            Iterator keyIt = map.keySet().iterator();
            while (keyIt.hasNext()) {
                String key = (String) keyIt.next();
                if (key.endsWith(name)) {
                    newMap.put(key, newCode);
                } else {
                    newMap.put(key, map.get(key));
                }
            }

            result.add(newMap);
        }

        return result;
    }

    public String[] splite(String code) {
        String[] result = null;
        if (code.endsWith("%")) {
            result = new String[10];
            for (int i = 0; i < 10; i++) {
                result[i] = (code.substring(0, code.length() - 1) + i);
            }
        } else if ((code.contains("&&")) && (!code.contains("&&-"))) {
            String[] codes = code.split("&&");

            long min = Long.parseLong(codes[0]);
            long max = Long.parseLong(codes[1]);
            long length = (max - min) + 1L;

            result = new String[Integer.parseInt(String.valueOf(length))];
            int index = 0;
            while (min <= max) {
                result[index] = StringUtils.leftPad("" + min++, codes[0].length(), '0');
                index++;
            }
        } else if ((code.contains("&&-")) || (code.contains("&-"))) {
            this.log.info("before split code:{}", code);
            int index = code.indexOf("&");
            String commonStr = code.substring(0, index - 1);
            String the_units = code.substring(index - 1, code.length());

            String[] the_units_array = the_units.split("&&-");
            StringBuffer the_units_sb = new StringBuffer();

            for (int i = 0; i < the_units_array.length; i++) {
                if ((i + 1) < the_units_array.length) {
                    int min = Integer.parseInt(the_units_array[i].substring(the_units_array[i].length() - 1, the_units_array[i].length()));

                    int max = Integer.parseInt(the_units_array[(i + 1)].substring(0, 1));
                    the_units_sb.append(the_units_array[i]).append("&-");
                    for (int j = min + 1; j < max; j++) {
                        the_units_sb.append(j).append("&-");
                    }
                } else {
                    the_units_sb.append(the_units_array[i]);
                }
            }
            the_units = the_units_sb.toString();

            the_units_array = the_units.split("&-");

            result = new String[the_units_array.length];

            for (int i = 0; i < result.length; i++) {
                result[i] = (commonStr + the_units_array[i]);
            }
            this.log.info("after split list:{}", result);
        } else {
            result = new String[] { code };
        }

        return result;
    }
    /*
    public static void main(String[] args) {
        RIHCodeSplite rihCodeSplite = new RIHCodeSplite();
        System.out.println(Arrays.toString(rihCodeSplite.splite("0147&&0153")));
    }*/
}