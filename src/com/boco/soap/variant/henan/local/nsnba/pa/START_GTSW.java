package com.boco.soap.variant.henan.local.nsnba.pa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class START_GTSW extends VariantValueInvoke {
    private Map<String, String> map = null;
    private Map<String, String> mapC = null;
    String[] arr = null;
    StringBuilder sbP = null;

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {
        Object oo = data.get("SUBANALYSIS_NAME");
        String SUBANALYSIS_NAME = null;
        if (oo == null) {
            return new String[] { "[NULL]" };
        }
        SUBANALYSIS_NAME = oo.toString();

        if ((!SUBANALYSIS_NAME.equals("A2NUMBER")) && (!SUBANALYSIS_NAME.equals("ANUMBER"))) {
            return null;
        }

        Object o = data.get("PREFIX");
        String PREFIX = null;
        if (o == null) {
            PREFIX = "";
        } else {
            PREFIX = o.toString();
        }
        Object o1 = data.get("SPECIAL_NAME");
        String SPECIAL_NAME = null;
        if (o1 == null) {
            return new String[] { "[NULL]" };
        }
        SPECIAL_NAME = o1.toString();

        String o2 = arg0.getCorverAddr();
        String[] arrO = null;
        if (o2 == null) {
            return new String[] { "[NULL]" };
        }
        arrO = o2.split(",");

        Object o4 = data.get("START_GT");
        String START_GT = null;
        if (o4 == null) {
            return new String[] { "[NULL]" };
        }
        START_GT = o4.toString();

        if ((PREFIX.equals("本地区号")) && (this.map == null)) {
            this.map = new HashMap();
            this.getAreaCode(PREFIX, dbFile);
        }
        if ((PREFIX.equals("本省区号")) && (this.arr == null)) {
            this.getAreaCode(PREFIX, dbFile);
        }
        if ((PREFIX.equals("")) && (this.mapC == null)) {
            this.mapC = new HashMap();
        }
        if (PREFIX.equals("本地区号")) {
            if (arrO.length > 1) {
                StringBuilder s = new StringBuilder();
                int j = arrO.length;
                for (int i = 0; i < j; i++) {
                    s.append(this.map.get(arrO[i])).append(START_GT);
                    if (i < (j - 1)) {
                        s.append(",");
                    }
                }
                return s.toString().split(",");
            }
            if (START_GT.contains("区号")) {
                if (arrO.length > 1) {
                    StringBuilder s = new StringBuilder();
                    int j = arrO.length;
                    for (int i = 0; i < j; i++) {
                        s.append("400").append(this.map.get(arrO[i])).append("6");
                        if (i < (j - 1)) {
                            s.append(",");
                        }
                    }
                    return s.toString().split(",");
                }
                return new String[] { "400" + this.map.get(arrO[0]) + "6" };
            }

            return new String[] { this.map.get(arrO[0]) + START_GT };
        }
        if (PREFIX.equals("本省区号")) {
            return this.getArr(START_GT);
        }
        if ((PREFIX.equals("")) && (!START_GT.equals("本地")) && (!START_GT.equals("本省"))) {
            return new String[] { START_GT };
        }
        if ((PREFIX.equals("")) && (START_GT.equals("本地")) && (this.mapC != null)) {
            StringBuilder s = new StringBuilder();
            int j = arrO.length;
            for (int i = 0; i < j; i++) {
                if (this.mapC.get(arrO[i]) == null) {
                    this.getC(arrO[i].toString(), dbFile);
                }
                s.append(this.mapC.get(arrO[i]));
                if (i < (j - 1)) {
                    s.append(",");
                }
            }
            return s.toString().split(",");
        }
        if ((PREFIX.equals("")) && (START_GT.equals("本省"))) {
            return this.getP(dbFile);
        }
        return new String[] { START_GT };
    }

    private void getAreaCode(String PREFIX, String dbFile) {
        if (PREFIX.equals("本省区号")) {
            DataQueryUtils util = DataQueryUtils.getInstance();
            String sql = "SELECT CMCC_AREA FROM AREA_CODE_LIST  WHERE PROVINCE='河南'";
            List resultList = util.getLocalData(sql, dbFile);
            if ((resultList != null) && (resultList.size() > 0)) {
                this.arr = new String[resultList.size()];
                int j = resultList.size();
                for (int i = 0; i < j; i++) {
                    this.arr[i] = ((Map) resultList.get(i)).get("CMCC_AREA").toString();
                }
            }
            return;
        }
        if (PREFIX.equals("本地区号")) {
            DataQueryUtils util = DataQueryUtils.getInstance();
            String sql = "SELECT BUSI_CITY,CMCC_AREA FROM AREA_CODE_LIST WHERE PROVINCE='河南'";
            List resultList = util.getLocalData(sql, dbFile);
            if ((resultList != null) && (resultList.size() > 0)) {
                int j = resultList.size();
                for (int i = 0; i < j; i++) {
                    this.map.put(((Map) resultList.get(i)).get("BUSI_CITY").toString(), ((Map) resultList.get(i)).get("CMCC_AREA").toString());
                }
            }
            return;
        }
    }

    private String[] getArr(String START_GT) {
        StringBuilder sbarr = new StringBuilder();
        for (int i = 0; i < this.arr.length; i++) {
            sbarr.append(this.arr[i] + START_GT);
            if (i < (this.arr.length - 1)) {
                sbarr.append(",");
            }
        }
        return sbarr.toString().split(",");
    }

    private void getC(String sCity, String dbFile) {
        StringBuilder sbC = new StringBuilder();
        DataQueryUtils util = DataQueryUtils.getInstance();
        String sql = "SELECT E164,BUSI_CITY FROM TCM_ALL_GTT_LIST WHERE BUSI_CITY='" + sCity + "'";
        List resultList = util.getLocalData(sql, dbFile);
        if ((resultList != null) && (resultList.size() > 0)) {
            int j = resultList.size();
            for (int i = 0; i < j; i++) {
                sbC.append(((Map) resultList.get(i)).get("E164").toString());
                if (i < (j - 1)) {
                    sbC.append(",");
                }
                if (i == (j - 1)) {
                    this.mapC.put(((Map) resultList.get(i)).get("BUSI_CITY").toString(), sbC.toString());
                }
            }
        }
    }

    private String[] getP(String dbFile) {
        if (this.sbP == null) {
            this.sbP = new StringBuilder();
            DataQueryUtils util = DataQueryUtils.getInstance();
            String sql = "SELECT E164 FROM TCM_ALL_GTT_LIST WHERE PROVINCE='河南'";
            List resultList = util.getLocalData(sql, dbFile);
            int j = resultList.size();
            for (int i = 0; i < j; i++) {
                this.sbP.append(((Map) resultList.get(i)).get("E164").toString());
                if (i < (j - 1)) {
                    this.sbP.append(",");
                }
            }
        }
        return this.sbP.toString().split(",");
    }
}