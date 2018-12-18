package com.boco.soap.fileparse.operate;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HwScpCpcsELEMENTMESSAGEID implements ISpliteOperate {
    private final Logger log = LoggerFactory.getLogger(HwScpMsgLocationSplite.class);

    @Override
    public List<Map<String, String>> operate(List<Map<String, String>> oldList) {
        if (this.log.isDebugEnabled()) {
            this.log.debug("正则匹配到的数据：" + oldList.toString());
        }

        for (Map<String, String> map : oldList) {
            String begin = map.get("MESSAGEBEGIN").trim();
            String end = map.get("MESSAGEEND").trim();
            String messageId = begin + "," + end;
            map.put("MESSAGE_ID", messageId);

        }

        return oldList;
    }

}