package com.ssaw.ReportManagement.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface SeatScheduleService {
    /**
     * 查询交易数据
     * @return List<TransactionData>
     */
    public Map<String,Object> selectSeatSchedule(String pageSize, String page, String seateId, String datefind, String settlementDate);
}
