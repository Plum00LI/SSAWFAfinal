package com.ssaw.ReportManagement.service.impl;

import com.ssaw.GlobalManagement.util.DateTimeUtil;
import com.ssaw.ReportManagement.entity.DifferenceReport;
import com.ssaw.ReportManagement.mapper.DifferenceReportMapper;
import com.ssaw.ReportManagement.service.DifferenceReportService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program:TescComment
 * @Eescription:成交清算轧差实现类
 * @author:黄庆
 * @Version:1.0
 * @create:2020-09-01
 */
@Service
@Transactional
public class DifferenceReportServiceImpl implements DifferenceReportService {

    @Resource
    DifferenceReportMapper  differenceReportMapper;
    @Override
    public List<DifferenceReport> selectDifferenceReport(String dateTime) {
        if (dateTime!=null && !dateTime.equals("")){
            dateTime=dateTime;
        }else {
            dateTime= DateTimeUtil.getSystemDateTime("yyyy-MM-dd");
        }

        return differenceReportMapper.selectDifferenceReport(dateTime);
    }
}
