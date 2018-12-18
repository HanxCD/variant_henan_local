package com.boco.soap.fileparse.operate;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HwUapScpAdrSpliteHN implements ISpliteOperate {
    private final Logger log = LoggerFactory.getLogger(HwUapScpAdrSpliteHN.class);
    private static final String reg_start_gt = "\\s*\\w{6}(\\d{10})";
    String start_gt = "";

    @Override
    public List<Map<String, String>> operate(List<Map<String, String>> oldList) {
        if (this.log.isDebugEnabled()) {
            this.log.debug("正则匹配到的数据：" + oldList.toString());
        }
        for (Map<String, String> map : oldList) {
            String address = map.get("GTCONTENT").trim();
            Pattern pGt = Pattern.compile(reg_start_gt);
            Matcher matGt = pGt.matcher(address);
            while (matGt.find()) {
                this.start_gt = matGt.group(1);
                String[] temp = new String[10];
                int i = 0;
                while (i < 10) {
                    temp[i] = this.start_gt.substring(i, i + 1);
                    i++;
                }
                this.start_gt = (temp[1] + temp[0] + temp[3] + temp[2] + temp[5] + temp[4] + temp[7] + temp[6] + temp[9] + temp[8]);
                map.put("GTCONTENT", this.start_gt);
            }
        }
        return oldList;
    }

}