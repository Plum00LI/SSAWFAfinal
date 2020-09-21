package com.ssaw.ReportManagement.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * ClassName:    AvailableCashPositionTableMapper
 * Package:    com.ssaw.ReportManagement.mapper
 * Description: 可用现金头寸表
 * Version:     1.0
 * Datetime:    2020/9/18 14:51
 * Author:   阙魁
 */
@Mapper
public interface AvailableCashPositionTableMapper {
    void selectAvailable(Map map);
}
