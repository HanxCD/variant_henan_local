package com.boco.soap.variant.henan.local.gt.mscserver.nokia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * 2017-03-30
 *
 * @author WANGHAO
 * @Email wanghao2009.01@gmail.com.cn
 *该变量查询诺西EMSC中TACLAC数据信息，根据标准表TACLAC信息汇总表中的LAC10取出来，跟ZELO指令查出来的数据进行对比，
 *在ZELO中有该数据，则在ZEIO中不用进行制作；如果ZELO中没有，去往ZEIO中去查询
 */


public class TAC10 extends VariantValueInvoke {
	private Map<String, String> map = null;

    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {

        String lac = String.valueOf(data.get("LAC10"));

            if (this.map == null) {
                this.map = new HashMap<String, String>();
                this.initMap(dbFile,arg0.getName());
            }
            if (this.map.get("LAC").contains(lac)) {
                return null;
            }

        return new String[] { lac };

    }

    private void initMap(String dbFile,String deviceName) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        String sql = "select GROUP_CONCAT(LAC) LAC from NS_MSS_ZELO  where deviceName='"+deviceName+"'"; 
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);
        for (Map<String, ?> temp : resultList) {
            this.map.put("LAC", temp.get("LAC").toString().trim());
        }
    }

}
