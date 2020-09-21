package com.ssaw.DayEndProcessing.service;

import com.ssaw.InventoryManagement.entity.TaInventory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create by: 佘高鹏
 * description: TODO
 * 净值统计TA模块业务层
 * create time: 2020/9/14 20:18
 * version number 1.0
  * @Param: null
 * @return
 */
@Service
public interface TaValueStatisticsService {
    /**
     * 查询TA数量方法
     * @param fundId
     * @param valueStatisticsDate
     * @return
     */
    public List<TaInventory> selectTaNum(String fundId, String valueStatisticsDate);
}
