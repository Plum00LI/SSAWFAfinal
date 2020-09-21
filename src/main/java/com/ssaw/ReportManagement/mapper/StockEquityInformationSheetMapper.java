package com.ssaw.ReportManagement.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * ClassName:    StockEquityInformationSheetMapper
 * Package:    com.ssaw.ReportManagement.mapper
 * Description: 股票权益数据表
 * Version:     1.0
 * Datetime:    2020/9/18 14:51
 * Author:   阙魁
 */
@Mapper
public interface StockEquityInformationSheetMapper {

    void selectStockEquityInformationSheet(Map map);
}
