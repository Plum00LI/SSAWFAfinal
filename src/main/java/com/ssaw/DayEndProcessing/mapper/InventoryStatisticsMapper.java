package com.ssaw.DayEndProcessing.mapper;



import com.ssaw.DayEndProcessing.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName:    InventoryStatisticsMapper
 * Package:    com.ssaw.DayEndProcessing.mapper
 * Description:
 * Version:
 * Datetime:    2020/9/8   20:12
 * Author:   SYT
 */
@Mapper
public interface InventoryStatisticsMapper {
    /**
     * 查询证券库存方法
     * @param dateTime
     * @param fundId
     * @return
     */
    public List<SecuritiesInventoryData> selectSecuritiesInventory(String dateTime, String fundId);

    /**
     * 查询Ta库存方法
     * @param dateTime
     * @param fundId
     * @return
     */
    public List<TaInventoryData> selectTaInventory(String dateTime, String fundId);

    /**
     * 查询现金库存方法
     * @param dateTime
     * @param fundId
     * @return
     */
    public List<CashInventoryData> selectCashInventory(String dateTime, String fundId);

    /**
     * 查询证券应收应付库存
     * @param dateTime
     * @param fundId
     * @return
     */
    public List<SecuritiesClosedPayInventoryData> selectSecuritiesClosedPayInventory(String dateTime,String fundId);

    /**
     * 查询现金应收应付库存
     * @param dateTime
     * @param fundId
     * @return
     */
    public List<CashClosedPayInventoryData> selectCashClosedPayInventory(String dateTime, String fundId);
}
