package com.ssaw.DayEndProcessing.mapper;

import com.ssaw.DayEndProcessing.entity.OperationValueStatistics;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * create by: 佘高鹏
 * description: TODO
 * 净值统计现金模块持久层
 * create time: 2020/9/13 18:55
 * version number 1.0
  * @Param: null
 * @return
 */
@Mapper

public interface OperationValueStatisticsMapper {
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
