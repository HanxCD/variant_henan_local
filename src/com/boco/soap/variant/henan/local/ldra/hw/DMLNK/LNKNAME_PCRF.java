package com.boco.soap.variant.henan.local.ldra.hw.DMLNK;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class LNKNAME_PCRF extends VariantValueInvoke {
    /**
     * @author Sha Jianwen
     * @time 2015-11-18 10:23:28
     * 链路名称 执行命令时，不区分大小写,链路名称不可重复。
     */
    private static final String REGEX_SERVERIP = "^\\s*(\\S+)-\\d+-\\d+-\\d+-(\\d+)-(\\w+)";

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {
        Object o = data.get("HN");
        Object name = data.get("DN");
        Object id = data.get("LNKID");
        String result = null;
        if (id == o) {
            if (null == name) {
                result = "NULL";
            } else {
                result = name.toString().trim() + "_LNK" + "NULL";
            }
        } else {
            if (null == name) {
                result = "NULL" + "_LNK" + id.toString().trim();
            } else {
                Byte indexByte = 1;
                try {
                    indexByte = Byte.parseByte(id.toString().trim());
                } catch (Exception ex) {
                    indexByte = 0;
                }
                result = o.toString();
                Pattern pServerIp = Pattern.compile(REGEX_SERVERIP);
                Matcher matServerIp = pServerIp.matcher(result);
                if (matServerIp.find()) {
                    result = matServerIp.group(3) + "-" + matServerIp.group(1) + "-" + matServerIp.group(2) + "_LNK" + String.format("%02d", indexByte);
                }

            }
        }
        return new String[] { result };
    }
}
