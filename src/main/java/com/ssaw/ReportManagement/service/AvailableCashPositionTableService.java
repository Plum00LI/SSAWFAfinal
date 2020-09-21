package com.ssaw.ReportManagement.service;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * ClassName:    AvailableCashPositionTableService
 * Package:    com.ssaw.ReportManagement.Service
 * Description: 可用现金头寸表
 * Version:     1.0
 * Datetime:    2020/9/18 14:51
 * Author:   阙魁
 */
@Service
public interface AvailableCashPositionTableService {
        public Map<String,Object> selectAvailable(String pageSize, String page,String dateTime);

}
