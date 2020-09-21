package com.ssaw.InventoryManagement.mapper;

import com.ssaw.InventoryManagement.entity.SecuritiesInventory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
*@program: TescComment
*@Description:证券库存Dao类
*@author: 瞿平
*@version:1.0
*@create: 2020-09-01
*/
@Mapper
public interface SecuritiesInventoryMapper {
    /**
     * 查询
     * @return 返回一个集合
     */
    List<SecuritiesInventory> selectSecuritiesInventory();

    /**
     * 增加
     * @param securitiesInventory 证券库存实体类
     * @return 返回一个int类型参数
     */
    int insertSecuritiesInventory(SecuritiesInventory securitiesInventory);

    /**
     * 删除
     * @param securitiesInventoryId 证券库存Id
     * @return 返回一个int类型参数
     */
    int deleteSecuritiesInventory(List securitiesInventoryId);

    /**
     * 修改
     * @param stock 证券库存实体类
     * @return 返回一个int类型参数
     */
    int updateSecuritiesInventory(SecuritiesInventory stock);

    /**
     * 分页查询
     * @param map 结果集
     * @return
     */
    void selectSecuritiesInventoryInfo(Map map);

    void deleteSecuritiesInventoryDate(String securitiesId,String dateTime,String fundId);

}
