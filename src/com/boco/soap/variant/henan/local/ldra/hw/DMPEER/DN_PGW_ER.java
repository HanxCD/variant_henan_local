package com.boco.soap.variant.henan.local.ldra.hw.DMPEER;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class DN_PGW_ER extends VariantValueInvoke {

    private static final String REGEX_SERVERIP = "^\\s*(\\S+)-\\d+-\\d+-\\d+-(\\d+)-(\\w+)";

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
            if ((result.indexOf("BER") > -1) || ((data.get("VENDOR_NAME") != null) && data.get("VENDOR_NAME").equals("爱立信"))) {
                Pattern pServerIp = Pattern.compile(REGEX_SERVERIP);
                Matcher matServerIp = pServerIp.matcher(result);
                if (matServerIp.find()) {
                    result = matServerIp.group(3) + "-" + matServerIp.group(1) + "-" + matServerIp.group(2);
                }
            }
        }

        return new String[] { result };

    }
}
