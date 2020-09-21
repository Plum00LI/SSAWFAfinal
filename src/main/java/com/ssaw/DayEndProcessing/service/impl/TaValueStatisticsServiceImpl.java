package com.ssaw.DayEndProcessing.service.impl;

import com.ssaw.DayEndProcessing.mapper.TaValueStatisticsMapper;
import com.ssaw.DayEndProcessing.service.TaValueStatisticsService;
import com.ssaw.InventoryManagement.entity.TaInventory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * create by: 佘高鹏
 * description: TODO
 * 净值统计TA模块业务层实现类
 * create time: 2020/9/14 20:19
 * version number 1.0
  * @Param: null
 * @return
 */
@Service
@Transactional
public class TaValueStatisticsServiceImpl implements TaValueStatisticsService {
    @Resource
    TaValueStatisticsMapper taValueStatisticsMapper;
    @Override
    public List<TaInventory> selectTaNum(String fundId, String valueStatisticsDate) {
        return taValueStatisticsMapper.selectTaNum(fundId,valueStatisticsDate);
    }
}
