package com.ssaw.DayEndProcessing.service.impl;

import com.ssaw.DayEndProcessing.entity.OperationValueStatistics;
import com.ssaw.DayEndProcessing.mapper.OperationValueStatisticsMapper;
import com.ssaw.DayEndProcessing.service.OperationValueStatisticsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * create by: 佘高鹏
 * description: TODO
 * 净值统计现金模块业务层实现类
 * create time: 2020/9/13 19:05
 * version number 1.0
  * @Param: null
 * @return
 */
@Service
@Transactional
public class OperationValueStatisticsServiceImpl implements OperationValueStatisticsService {
    @Resource
    OperationValueStatisticsMapper operationValueStatisticsMapper;
    @Override
    public List<OperationValueStatistics> selectOperationValueStatistics(String dateTime, String fundID, int type) {

        return operationValueStatisticsMapper.selectOperationValueStatistics(dateTime,fundID,type);
    }

    @Override
    public List<OperationValueStatistics> selectOperationTA(String accountID, int type, int typeTwo, String dateTime, String fundID) {
        return operationValueStatisticsMapper.selectOperationTA(accountID,type,typeTwo,dateTime,fundID);
    }

    @Override
    public List<OperationValueStatistics> selectOperationCost(String accountID, String dateTime, String fundID, int type) {
        return operationValueStatisticsMapper.selectOperationCost(accountID,dateTime,fundID,type);
    }
}
