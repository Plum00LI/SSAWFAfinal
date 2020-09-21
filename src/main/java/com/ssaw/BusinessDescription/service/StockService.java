package com.ssaw.BusinessDescription.service;

import com.ssaw.BusinessDescription.entity.Securities;
import com.ssaw.BusinessDescription.entity.Stock;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 股票板块
 * @type stock的service类
 * @author fusaiying
 * @date 2020-09-01
 * @version 1.0
 */

@Service
public interface StockService {
    /**
     * 查询所有
     */
    public List<Securities> selectStock();
    /**
     * 增加
     * @param stock
     */
    public int insertStock(Stock stock);
    /**
     * 查子类
     * 证券信息引用
     */
    public List<Stock> selectSonStock();
    /**
     * 删除
     * 根据板块编号 stockId
     */
    public int deleteStock(String stockId);
    /**
     * 修改
     * 根据板块编号 stockId
     */
    public int updateStock(Stock stock);
    /**
     * 查询父类
     */
    public List<Stock> selectParentStock();
}
