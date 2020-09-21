package com.ssaw.ReportManagement.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * ClassName:    StockPriceFluctuationTableMapper
 * Package:    com.ssaw.ReportManagement.mapper
 * Description: 股票价格波动表
 * Version:     1.0
 * Datetime:    2020/9/18 14:51
 * Author:   阙魁
 */
@Mapper
public interface StockPriceFluctuationTableMapper {
    void SelectStockPrice(Map map);
}
