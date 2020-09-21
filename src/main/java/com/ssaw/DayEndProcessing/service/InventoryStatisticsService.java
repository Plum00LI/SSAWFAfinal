package com.ssaw.DayEndProcessing.service;

import com.ssaw.DayEndProcessing.entity.InventoryStatistics;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName:    InventoryStatisticsService
 * Package:    com.ssaw.DayEndProcessing.service
 * Description:  库存统计
 * Version:
 * Datetime:    2020/9/8   11:28
 * Author:   SYT
 */
@Service
public interface InventoryStatisticsService {
    /**
     * 查询库存
     * @param dateTime
     * @param invId
     * @return
     */
    public List<InventoryStatistics> selectInventoryStatistics(String fundId, String dateTime, String invId);

}
