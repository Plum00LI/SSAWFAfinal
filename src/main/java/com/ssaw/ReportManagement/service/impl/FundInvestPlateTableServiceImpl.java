package com.ssaw.ReportManagement.service.impl;

import com.ssaw.DayEndProcessing.entity.ValueStatistics;
import com.ssaw.ReportManagement.entity.FundInvestPlateTable;
import com.ssaw.ReportManagement.mapper.FundInvestPlateTableMapper;
import com.ssaw.ReportManagement.service.FundInvestPlateTableService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * create by: 佘高鹏
 * description: TODO
 * 基金投资板块报表业务层实现类
 * create time: 2020/9/21 11:36
 * version number 1.0
  * @Param: null
 * @return
 */
@Service
@Transactional
public class FundInvestPlateTableServiceImpl implements FundInvestPlateTableService {
    @Resource
    FundInvestPlateTableMapper fundInvestPlateTableMapper;


    @Override
    public List<FundInvestPlateTable> selectFundInvestPlate(String valueStatisticsDate, String fundId, String valueStatisticsDateTwo) {
        return fundInvestPlateTableMapper.selectFundInvestPlate(valueStatisticsDate,fundId,valueStatisticsDateTwo);
    }

    @Override
    public ValueStatistics selectValue(String valueStatisticsDate, String fundId) {
        return fundInvestPlateTableMapper.selectValue(valueStatisticsDate,fundId);
    }
}
