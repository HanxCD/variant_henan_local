package com.boco.soap.variant.henan.local.gt.hstp.bell;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * 
 * @ClassName:  ES2   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: caozengran 
 * @date:   2017年5月23日 上午9:07:55   
 * @version: V1.0  
 * @Copyright: 2017 www.boco.com.cn Inc. All rights reserved.
 */
public class ES2 extends VariantValueInvoke {

    private Map<String, String> map = new HashMap<String, String>();
    private Map<String, String> map2 = new HashMap<String, String>();

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {

        String o = data.get("HLR_NAME").toString();
        String result = "";
        if (null == o) {
            result = "[NULL]";
        } else {
            if (map == null || map.size() == 0) {
                this.initMap(dbFile);
            }
            String hlrName = o.toString();
            Pattern p = Pattern.compile("^\\s*(\\w+)[\\s+|,|，|、]");
            Matcher m = p.matcher(hlrName);
            if (m.find()) {
                result = m.group(1);
            } else {
                result = hlrName;
            }
            if (result.contains("BNK")) {
                if (String.valueOf(data.get("BUSI_CITY")).equals("郑州") && result.equals("ZZHSS01FE01BNK")) {
                    String[] spcArr = { "ZZDHSS01BNK-ZZL1", "ZZDHSS01BNK-ZZL3", "ZZDHSS01BNK-ZZL4", "ZZDHSS01BNK-ZZL5" };
                    String spcStr = spcArr[new Random().nextInt(spcArr.length)];
                    return new String[] { spcStr };
                } else if (String.valueOf(data.get("BUSI_CITY")).equals("郑州") && result.equals("ZZHSS02FE01BNK")) {
                    String[] spcArr = { "ZZDHSS01BNK-ZZL6", "ZZDHSS01BNK-ZZL7", "ZZDHSS01BNK-ZZL8", "ZZDHSS01BNK-ZZL9" };
                    String spcStr = spcArr[new Random().nextInt(spcArr.length)];
                    return new String[] { spcStr };
                } else if (String.valueOf(data.get("BUSI_CITY")).equals("开封") && result.equals("ZZHSS03FE01BNK")) {
                    String[] spcArr = { "ZZDHSS03BNK-KAF", "ZZDHSS03BNK-KAF2", "ZZDHSS03BNK-KAF3" };
                    String spcStr = spcArr[new Random().nextInt(spcArr.length)];
                    return new String[] { spcStr };
                } else if ((String.valueOf(data.get("BUSI_CITY")).contains("焦作") || String.valueOf(data.get("BUSI_CITY")).contains("济源")) && result.equals("ZZHSS04FE01BNK")) {
                    String[] spcArr = { "ZZDHSS04BNK-JIZ3", "ZZDHSS04BNK-JIZ4" };
                    String spcStr = spcArr[new Random().nextInt(spcArr.length)];
                    return new String[] { spcStr };
                } else if (String.valueOf(data.get("BUSI_CITY")).equals("周口") && result.equals("ZZHSS05FE01BNK")) {
                    String[] spcArr = { "ZZDHSS05BNK-ZHK2", "ZZDHSS05BNK-ZHK3", "ZZDHSS05BNK-ZHK4" };
                    String spcStr = spcArr[new Random().nextInt(spcArr.length)];
                    return new String[] { spcStr };
                } else if (String.valueOf(data.get("BUSI_CITY")).equals("商丘") && result.equals("ZZHSS06FE01BNK")) {
                    String[] spcArr = { "ZZDHSS06BNK-SHQ", "ZZDHSS06BNK-SHQ2", "ZZDHSS06BNK-SHQ3", "ZZDHSS06BNK-SHQ4" };
                    String spcStr = spcArr[new Random().nextInt(spcArr.length)];
                    return new String[] { spcStr };
                } else {
                    String simpleName="";
                    if (String.valueOf(data.get("BUSI_CITY")).contains("焦作")) {
                        simpleName=map.get("焦作");
                    }else {
                         simpleName=map.get(String.valueOf(data.get("BUSI_CITY")));
                    }
                   
                    result=result.replace("HSS", "DHSS").replaceAll("FE//d+", "")+"-"+simpleName;
              //      return new String[]{result};
                }
            } else {
                if (String.valueOf(data.get("BUSI_CITY")).equals("南阳") && result.equals("ZZHSS112FE01BHW")) {
                    String[] spcArr = { "ZZ112FE01-NAY3" };
                    String spcStr = spcArr[new Random().nextInt(spcArr.length)];
                    return new String[] { spcStr };
                } else if (String.valueOf(data.get("BUSI_CITY")).equals("安阳") && result.equals("ZZHSS103FE01BHW")) {
                    String[] spcArr = { "ZZ103FE01-ANY2" };
                    String spcStr = spcArr[new Random().nextInt(spcArr.length)];
                    return new String[] { spcStr };
                }else {
                    String simpleName="";
                    if (String.valueOf(data.get("BUSI_CITY")).contains("焦作")) {
                        simpleName=map.get("焦作");
                    }else {
                         simpleName=map.get(String.valueOf(data.get("BUSI_CITY")));
                    }
                    result=result.replace("HSS", "").replaceAll("BHW", "")+"-"+simpleName;
                    //return new String[]{result};
                }
            }
            String entry = this.getEs(arg0.getName(), result, dbFile);
            if (StringUtils.isNotEmpty(entry)) {
                String[] esArr = entry.split(",");
                result = esArr[new Random().nextInt(esArr.length)];
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
            map.put(busiCity , simpleName);

        }

    }
    

    private String getEs(String deviceName, String es, String dbFile) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "SELECT GROUP_CONCAT(DISTINCT ES) ES FROM BELL_HSTP_SCCP_ES WHERE DEVICENAME='"+deviceName+"' and ES like '"+es+"%'";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
        if (resultList.size()>0) {
            return resultList.get(0).get("ES").toString();
        }else{
            return "";
        }
    }

}
