package com.ssaw.DayEndProcessing.service;

import com.ssaw.DayEndProcessing.entity.AssetValuation;
import com.ssaw.DayEndProcessing.entity.AssetValuationData;
import com.ssaw.InventoryManagement.entity.CashClosedPayInventory;
import com.ssaw.InventoryManagement.entity.SecuritiesClosedPayInventory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * @program: TescComment
 * @Description:资产估值Biz类
 * @author: 瞿平
 * @version:1.0
 * @create: 2020-09-01
 */
@Service
@Transactional
public interface AssetValuationService {
    //查询表格内容
    public List<AssetValuationData> selectBiaoge();

    //查询应收应付状态
    public List<AssetValuation> selectValuationProcessing();
    //证券库存join行情表查询
    public HashMap selectStockarket(String fundId,String dateTime);
    //删除证券应收应付库存
    public int deleteSecuritiesClosedPayInventory(SecuritiesClosedPayInventory securitiesClosedPayInventory);

    //查交易数据 按证券代码分组 插入证券应收应付库存
    public HashMap selectTransactionData(String dateTime);
    //查交易数据后 按条件删除 证券应收应付表的内容
    public int deleteSecuritiesClosedPayInventoryTwo(SecuritiesClosedPayInventory securitiesClosedPayInventory);

    //查询ta交易数据
    public HashMap selectTaTransaction(String dateTime,String fundId);

    //删除现金应收应付
    public int deleteCashClosedPaylnventory(CashClosedPayInventory cashClosedPayInventory);
}
