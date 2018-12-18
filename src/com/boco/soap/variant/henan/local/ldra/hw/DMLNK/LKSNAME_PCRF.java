package com.boco.soap.variant.henan.local.ldra.hw.DMLNK;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class LKSNAME_PCRF extends VariantValueInvoke {

    private static final String REGEX_SERVERIP = "^\\s*(\\S+)-\\d+-\\d+-\\d+-(\\d+)-(\\w+)";

    /**
     * @author Sha Jianwen
     * @time 2015-11-18 10:23:28
     * 链路集名称
     * 执行命令时，不区分大小写。
     */
    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {
        /**
         * DMPEER表:对等端设备名称
         */
        Object o = data.get("HN");
        String result = null;
        if (null == o) {
            result = "NULL";
        } else {
            result = o.toString();
            Pattern pServerIp = Pattern.compile(REGEX_SERVERIP);
            Matcher matServerIp = pServerIp.matcher(result);
            if (matServerIp.find()) {
                result = matServerIp.group(3) + "-" + matServerIp.group(1) + "-" + matServerIp.group(2) + "_LKS";
            }
        }

        return new String[] { result };

    }
}
