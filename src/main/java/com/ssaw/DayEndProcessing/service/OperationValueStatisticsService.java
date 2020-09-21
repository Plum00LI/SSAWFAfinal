package com.ssaw.DayEndProcessing.service;

import com.ssaw.DayEndProcessing.entity.OperationValueStatistics;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create by: 佘高鹏
 * description: TODO
 * 净值统计现金模块业务层
 * create time: 2020/9/13 19:02
 * version number 1.0
  * @Param: null
 * @return
 */
@Service
public interface OperationValueStatisticsService {
    /**
     * 现金模块查询方法
     * @param dateTime
     * @param fundID
     * @param type
     * @return
     */
    public List<OperationValueStatistics> selectOperationValueStatistics(String dateTime, String fundID, int type);
    /**
     * 现金模块查询方法-TA清算款
     * @param accountID
     * @param type
     * @param typeTwo
     * @return
     */
    public List<OperationValueStatistics> selectOperationTA(String accountID, int type, int typeTwo, String dateTime, String fundID);

    /**
     * 运营模块查询方法-两费
     * @param dateTime
     * @param fundID
     * @param type
     *
     * @return
     */
    public List<OperationValueStatistics> selectOperationCost(String accountID, String dateTime, String fundID, int type);
}

