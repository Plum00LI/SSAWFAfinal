package com.ssaw.ReportManagement.service;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * ClassName:    StockEquityInformationSheetService
 * Package:    com.ssaw.ReportManagement.Service
 * Description: 股票权益数据表
 * Version:     1.0
 * Datetime:    2020/9/18 14:51
 * Author:   阙魁
 */
@Service
public interface StockEquityInformationSheetService {
    public Map<String,Object> selectStockEquityInformationSheet(String pageSize, String page, String startTime,String endTime,String equitiesType );
}
