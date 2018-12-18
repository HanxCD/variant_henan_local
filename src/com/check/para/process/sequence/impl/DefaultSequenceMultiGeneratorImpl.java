package com.check.para.process.sequence.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;
import com.boco.soap.db.service.DBLocalService;
import com.boco.soap.db.service.DBServiceManager;
import com.check.instruction.pojo.solution.IValueSequence;
import com.check.para.process.sequence.ISequenceGenerator;

/**
 * 多区间自增长
 * @author caozengran
 *
 */
public class DefaultSequenceMultiGeneratorImpl implements ISequenceGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultSequenceMultiGeneratorImpl.class);

    @Override
    public IValueSequence createValueSequence(IInstructionParameter instructionParam, INeElement ne, String tableName, String url, List<IInstructionParameter> instructionParams) {
        Map<String, IInstructionParameter> paramsMap = this.getParamsMap(instructionParams);

        String paramsStr = instructionParam.getEnglishValue();

        String[] params = paramsStr.split(",");
        int defaultValue = 0;
        String[] conditionArr = {};
        String conditionStr = "";
        /* LOGGER.error("paramsStr:" + paramsStr);
         LOGGER.error("paramsMap:" + paramsMap.keySet());
         LOGGER.error("getEnglishName:" + paramsMap.get(params[1]).getEnglishName());
         LOGGER.error("getChineseName:" + paramsMap.get(params[1]).getChineseName());*/
        String nameIndexC = paramsMap.get(params[1]).getChineseName();
        if (params.length == 3) {
            conditionArr = params[2].split("\\|");
            //            System.out.println(Arrays.toString(conditionArr));
            for (int i = 0; i < conditionArr.length; i++) {
                String conBegin = conditionArr[i].split("-")[0];
                defaultValue = Integer.valueOf(conBegin);
                String conEnd = conditionArr[i].split("-")[1];
                conditionStr += " or  CAST(" + nameIndexC + " AS INT) BETWEEN " + conBegin + " AND " + conEnd;
            }
            conditionStr = conditionStr.replaceFirst("or", "and");
        }

        String sql = "SELECT CAST(" + nameIndexC + " AS INT) MAXVALUE FROM " + tableName + " WHERE DEVICENAME = '" + ne.getName() + "'" + conditionStr + "  ORDER BY CAST (" + nameIndexC + " AS INT) ASC";

        LOGGER.error("多区间自增长sql:" + sql);
        int step = Integer.parseInt(params[0]);

        DBLocalService service = (DBLocalService) DBServiceManager.getInstance().getDBService("db_local_service");
        List<Map<String, ?>> list = service.getLocalData(sql, url);
        List sequenceList = new ArrayList<String>();
        Map sequences = new HashMap();
        for (Map map : list) {
            String maxStr = map.get("MAXVALUE").toString();
            sequenceList.add(maxStr);
        }
        return new DefaultValueMultiSequence(step, sequenceList, defaultValue);
    }

    private Map<String, IInstructionParameter> getParamsMap(List<IInstructionParameter> instructionParams) {
        Map result = new HashMap();
        for (IInstructionParameter param : instructionParams) {
            result.put(param.getEnglishName(), param);
        }
        return result;
    }
}
