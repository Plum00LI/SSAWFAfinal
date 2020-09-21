package com.ssaw.BusinessDescription.mapper;

import com.ssaw.BusinessDescription.entity.Securities;
import com.ssaw.BusinessDescription.entity.Stock;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 股票板块
 * @type stock的dao层
 * @author fusaiying
 * @date 2020-09-01
 * @version 1.0
 */
@Mapper
public interface StockMapper {
    /**
     * 查询所有
     */
    public List<Securities> selectStock();
    /**
     * 查子类
     * 证券信息引用
     */
    public List<Stock> selectSonStock();
    /**
     * 增加
     * @param stock
     */
    public int insertStock(Stock stock);

    /**
     * 单个删除
     * 根据板块编号 stockId
     */
    public int deleteStock(String stockId);
    /**
     * 修改
     * 根据板块编号 stockId
     */
    public int updateStock(Stock stock);
    /**
     * 查询父模块
     * selectParentStock
     */
    public List<Stock> selectParentStock();
}
