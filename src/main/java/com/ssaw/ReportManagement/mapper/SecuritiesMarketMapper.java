package com.ssaw.ReportManagement.mapper;

import com.ssaw.ReportManagement.entity.SecuritiesMarket;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName:    SecuritiesMarketMapper
 * Package:    com.ssaw.ReportManagement.mapper
 * Description: 证券市场变动表
 * Version:
 * Datetime:    2020/9/17   11:44
 * Author:   SYT
 */
@Mapper
public interface SecuritiesMarketMapper {

    public List<SecuritiesMarket> selectSecuritiesMarket(String dateTime);

//    public double selectProjectId(String dateTime,String fundId);
}
