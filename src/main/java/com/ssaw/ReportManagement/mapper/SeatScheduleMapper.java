package com.ssaw.ReportManagement.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface SeatScheduleMapper {
    /**
     * 查询交易数据
     * @return
     */
    void selectSeatSchedule(Map map);
}
