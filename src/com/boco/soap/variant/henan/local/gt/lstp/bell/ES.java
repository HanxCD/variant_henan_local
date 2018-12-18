package com.boco.soap.variant.henan.local.gt.lstp.bell;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

//DATE:2018-01-02
//MOD:在曹然修改过的连续号段缩位的基础上，王浩修改了当HLR名称为郑州时，取值ZZLX中应该没有L那个值；
//商丘的取值现在为：	ZZDHSS06-SHQ2和ZZDHSS06BNK-SHQ3和ZZDHSS06BNK-SHQ4这三个值中随机取
/**
 *
 * @ClassName:  ES
 * @Description:需求变更，取消es取值时的负荷分担，直接取最後一個
 * @author: caozengran
 * @date:   2018年3月22日 下午3:37:45
 * @version: V1.0
 * @Copyright: 2018 www.boco.com.cn Inc. All rights reserved.
 */

public class ES extends VariantValueInvoke {
    private static final Logger LOGGER = LoggerFactory.getLogger(ES.class);
    private final Map<String, String> map = new HashMap();
    private final Map<String, String> map2 = new HashMap();
    private final Map<String, Map<String, String>> refrenceMap = new HashMap();

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {
        String o = data.get("HLR_NAME").toString();
        String e164No = data.get("E164").toString();
        String result = "";
        if (o == null) {
            result = "[NULL]";
        } else {
            if ((this.map == null) || (this.map.size() == 0)) {
                this.initMap(dbFile);
                this.getSequenceMap(dbFile);
            }
            String hlrName = o.toString();
            Pattern p = Pattern.compile("^\\s*(\\w+)[\\s+|,|，|、]");
            Matcher m = p.matcher(hlrName);
            if (m.find()) {
                result = m.group(1);
            } else {
                result = hlrName;
            }
            result = this.getES(o.toString(), data.get("BUSI_CITY").toString(), arg0.getName(), dbFile);

            Map<String, String> hlrMap = this.refrenceMap.get(data.get("BUSI_CITY").toString());
            if ((hlrMap != null) && (hlrMap.containsKey(e164No))) {
                if (StringUtils.isNotBlank(hlrMap.get(e164No))) {
                    LOGGER.info("连续号段且不为第一个，{}", e164No);
                    result = hlrMap.get(e164No);
                } else {
                    String parentNo = e164No.substring(0, e164No.length() - 1);
                    LOGGER.info("连续号段且为第一个，{},父号码为：{},存入信令{}", new Object[] { e164No, parentNo, result });
                    for (int i = 0; i < 10; i++) {
                        hlrMap.put(parentNo + i, result);
                        this.refrenceMap.put(data.get("BUSI_CITY").toString(), hlrMap);
                    }
                }
            }
        }
        return new String[] { result };
    }

