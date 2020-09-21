package com.ssaw.DayEndProcessing.service;

import com.ssaw.DayEndProcessing.entity.ValueStatistics;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * create by: 佘高鹏
 * description: TODO
 * create time: 2020/9/1 10:17
 *version number 1.0
 * 净值统计业务层接口
  * @Param: null
 * @return
 */
@Service
@Transactional
public interface ValueStatisticsService {
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
    public List<ValueStatistics> selectValueStatistics(String valueStatisticsDate, String fundId);
}
