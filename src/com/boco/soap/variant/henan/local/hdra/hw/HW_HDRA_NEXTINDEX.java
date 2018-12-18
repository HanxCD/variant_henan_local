package com.boco.soap.variant.henan.local.hdra.hw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class HW_HDRA_NEXTINDEX extends VariantValueInvoke {

    // HW厂家省际DRA路由设置
    private final Map<String, String> _draMap = new HashMap<String, String>();
    // RTEXIT表中路由结果名称、参考号HASH
    private final Map<String, String> _rtrsIndex = new HashMap<String, String>();

    /*
     * Author:Sha Jianwen Edited time:2015-11-12 11:09:37 省际路由结果名称:
     * 《DRA号段省际路由表》表，关联HW厂家的省份查询 本省路由结果名称:《中国移动LTE用户号段全量汇总表》中号段归属HSS名称即是路由结果名称
     * 下一路由参考号： 路由结果名称 关联 现网RTEXIT表 查询参考号 INeElement arg0:网元信息
     * IInstructionParameter arg1：激活设计中指令信息 参数信息(参数名称、用途、类型、参数值...) Map<String,
     * ?> data:标准表主表 String dbFile：文件数据库地址(执行SQL查询时，需要根据文件数据库地址关联查询)
     */
    @Override
    public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String dbFile) {

        String result = null;
        Object _province = data.get("PROVINCE").toString();
        String _rtrsltname = null;

        if (_province == null) {
            result = "[NULL]";
        } else {
            // 山西号段归属河南
            //目前系统不支持特殊省份。建议在标准数据中，将“山西”的号段省份名称改为“河南”后核查HDRA的IMSI
            if (this._draMap.size() == 0) {
                this.initMap(dbFile, arg0.getName());
            }
            if (_province.toString().contains("河南") || _province.toString().contains("山西")) {
                Object o = data.get("RTRSLTNAME");
                _rtrsltname = o == null ? "[NULL]" : o.toString();
            }
            // 省际IMSI，归属覆盖大区的DRA路由，从《DRA号段省际路由表》表，关联HW厂家的省份查询
            else {
                _rtrsltname = this._draMap.get(_province);
            }
            if (this._rtrsIndex.containsKey(_rtrsltname)) {
                result = this._rtrsIndex.get(_rtrsltname);
            }
        }
        return new String[] { result };
    }

    private void initMap(String dbFile, String _devicename) {
        DataQueryUtils utils = DataQueryUtils.getInstance();
        // 省份-路由结果名称
        String sql = "SELECT A.PROVINCE,  A.RTRSLTNAME  FROM TCM_DRA_IMSI_ROUTESET A WHERE A.VENDOR_NAME='HW'";
        List<Map<String, ?>> resultList = utils.getLocalData(sql, dbFile);

        for (Map<String, ?> temp : resultList) {
            this._draMap.put(temp.get("PROVINCE").toString(), temp.get("RTRSLTNAME").toString());
        }

        // 路由结果名称-参考号
        sql = "SELECT A.RTRSLTNAME,A.REFERINDEX FROM HW_HDRA_RTEXIT A WHERE A.DEVICENAME='" + _devicename + "'";
        List<Map<String, ?>> tmprslist = utils.getLocalData(sql, dbFile);
        for (Map<String, ?> temp : tmprslist) {
            this._rtrsIndex.put(temp.get("RTRSLTNAME").toString(), temp.get("REFERINDEX").toString());
        }
    }
}
