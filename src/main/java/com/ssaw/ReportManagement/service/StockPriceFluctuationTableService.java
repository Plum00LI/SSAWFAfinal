package com.ssaw.ReportManagement.service;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * ClassName:    StockPriceFluctuationTableService
 * Package:    com.ssaw.ReportManagement.Service
 * Description: 股票价格波动表
 * Version:     1.0
 * Datetime:    2020/9/18 14:51
 * Author:   阙魁
 */
@Service
public interface StockPriceFluctuationTableService {
    public Map<String,Object> SelectStockPrice(String pageSize, String page, String dateTime );
}
