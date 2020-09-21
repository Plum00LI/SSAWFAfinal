package com.ssaw.ReportManagement.mapper;

import com.ssaw.ReportManagement.entity.DifferenceReport;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @program:TescComment
 * @Eescription:成交清算轧差持久层
 * @author:黄庆
 * @Version:1.0
 * @create:2020-09-16
 */
@Mapper
public interface DifferenceReportMapper {

   public List<DifferenceReport> selectDifferenceReport(String dateTime);
}