    private void initMap(String dbFile) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "select BUSI_CITY,SIMPLENAME from TCM_LOCAL_CITY_LIST ";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
        for (Map<String, ?> temp : resultList) {
            String busiCity = temp.get("BUSI_CITY").toString();
            String simpleName = temp.get("SIMPLENAME").toString();
            this.map.put(busiCity, simpleName);
        }
    }

    private void getSequenceMap(String dbFile) {
        LOGGER.info("初始化连续号段Map...");
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "SELECT E164,BUSI_CITY FROM TCM_LOCAL_GTT_LIST ORDER BY BUSI_CITY,E164 ASC ";
        List<Map<String, ?>> searchList = utils.getLocalData(sql, dbFile);
        int count = 0;
        for (int i = 0; i < searchList.size(); i++) {
            Map<String, ?> curMap = searchList.get(i);
            String curValue = curMap.get("E164").toString();
            if (curValue.endsWith("0")) {
                count = 0;
                LOGGER.info("发现第一个为0的号段为：{" + curValue + "}");
                count++;
            } else if (count != 0) {
                if (((Long.parseLong(curValue) - Long.parseLong(((Map) searchList.get(i - 1)).get("E164").toString())) == 1L) && (curMap.get("BUSI_CITY").equals(((Map) searchList.get(i - 1)).get("BUSI_CITY").toString()))) {
                    count++;
                    LOGGER.info("发现连续号段为：{}", curValue);
                } else {
                    count = 0;
                    LOGGER.info("发现不连续号段为：{}，计数置0", curValue);
                }
                if (count == 10) {
                    LOGGER.info("发现10个连续号段为：{}", curValue);
                    Map<String, String> tempMap = this.refrenceMap.get(curMap.get("BUSI_CITY").toString());
                    if (tempMap == null) {
                        tempMap = new HashMap();
                    }
                    for (int j = 0; j < 10; j++) {
                        tempMap.put(((Map) searchList.get(i - j)).get("E164").toString(), "");
                        this.refrenceMap.put(curMap.get("BUSI_CITY").toString(), tempMap);
                    }
                    count = 0;
                }
            }
        }
    }

    private String getEs(String deviceName, String es, String dbFile) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "SELECT GROUP_CONCAT(DISTINCT ES) ES FROM BELL_LSTP_SCCP_ES WHERE DEVICENAME='" + deviceName + "' and ES like '" + es + "%'";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
        if (resultList.size() > 0) {
            return ((Map) resultList.get(0)).get("ES").toString();
        }
        return "";
    }

    private String getES(String hrlName, String busiCity, String neName, String dbFile) {
        String result = "";
        if (hrlName.contains("BNK")) {
            if ((busiCity.equals("郑州")) && (hrlName.equals("ZZHSS01FE01BNK"))) {
                String[] spcArr = { /* "ZZDHSS01BNK-ZZ1", "ZZDHSS01BNK-ZZ3", "ZZDHSS01BNK-ZZ4", */"ZZDHSS01BNK-ZZ5" };
                String spcStr = spcArr[new java.util.Random().nextInt(spcArr.length)];
                return spcStr;
            }
            if ((busiCity.equals("郑州")) && (hrlName.equals("ZZHSS02FE01BNK"))) {
                String[] spcArr = { /*"ZZDHSS02BNK-ZZ6", "ZZDHSS02BNK-ZZ7", "ZZDHSS02BNK-ZZ8",*/ "ZZDHSS02BNK-ZZ9" };
                String spcStr = spcArr[new java.util.Random().nextInt(spcArr.length)];
                return spcStr;
            }
            if ((busiCity.equals("开封")) && (hrlName.equals("ZZHSS03FE01BNK"))) {
                String[] spcArr = { /*"ZZDHSS03BNK-KAF", "ZZDHSS03BNK-KAF2", */"ZZDHSS03BNK-KAF3" };
                String spcStr = spcArr[new java.util.Random().nextInt(spcArr.length)];
                return spcStr;
            }
            if (((busiCity.contains("焦作")) || (busiCity.contains("济源"))) && (hrlName.equals("ZZHSS04FE01BNK"))) {
                String[] spcArr = { /*"ZZDHSS04BNK-JIZ3", */"ZZDHSS04BNK-JIZ4" };
                String spcStr = spcArr[new java.util.Random().nextInt(spcArr.length)];
                return spcStr;
            }
            if ((busiCity.equals("周口")) && (hrlName.equals("ZZHSS05FE01BNK"))) {
                String[] spcArr = { /*"ZZDHSS05BNK-ZHK2", "ZZDHSS05BNK-ZHK3",*/ "ZZDHSS05BNK-ZHK4" };
                String spcStr = spcArr[new java.util.Random().nextInt(spcArr.length)];
                return spcStr;
            }
            if ((busiCity.equals("商丘")) && (hrlName.equals("ZZHSS06FE01BNK"))) {
                //String[] spcArr = { "ZZDHSS06BNK-SHQ", "ZZDHSS06BNK-SHQ2", "ZZDHSS06BNK-SHQ3", "ZZDHSS06BNK-SHQ4" };
                String[] spcArr = { /*"ZZDHSS06-SHQ2", "ZZDHSS06BNK-SHQ3", */"ZZDHSS06BNK-SHQ4" };
                String spcStr = spcArr[new java.util.Random().nextInt(spcArr.length)];
                return spcStr;
            }
            String simpleName = "";
            if (busiCity.contains("焦作")) {
                simpleName = this.map.get("焦作");
            } else {
                simpleName = this.map.get(busiCity);
            }
            hrlName = hrlName.replace("HSS", "DHSS").replaceAll("FE//d+", "") + "-" + simpleName;
        } else {
            if ((busiCity.equals("南阳")) && (hrlName.equals("ZZHSS112FE01BHW"))) {
                String[] spcArr = { "ZZ112FE01-NAY3" };
                String spcStr = spcArr[new java.util.Random().nextInt(spcArr.length)];
                return spcStr;
            }
            if ((busiCity.equals("安阳")) && (hrlName.equals("ZZHSS103FE01BHW"))) {
                String[] spcArr = { "ZZ103FE01-ANY2" };
                String spcStr = spcArr[new java.util.Random().nextInt(spcArr.length)];
                return spcStr;
            }
            String simpleName = "";
            if (busiCity.contains("焦作")) {
                simpleName = this.map.get("焦作");
            } else {
                simpleName = this.map.get(busiCity);
            }
            hrlName = hrlName.replace("HSS", "").replaceAll("BHW", "") + "-" + simpleName;
        }
        String entry = this.getEs(neName, hrlName, dbFile);
        if (StringUtils.isNotEmpty(entry)) {
            String[] esArr = entry.split(",");
            result = esArr[new java.util.Random().nextInt(esArr.length)];
        }
        return result;
    }
}
