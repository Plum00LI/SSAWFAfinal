package com.ssaw.DayEndProcessing.mapper;

import com.ssaw.InventoryManagement.entity.TaInventory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * create by: 佘高鹏
 * description: TODO
 * 净值统计TA模块持久层
 * create time: 2020/9/14 20:10
 * version number 1.0
  * @Param: null
 * @return
 */
@Mapper
public interface TaValueStatisticsMapper {
    /**
     * 查询TA数量方法
     * @param fundId
     * @param valueStatisticsDate
     * @return
     */
    public List<TaInventory> selectTaNum(String fundId, String valueStatisticsDate);
}
