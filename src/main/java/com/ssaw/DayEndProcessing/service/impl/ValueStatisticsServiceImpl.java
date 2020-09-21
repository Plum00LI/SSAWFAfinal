package com.ssaw.DayEndProcessing.service.impl;

import com.ssaw.DayEndProcessing.entity.ValueStatistics;
import com.ssaw.DayEndProcessing.mapper.ValueStatisticsMapper;
import com.ssaw.DayEndProcessing.service.ValueStatisticsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * create by: 佘高鹏
 * description: TODO
 * create time: 2020/9/1 10:19
 * version number 1.0
 * 净值统计业务层接口实现类
  * @Param: null
 * @return
 */
@Service
@Transactional
public class ValueStatisticsServiceImpl implements ValueStatisticsService {
    @Resource
    ValueStatisticsMapper valueStatisticsMapper;
    @Override
    public void insertValueStatistics(ValueStatistics valueStatistics) {
        valueStatisticsMapper.insertValueStatistics(valueStatistics);
    }

    @Override
    public void deleteValueStatistics(String valueStatisticsDate,String fundId) {
        valueStatisticsMapper.deleteValueStatistics(valueStatisticsDate,fundId);
    }

    @Override
    public List<ValueStatistics> selectValueStatistics(String valueStatisticsDate,String fundId) {
        return valueStatisticsMapper.selectValueStatistics(valueStatisticsDate,fundId);
    }
}
