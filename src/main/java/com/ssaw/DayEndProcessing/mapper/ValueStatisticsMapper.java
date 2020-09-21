package com.ssaw.DayEndProcessing.mapper;

import com.ssaw.DayEndProcessing.entity.ValueStatistics;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * create by: 佘高鹏
 * description: TODO
 * create time: 2020/9/1 9:53
 * version number 1.0
  * @Param: 净值统计持久层接口
 * @return
 */
@Mapper
public interface ValueStatisticsMapper {
    /**
     * 净值统计新增统计方法
     * @Param: ValueStatisticsMapper
     * @return
     */
    public void insertValueStatistics(ValueStatistics valueStatistics);

    /**
     * 净值统计删除方法
     * @Param: String valueStatisticsDate
     * @return
     */

    public void deleteValueStatistics(String valueStatisticsDate,String fundId);
    /**
     * 净值统计查询方法
     * @Param: ValueStatistics
     * @return valueStatisticsList
     */
    public List<ValueStatistics> selectValueStatistics(String valueStatisticsDate,String fundId);
}
