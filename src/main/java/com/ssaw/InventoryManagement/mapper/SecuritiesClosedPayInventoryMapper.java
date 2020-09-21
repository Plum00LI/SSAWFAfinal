package com.ssaw.InventoryManagement.mapper;

import com.ssaw.InventoryManagement.entity.SecuritiesClosedPayInventory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * ClassName:证券应收应付库存
 * Datetime:2020-09-14
 * Author:sunH
 */
@Mapper
public interface SecuritiesClosedPayInventoryMapper {
    /**
     * 按条件分页查询所有证券应收应付库存
     * @param map
     */
    public void selectSecuritiesClosedPayInventory(Map map);

    /**
     * 增加证券应收应付库存
     * @param securitiesClosedPayInventory
     * @return
     */
    public int insertSecuritiesClosedPayInventory(SecuritiesClosedPayInventory securitiesClosedPayInventory);

    /**
     * 修改证券应收应付库存信息
     * @param securitiesClosedPayInventory
     * @return
     */
    public int updateSecuritiesClosedPayInventory(SecuritiesClosedPayInventory securitiesClosedPayInventory);

    /**
     * 批量删除
     * @param securitiesClosedPayInventoryId
     * @return
     */
    public int deleteSecuritiesClosedPayInventory(List securitiesClosedPayInventoryId);

    /**
     * 根据时间删除
     * @param dateTime
     */
    public void deleteSecuritiesClosedPayInventoryDate(String securitiesid,String dateTime,String fundId);

    void deleteSecuritiesClosedPayInventoryDate(String securitiesId, String toDay, Integer securitiesType);
}
