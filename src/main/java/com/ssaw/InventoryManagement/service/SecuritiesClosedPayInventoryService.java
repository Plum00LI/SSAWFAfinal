package com.ssaw.InventoryManagement.service;

import com.ssaw.InventoryManagement.entity.SecuritiesClosedPayInventory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * ClassName:证券应收应付库存
 * Datetime:2020-09-14
 * Author:sunH
 */
@Service

public interface SecuritiesClosedPayInventoryService {
    /**
     *
     * @param pageSize  每页条数
     * @param page    页数
     * @param securitiesType    证券应收应付类型
     * @param dateTime  日期
     * @return
     */
    public  Map<String,Object> selectSecuritiesClosedPayInventory(String pageSize,String page,String securitiesType,String dateTime);

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
    public int deleteSecuritiesClosedPayInventory(String securitiesClosedPayInventoryId);


    void deleteSecuritiesClosedPayInventoryDate(String securitiesId,String toDay,Integer securitiesType);
}
