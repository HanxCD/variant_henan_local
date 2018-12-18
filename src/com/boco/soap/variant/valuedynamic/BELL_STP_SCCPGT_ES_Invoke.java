package com.boco.soap.variant.valuedynamic;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.boco.soap.common.pojo.INeElement;
import com.check.instruction.pojo.checkdata.IDataItem;
import com.check.instruction.pojo.checkdata.IValueDynamicInvoke;

/**
 * @ClassName:  BELL_STP_SCCPGT_ES_Invoke
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: mengninging
 * @date:   2017年12月20日 下午6:18:34
 * @version: V1.0
 * @Copyright: 2017 www.boco.com.cn Inc. All rights reserved.
 */
public class BELL_STP_SCCPGT_ES_Invoke implements IValueDynamicInvoke {
    private static final Logger logger = LoggerFactory.getLogger(BELL_STP_SCCPGT_ES_Invoke.class);

    @Override
    public List<Map<String, IDataItem>> getDynamicValues(INeElement neObj, Map<String, String> standData, String dbFile, List<Map<String, IDataItem>> parasOldData) {
        /*logger.error("标准数据的个数为{},开始标准数据处理。。。。。。", parasOldData.size());
        String es = null;
        Collections.sort(parasOldData, new Comparator<Map<String, IDataItem>>() {
            @Override
            public int compare(Map<String, IDataItem> o1, Map<String, IDataItem> o2) {
                Long num1 = Long.valueOf(o1.get("DN").getEnglishValue() == null ? "1" : o1.get("DN").getEnglishValue());//name1是从你list里面拿出来的一个
                Long num2 = Long.valueOf(o2.get("DN").getEnglishValue() == null ? "1" : o2.get("DN").getEnglishValue()); //name1是从你list里面拿出来的第二个name
                return num1.compareTo(num2);
            }
        });
        logger.error("标准数据排序结束。。。。");
        IDataItem esIDataItem;
        IDataItem dnIDataItem;
        int count = 0;
        for (int i = 0; i < parasOldData.size(); i++) {
            Map<String, IDataItem> mapItem = parasOldData.get(i);
            esIDataItem = mapItem.get("ES");
            dnIDataItem = mapItem.get("DN");//1,1
            if ((esIDataItem != null) && (dnIDataItem != null)) {
                if (count == 0) {
                    if (dnIDataItem.getEnglishValue().endsWith("0")) {
                        System.out.println("发现第一个为0的号段为：{" + dnIDataItem.getEnglishValue() + "}");
                        count++;
                    }
                    continue;
                }
        
                if ((Long.parseLong(dnIDataItem.getEnglishValue()) - Long.parseLong(parasOldData.get(i - 1).get("DN").getEnglishValue())) == 1) {
                    count++;
                    logger.error("发现连续号段为：{}", dnIDataItem.getEnglishValue());
                } else {
                    count = 0;
                    logger.error("发现不连续号段为：{}，计数置0", dnIDataItem.getEnglishValue());
                }
                if (count == 10) {
                    String curValue = esIDataItem.getEnglishValue();
                    logger.error("发现10个连续号段为：{},修改相应的ES置为{}", dnIDataItem.getEnglishValue(), curValue);
                    for (int j = 0; j < 10; j++) {
                        parasOldData.get(i - j).get("ES").setEnglishValue(curValue);
                        parasOldData.get(i - j).get("ES").setChineseValue(curValue);
                    }
                    count = 0;
                }
        
            }
        }*/
        return parasOldData;
    }
    /*
    public static void main(String[] args) {
        List<Map<String, IDataItem>> parasOldData = new ArrayList<Map<String, IDataItem>>();
        Map<String, IDataItem> map;
        IDataItem dataItem;
        Long curValue = 861781469L;
        Random random = new Random();
        for (int i = 0; i < 13; i++) {
            dataItem = new DataItem(String.valueOf(curValue + i));
            map = new HashMap<String, IDataItem>();
            map.put("DN", dataItem);
            dataItem = new DataItem(String.valueOf(curValue + i));
            map.put("ES", dataItem);
            parasOldData.add(map);
        }
        dataItem = new DataItem(String.valueOf(861781423L));
        map = new HashMap<String, IDataItem>();
        map.put("DN", dataItem);
        dataItem = new DataItem(String.valueOf(861781423L));
        map.put("ES", dataItem);
        parasOldData.add(map);
        System.out.println("标准数据的个数为{" + parasOldData.size() + "},开始标准数据处理。。。。。。");
        String es = null;
        Collections.sort(parasOldData, new Comparator<Map<String, IDataItem>>() {
            @Override
            public int compare(Map<String, IDataItem> o1, Map<String, IDataItem> o2) {
                Long num1 = Long.valueOf(o1.get("DN").getEnglishValue() == null ? "1" : o1.get("DN").getEnglishValue());//name1是从你list里面拿出来的一个
                Long num2 = Long.valueOf(o2.get("DN").getEnglishValue() == null ? "1" : o2.get("DN").getEnglishValue()); //name1是从你list里面拿出来的第二个name
                return num1.compareTo(num2);
            }
        });
        System.out.println("标准数据排序结束。。。。");
        IDataItem esIDataItem;
        IDataItem dnIDataItem;
        int count = 0;
        for (int i = 0; i < parasOldData.size(); i++) {
            Map<String, IDataItem> mapItem = parasOldData.get(i);
            esIDataItem = mapItem.get("ES");
            dnIDataItem = mapItem.get("DN");//1,1
            if ((esIDataItem != null) && (dnIDataItem != null)) {
                if (count == 0) {
                    if (dnIDataItem.getEnglishValue().endsWith("0")) {
                        System.out.println("发现第一个为0的号段为：{" + dnIDataItem.getEnglishValue() + "}");
                        count++;
                    }
                    continue;
                }
                if ((Long.parseLong(dnIDataItem.getEnglishValue()) - Long.parseLong(parasOldData.get(i - 1).get("DN").getEnglishValue())) == 1) {
                    count++;
                    System.out.println("发现连续号段为：{" + dnIDataItem.getEnglishValue() + "}");
                } else {
                    count = 0;
                    System.out.println("发现不连续号段为：{" + dnIDataItem.getEnglishValue() + "}，计数置0");
                }
                if (count == 10) {
                    System.out.println("发现10个连续号段为：{" + dnIDataItem.getEnglishValue() + "},修改相应的ES置为{" + esIDataItem.getEnglishValue() + "}");
                    for (int j = 0; j < 10; j++) {
                        parasOldData.get(i - j).get("ES").setEnglishValue(esIDataItem.getEnglishValue());
                    }
                    count = 0;
                }
    
            }
    
        }
    }
    }
    
    class DataItem implements IDataItem {
    
    private String englishValue;
    private String chineseValue;
    
    public DataItem(String englishValue) {
        super();
        this.englishValue = englishValue;
    }
    
    @Override
    public String getEnglishValue() {
        // TODO Auto-generated method stub
        return this.englishValue;
    }
    
    @Override
    public String getChineseValue() {
        // TODO Auto-generated method stub
        return this.chineseValue;
    }
    
    @Override
    public void setEnglishValue(String s) {
        // TODO Auto-generated method stub
        this.englishValue = s;
    }
    
    @Override
    public void setChineseValue(String s) {
        // TODO Auto-generated method stub
        this.chineseValue = s;
    }
    
    @Override
    public IDataParamDefine getParam() {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public void setParam(IDataParamDefine idataparamdefine) {
        // TODO Auto-generated method stub
    
    }
    
    @Override
    public String getParamName() {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public StringBuffer formatStdParaString(IDataItem idataitem, String s) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public StringBuffer formatCurParaString(IDataItem idataitem, String s) {
        // TODO Auto-generated method stub
        return null;
    }*/

}
