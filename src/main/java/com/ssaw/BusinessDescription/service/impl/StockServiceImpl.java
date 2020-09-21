package com.ssaw.BusinessDescription.service.impl;

import com.ssaw.BusinessDescription.entity.Securities;
import com.ssaw.BusinessDescription.entity.Stock;
import com.ssaw.BusinessDescription.mapper.StockMapper;
import com.ssaw.BusinessDescription.service.StockService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 股票板块
 * @type stock的service的实现类
 * @author fusaiying
 * @date 2020-09-01
 * @version 1.0
 */
@Service
public class StockServiceImpl implements StockService {

    @Resource
    StockMapper stockMapper;

    /**查询所有
     * @return
     */
    @Override
    public List<Securities> selectStock() {
        return stockMapper.selectStock();
    }
    /**查询
     * 证券信息引用
     * */
    @Override
    public List<Stock> selectSonStock() {
        return stockMapper.selectSonStock();
    }

    /**增加
     * @param stock
     * @return
     */
    @Override
    public int insertStock(Stock stock) {
        int i = stockMapper.insertStock(stock);
        return i;
    }
//删除
    @Override
    public int deleteStock(String stockId) {

        int i = stockMapper.deleteStock(stockId);
        return i;
    }
//改值
    @Override
    public int updateStock(Stock stock) {

        int i = stockMapper.updateStock(stock);
        return i;
    }
    /**
     * 查询父类
     */
    @Override
    public List<Stock> selectParentStock() {
        return stockMapper.selectParentStock();
    }
}
