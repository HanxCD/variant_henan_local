package com.boco.soap.variant.henan.local.shortnumber.gmsc.hw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * 短号码 gmsc 带区号的呼叫字冠
 *
 * @author changquanzhou
 *
 */
public class HW_GMSS_CNACLD_SN_AREACODE_PFX extends VariantValueInvoke {
    private final Map<String, String> areacodeMap = new HashMap<String, String>();

    @Override
    public String[] getValues(INeElement ne, IInstructionParameter arg1, Map<String, ?> data, String arg3) {
        Object o = data.get("START_GT");
        String result = null;
        if (null == o) {
            result = "[NULL]";
        } else {
            result = o.toString();
            if (!(result.startsWith("037") || result.startsWith("039"))) {
                return null;
            }

        }

        this.init();
        Map<String, String> coverCityMap = ne.getCoverCities();
        List<String> cityList = new ArrayList<String>();
        List<String> areaCodeList = new ArrayList<String>();
        Iterator iter = coverCityMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String cityName = entry.getKey().toString();
            cityList.add(cityName);
            areaCodeList.add(this.areacodeMap.get(cityName));

        }

        Object oCallType = data.get("CALL_TYPE");// 如果拨号方式为 直拨+接入地区号
        if (oCallType != null) {
            String callType = oCallType.toString();
            Object accessArea = data.get("ACCESS_AREA");
            Object area = data.get("BUSI_CITY");
            if (callType.contains("接入地区号")) {
                if ((accessArea != null) && (area != null)) {
                    if (!accessArea.toString().equals(area.toString())) {
                        return null;
                    }
                }
            }
        }
        return new String[] { result };

    }

    private void init() {
        this.areacodeMap.put("商丘", "370");
        this.areacodeMap.put("郑州", "371");
        this.areacodeMap.put("安阳", "372");
        this.areacodeMap.put("新乡", "373");
        this.areacodeMap.put("许昌", "374");
        this.areacodeMap.put("平顶山", "375");
        this.areacodeMap.put("潢川", "376");
        this.areacodeMap.put("信阳", "376");
        this.areacodeMap.put("南阳", "377");
        this.areacodeMap.put("洛阳", "379");
        this.areacodeMap.put("焦作", "391");
        this.areacodeMap.put("鹤壁", "392");
        this.areacodeMap.put("濮阳", "393");
        this.areacodeMap.put("周口", "394");
        this.areacodeMap.put("漯河", "395");
        this.areacodeMap.put("驻马店", "396");
        this.areacodeMap.put("三门峡", "398");
        this.areacodeMap.put("济源", "391");
        this.areacodeMap.put("开封", "371");

    }
}