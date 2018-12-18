package com.boco.soap.match.constraintmatch.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.match.constraintmatch.IConstraintMatch;

/**
 * 归属地是包含区号
 * @author caozengran
 *
 */
@Service("cityConstraintContainAreaMatcher")
public class CityConstraintContainAreaMatcher implements IConstraintMatch {
    private static final Logger log = LoggerFactory.getLogger(CityConstraintContainAreaMatcher.class);
    private final Map<String, String> areacodeMap = new HashMap<String, String>();

    @Override
    public List<Map<String, String>> match(INeElement ne, List<Map<String, String>> stdData, String url) {
        if (log.isDebugEnabled()) {
            log.debug("网元：" + ne.getName() + "进行限定地市包含区号匹配");
        }
        this.init();

        List newlist = new ArrayList();

        Map citys = ne.getCoverCities();

        Set set = citys.keySet();
        Set<String> areaCodes = this.getcovercityAreaCode(ne);
        for (Map temp : stdData) {
            if (set.size() > 0) {
                if ((set.toString().contains((CharSequence) temp.get("BUSI_CITY"))) || (areaCodes.toString().contains((CharSequence) temp.get("AREA_CODE"))) || ((temp.get("BUSI_CITY") != null) && (((String) temp.get("BUSI_CITY")).contains("全省")))) {
                    newlist.add(temp);
                }

            } else if ((ne.getPhysicalAddr().equalsIgnoreCase((String) temp.get("BUSI_CITY"))) || ((temp.get("BUSI_CITY") != null) && (((String) temp.get("BUSI_CITY")).contains("全省")))) {
                newlist.add(temp);
            }

        }

        return newlist;
    }

    private Set<String> getcovercityAreaCode(INeElement ne) {
        Set<String> zbCity = ne.getCoverCities().keySet();
        Set<String> areaCodes = new HashSet();
        for (String city : zbCity) {
            if (this.areacodeMap.containsKey(zbCity)) {
                areaCodes.add(this.areacodeMap.get(city));
            }
        }
        return areaCodes;
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
        // this.areacodeMap.put("济源", "391");
        this.areacodeMap.put("开封", "371");
    }

}
