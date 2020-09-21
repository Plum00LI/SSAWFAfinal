package com.ssaw.DayEndProcessing.service.impl;

import com.ssaw.DayEndProcessing.entity.SecuritiesValueStatistics;
import com.ssaw.DayEndProcessing.mapper.SecuritiesValueStatisticsMapper;
import com.ssaw.DayEndProcessing.service.SecuritiesValueStatisticsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * create by: 佘高鹏
 * description: TODO
 * 证券净值统计查询业务层实现类
 * create time: 2020/9/11 17:09
 * version number 1.0
  * @Param: null
 * @return
 */
@Service
@Transactional
public class SecuritiesValueStatisticsServiceImpl implements SecuritiesValueStatisticsService{
    @Resource
    SecuritiesValueStatisticsMapper securitiesValueStatisticsMapper;

    @Override
    public List<SecuritiesValueStatistics> selectSecuritiesValueStatistics(String dateTime, String fundID, String dateTimeTwo) {

        return securitiesValueStatisticsMapper.selectSecuritiesValueStatistics(dateTime,fundID,dateTimeTwo);
    }

    @Override
    public List<SecuritiesValueStatistics> selectDebentureInterestValueStatistics(String dateTime, String fundID, int type) {
        return securitiesValueStatisticsMapper.selectDebentureInterestValueStatistics(dateTime,fundID,type);
    }

    @Override
    public List<SecuritiesValueStatistics> selectSecuritiesClearingValueStatistics(String dateTime, String fundID, int type, int flag) {
        return securitiesValueStatisticsMapper.selectSecuritiesClearingValueStatistics(dateTime,fundID,type,flag);
    }


}
