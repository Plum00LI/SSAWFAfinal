package com.ssaw.ReportManagement.mapper;

import com.ssaw.DayEndProcessing.entity.ValueStatistics;
import com.ssaw.ReportManagement.entity.FundInvestPlateTable;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * create by: 佘高鹏
 * description: TODO
 * 基金投资板块报表持久层
 * create time: 2020/9/21 11:23
 * version number 1.0
  * @Param: null
 * @return
 */
@Mapper
public interface FundInvestPlateTableMapper {
    /**
     * 证券板块查询方法
     * @param valueStatisticsDate 时间
     * @param valueStatisticsDateTwo 行情时间
     * @param fundId  基金ID
     * @return
     */
    public List<FundInvestPlateTable> selectFundInvestPlate(String valueStatisticsDate,String fundId,String valueStatisticsDateTwo);

    /**
     * 资产净值查询方法
     * @param valueStatisticsDate 时间
     * @param fundId 基金ID
     * @return
     */
    public ValueStatistics selectValue(String valueStatisticsDate, String fundId);
}
