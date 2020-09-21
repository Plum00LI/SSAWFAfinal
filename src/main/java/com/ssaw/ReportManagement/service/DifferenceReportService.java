package com.ssaw.ReportManagement.service;

import com.ssaw.ReportManagement.entity.DifferenceReport;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program:TescComment
 * @Eescription:成交清算轧差service
 * @author:黄庆
 * @Version:1.0
 * @create:2020-09-01
 */
@Service
public interface DifferenceReportService {

    public List<DifferenceReport> selectDifferenceReport(String dateTime);
}
