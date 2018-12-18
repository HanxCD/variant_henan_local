package com.boco.soap.fileparse.operate;

/**
 * 吉林的
 * @author guyufei
 */

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HwScpSysNetConfigSpliteHN implements ISpliteOperate {
    private final Logger log = LoggerFactory.getLogger(HwScpSysNetConfigSpliteHN.class);
    private static final String reg_start_gt = "\\s*\\w{18}(\\d+)00000002\\s*";
    private static final String reg_ip_port = "\\s*(\\d+\\.\\d+\\.\\d+\\.\\d+:30200)\\s*";
    private static final String reg_ip = "\\s*(\\d+\\.\\d+\\.\\d+\\.\\d+)\\s*\\d+\\.\\d+\\.\\d+\\.\\d+\\s*";
    //private static final String reg_type9_para4 = "\\s*\\d+\\s*(\\d+)\\s*";
    String start_gt = "";

    @Override
    public List<Map<String, String>> operate(List<Map<String, String>> oldList) {
        if (this.log.isDebugEnabled()) {
            this.log.debug("正则匹配到的数据：" + oldList.toString());
        }

        for (Map<String, String> map : oldList) {
            String address = map.get("ADDRESS").trim();
            String type = map.get("NOTE_TYPE").trim();
            if (type.equals("9")) {
                String address9 = map.get("PARA3").trim();
                String para4 = map.get("PARA4").trim().replaceAll(" +", " ").split(" ")[1];
                //String para4 = map.get("PARA4").trim();
                Pattern pGt = Pattern.compile(reg_start_gt);
                Matcher matGt9 = pGt.matcher(address9);

                //				Pattern pPara4 = Pattern.compile(reg_type9_para4);
                //				Matcher matPara4 = pPara4.matcher(para4);
                while (matGt9.find()) {
                    this.start_gt = matGt9.group(1);
                    String[] temp = new String[10];
                    int i = 0;
                    while ((i < 10) && (this.start_gt.length() == 10)) {
                        temp[i] = this.start_gt.substring(i, i + 1);
                        i++;
                    }

                    this.start_gt = temp[1] + temp[0] + temp[3] + temp[2] + temp[5] + temp[4] + temp[7] + temp[6] + temp[9] + temp[8];
                    if (address.equals("")) {
                        //						while (matPara4.find())
                        //						{
                        //							para4 = matPara4.group(1);
                        map.put("ADDRESS", this.start_gt);
                        map.put("PARA3", "");
                        map.put("PARA4", para4);
                        //}
                    }
                }
            } else if (type.equals("4") || type.equals("5")) {
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

                    this.start_gt = temp[1] + temp[0] + temp[3] + temp[2] + temp[5] + temp[4] + temp[7] + temp[6] + temp[9] + temp[8];
                    map.put("ADDRESS", this.start_gt);
                }
            } else if (type.equals("3")) {
                String para4 = map.get("PARA4").trim().split(":")[0];
                Pattern pIpPort = Pattern.compile(reg_ip_port);
                Matcher matIpPort = pIpPort.matcher(address);
                while (matIpPort.find()) {
                    this.start_gt = matIpPort.group(1);
                    map.put("ADDRESS", this.start_gt);
                    map.put("PARA4", para4);
                }
            } else if (type.equals("6")) {
                String para3 = map.get("PARA3").trim().replaceAll(" +", " ").split(" ")[0] + "-"
                        + map.get("PARA3").trim().replaceAll(" +", " ").split(" ")[1];
                Pattern pIp = Pattern.compile(reg_ip);
                Matcher matIp = pIp.matcher(address);
                while (matIp.find()) {
                    this.start_gt = matIp.group(1);
                    map.put("ADDRESS", this.start_gt);
                    map.put("PARA3", para3);
                }
            }
        }

        return oldList;
    }
}
