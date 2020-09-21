package com.ssaw.DayEndProcessing.mapper;

import com.ssaw.DayEndProcessing.entity.AssetValuation;
import com.ssaw.InventoryManagement.entity.CashClosedPayInventory;
import com.ssaw.InventoryManagement.entity.SecuritiesClosedPayInventory;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * @program: TescComment
 * @Description:资产估值Dao类
 * @author: 瞿平
 * @version:1.0
 * @create: 2020-09-01
 */
@Mapper
public interface AssetValuationMapper {
    //查询应收应付状态
    public List<AssetValuation> selectValuationProcessing();
    //证券库存关联行情表查询
    public void selectStockarket(HashMap hashMap);
    //删除证券应收应付库存的方法
    public int deleteSecuritiesClosedPayInventory(SecuritiesClosedPayInventory securitiesClosedPayInventory);
    //查交易数据 按证券代码分组 插入证券应收应付库存
    public void selectTransactionData(HashMap hashMap);
    //查交易数据后 按条件删除 证券应收应付表的内容
    public int deleteSecuritiesClosedPayInventoryTwo(SecuritiesClosedPayInventory securitiesClosedPayInventory);
    //查询ta交易数据 按账户分组

    public void selectTaTransaction(HashMap hashMap);

    //按条件删除应收应付库存

    public int deleteCashClosedPaylnventory(CashClosedPayInventory cashClosedPayInventory);
}
