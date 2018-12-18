package com.boco.soap.fileparse.operate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HW_IMS_AGCF_CLRDSN implements ISpliteOperate {

    private final Map<String, String> busiCityMap = new HashMap<String, String>();

    private static final Logger log = LoggerFactory.getLogger(HW_IMS_AGCF_CLRDSN.class);

    @Override
    public List<Map<String, String>> operate(List<Map<String, String>> oldList) {
        Map<String, Map<String, String>> newMapList = new HashMap<String, Map<String, String>>();
        Map<String, String> newMap = null;
        if ((this.busiCityMap == null) || (this.busiCityMap.size() == 0)) {
            this.initMap();
        }
        for (Map<String, String> map : oldList) {
            if (map.get("CALLING_NUMBER") == null) {
                map.put("SCREEN_GROUP", map.get("SCREEN_GROUP_Z"));
                map.put("CALLING_NUMBER", map.get("CALLING_NUMBER_Z"));
                map.put("NUMBER_TYPE", map.get("NUMBER_TYPE_Z"));
                map.put("ADDRESS_PROPERTY", map.get("ADDRESS_PROPERTY_Z"));
                map.put("FUNCTION_CODE", map.get("FUNCTION_CODE_Z"));
                map.put("MIN_LENGTH", map.get("MIN_LENGTH_Z"));
                map.put("MAX_LENGTH", map.get("MAX_LENGTH_Z"));
            }
            String callNum = map.get("CALLING_NUMBER");//主叫号码CALLING_NUMBER
            if (callNum.length() < 10) {
                int max_length = Integer.valueOf(map.get("MAX_LENGTH"));//主叫号码CALLING_NUMBERMAX_LENGTH
                int min_length = Integer.valueOf(map.get("MIN_LENGTH"));//主叫号码CALLING_NUMBER
                if (callNum.startsWith("03")) {
                    callNum = callNum.substring(4);
                    if (map.get("FUNCTION_CODE").toString().equals("主叫号码甄别")) {
                        max_length = max_length - 4;
                        min_length = min_length - 4;
                    }
                } else if (this.busiCityMap.containsKey(map.get("SCREEN_GROUP"))) {
                    callNum = this.busiCityMap.get(map.get("SCREEN_GROUP")) + callNum;
                    if (map.get("FUNCTION_CODE").toString().equals("主叫号码甄别")) {
                        max_length = max_length + 4;
                        min_length = min_length + 4;
                    }
                }
                newMap = new HashMap<String, String>();
                newMap.putAll(map);
                newMap.put("CALLING_NUMBER", callNum);
                newMap.put("MAX_LENGTH", max_length + "");
                newMap.put("MIN_LENGTH", min_length + "");

                newMapList.put(newMap.get("CALLING_NUMBER"), newMap);
            }
            //已经包含当前主叫号码，并且当前号码类型为主叫号码甄别则不添加到当前列表
            newMapList.put(map.get("CALLING_NUMBER"), map);
            /* String callNum = map.get("CALLING_NUMBER");//主叫号码CALLING_NUMBER
            int max_length = Integer.valueOf(map.get("MAX_LENGTH"));//主叫号码CALLING_NUMBERMAX_LENGTH
            int min_length = Integer.valueOf(map.get("MIN_LENGTH"));//主叫号码CALLING_NUMBER
            if (callNum.startsWith("03")) {
                callNum = callNum.substring(4);
                max_length = max_length - 4;
                min_length = min_length - 4;
            }
            if (map.get("FUNCTION_CODE").equals("主叫号码甄别")) {
                Map<String, String> newMap = null;
                int lens = (int) Math.pow(10, max_length - callNum.length());//获取总差距长度
                for (int i = 0; i < lens; i++) {
                    newMap = new HashMap<String, String>();
                    newMap.putAll(map);
                    newMap.put("CALLING_NUMBER", callNum + i);
                    newMap.put("MAX_LENGTH", max_length + "");
                    newMap.put("MIN_LENGTH", min_length + "");
            
                    //已经包含当前主叫号码，并且当前号码类型为主叫号码甄别则不添加到当前列表
                    if (!newMapList.containsKey(newMap.get("CALLING_NUMBER"))) {
                        newMapList.put(newMap.get("CALLING_NUMBER"), newMap);
                    }
                }
            } else {
                newMapList.put(map.get("CALLING_NUMBER"), map);
            }*/
        }
        log.info("现网解析的总条数为：" + newMapList.size());
        return new ArrayList<Map<String, String>>(newMapList.values());
        //return oldList;
    }

    private void initMap() {
        this.busiCityMap.put("13401", "0392");
        this.busiCityMap.put("11114", "0391");
        this.busiCityMap.put("3102", "0371");
        this.busiCityMap.put("3119", "0371");
        this.busiCityMap.put("16100", "0395");
        this.busiCityMap.put("8106", "0376");
        this.busiCityMap.put("8110", "0376");
        this.busiCityMap.put("9600", "0377");
        this.busiCityMap.put("5125", "0373");
        this.busiCityMap.put("5114", "0373");
        this.busiCityMap.put("5157", "0373");
        this.busiCityMap.put("18104", "0398");
        this.busiCityMap.put("18119", "0398");
        this.busiCityMap.put("18113", "0398");
        this.busiCityMap.put("18115", "0398");
        this.busiCityMap.put("5151", "0373");
        this.busiCityMap.put("5115", "0373");
        this.busiCityMap.put("5109", "0373");
        this.busiCityMap.put("5154", "0373");
        this.busiCityMap.put("7100", "0375");
        this.busiCityMap.put("15108", "0394");
        this.busiCityMap.put("1100", "0370");
        this.busiCityMap.put("2221", "0371");
        this.busiCityMap.put("1123", "0370");
        this.busiCityMap.put("2640", "0371");
        this.busiCityMap.put("2222", "0371");
        this.busiCityMap.put("3106", "0371");
        this.busiCityMap.put("9606", "0377");
        this.busiCityMap.put("3101", "0371");
        this.busiCityMap.put("16103", "0395");
        this.busiCityMap.put("8101", "0376");
        this.busiCityMap.put("8102", "0376");
        this.busiCityMap.put("5131", "0373");
        this.busiCityMap.put("18117", "0398");
        this.busiCityMap.put("18109", "0398");
        this.busiCityMap.put("18105", "0398");
        this.busiCityMap.put("18101", "0398");
        this.busiCityMap.put("5124", "0373");
        this.busiCityMap.put("5123", "0373");
        this.busiCityMap.put("7130", "0375");
        this.busiCityMap.put("2136", "0371");
        this.busiCityMap.put("14110", "0393");
        this.busiCityMap.put("14124", "0393");
        this.busiCityMap.put("14128", "0393");
        this.busiCityMap.put("7600", "0375");
        this.busiCityMap.put("7650", "0375");
        this.busiCityMap.put("1115", "0370");
        this.busiCityMap.put("2218", "0371");
        this.busiCityMap.put("2238", "0371");
        this.busiCityMap.put("3107", "0371");
        this.busiCityMap.put("3110", "0371");
        this.busiCityMap.put("10129", "0379");
        this.busiCityMap.put("10130", "0379");
        this.busiCityMap.put("8119", "0376");
        this.busiCityMap.put("8132", "0376");
        this.busiCityMap.put("9610", "0377");
        this.busiCityMap.put("18100", "0398");
        this.busiCityMap.put("12100", "0391");
        this.busiCityMap.put("5134", "0373");
        this.busiCityMap.put("5144", "0373");
        this.busiCityMap.put("5104", "0373");
        this.busiCityMap.put("7170", "0375");
        this.busiCityMap.put("2622", "0371");
        this.busiCityMap.put("2611", "0371");
        this.busiCityMap.put("1129", "0370");
        this.busiCityMap.put("4613", "0372");
        this.busiCityMap.put("15102", "0394");
        this.busiCityMap.put("2677", "0371");
        this.busiCityMap.put("1105", "0370");
        this.busiCityMap.put("13120", "0392");
        this.busiCityMap.put("13400", "0392");
        this.busiCityMap.put("3105", "0371");
        this.busiCityMap.put("10100", "0379");
        this.busiCityMap.put("10108", "0379");
        this.busiCityMap.put("16104", "0395");
        this.busiCityMap.put("16117", "0395");
        this.busiCityMap.put("8121", "0376");
        this.busiCityMap.put("9103", "0377");
        this.busiCityMap.put("9604", "0377");
        this.busiCityMap.put("17100", "0396");
        this.busiCityMap.put("12101", "0391");
        this.busiCityMap.put("18114", "0398");
        this.busiCityMap.put("5139", "0373");
        this.busiCityMap.put("5112", "0373");
        this.busiCityMap.put("2642", "0371");
        this.busiCityMap.put("14106", "0393");
        this.busiCityMap.put("4615", "0372");
        this.busiCityMap.put("2649", "0371");
        this.busiCityMap.put("2639", "0371");
        this.busiCityMap.put("2137", "0371");
        this.busiCityMap.put("2691", "0371");
        this.busiCityMap.put("2610", "0371");
        this.busiCityMap.put("1102", "0370");
        this.busiCityMap.put("1104", "0370");
        this.busiCityMap.put("2150", "0371");
        this.busiCityMap.put("2147", "0371");
        this.busiCityMap.put("2220", "0371");
        this.busiCityMap.put("2214", "0371");
        this.busiCityMap.put("2176", "0371");
        this.busiCityMap.put("11115", "0391");
        this.busiCityMap.put("9609", "0377");
        this.busiCityMap.put("11113", "0391");
        this.busiCityMap.put("3109", "0371");
        this.busiCityMap.put("16114", "0395");
        this.busiCityMap.put("10102", "0379");
        this.busiCityMap.put("8120", "0376");
        this.busiCityMap.put("17106", "0396");
        this.busiCityMap.put("17101", "0396");
        this.busiCityMap.put("5136", "0373");
        this.busiCityMap.put("5113", "0373");
        this.busiCityMap.put("2130", "0371");
        this.busiCityMap.put("2132", "0371");
        this.busiCityMap.put("4108", "0372");
        this.busiCityMap.put("4611", "0372");
        this.busiCityMap.put("2604", "0371");
        this.busiCityMap.put("2606", "0371");
        this.busiCityMap.put("2226", "0371");
        this.busiCityMap.put("13402", "0392");
        this.busiCityMap.put("9620", "0377");
        this.busiCityMap.put("10105", "0379");
        this.busiCityMap.put("10103", "0379");
        this.busiCityMap.put("17102", "0396");
        this.busiCityMap.put("5120", "0373");
        this.busiCityMap.put("18120", "0398");
        this.busiCityMap.put("18121", "0398");
        this.busiCityMap.put("18103", "0398");
        this.busiCityMap.put("18102", "0398");
        this.busiCityMap.put("5153", "0373");
        this.busiCityMap.put("4633", "0372");
        this.busiCityMap.put("2156", "0371");
        this.busiCityMap.put("2177", "0371");
        this.busiCityMap.put("2190", "0371");
        this.busiCityMap.put("1126", "0370");
        this.busiCityMap.put("2705", "0371");
        this.busiCityMap.put("13403", "0392");
        this.busiCityMap.put("3118", "0371");
        this.busiCityMap.put("3120", "0371");
        this.busiCityMap.put("8125", "0376");
        this.busiCityMap.put("16113", "0395");
        this.busiCityMap.put("5100", "0373");
        this.busiCityMap.put("18111", "0398");
        this.busiCityMap.put("5105", "0373");
        this.busiCityMap.put("5150", "0373");
        this.busiCityMap.put("2999", "0371");
        this.busiCityMap.put("2145", "0371");
        this.busiCityMap.put("4623", "0372");
        this.busiCityMap.put("1108", "0370");
        this.busiCityMap.put("2100", "0371");
        this.busiCityMap.put("2634", "0371");
        this.busiCityMap.put("1122", "0370");
        this.busiCityMap.put("1124", "0370");
        this.busiCityMap.put("2202", "0371");
        this.busiCityMap.put("2182", "0371");
        this.busiCityMap.put("2179", "0371");
        this.busiCityMap.put("2191", "0371");
        this.busiCityMap.put("2225", "0371");
        this.busiCityMap.put("2702", "0371");
        this.busiCityMap.put("13105", "0392");
        this.busiCityMap.put("16102", "0395");
        this.busiCityMap.put("5152", "0373");
        this.busiCityMap.put("18108", "0398");
        this.busiCityMap.put("18112", "0398");
        this.busiCityMap.put("5146", "0373");
        this.busiCityMap.put("5160", "0373");
        this.busiCityMap.put("5118", "0373");
        this.busiCityMap.put("2133", "0371");
        this.busiCityMap.put("15110", "0394");
        this.busiCityMap.put("15112", "0394");
        this.busiCityMap.put("2665", "0371");
        this.busiCityMap.put("14112", "0393");
        this.busiCityMap.put("2227", "0371");
        this.busiCityMap.put("2230", "0371");
    }

}
