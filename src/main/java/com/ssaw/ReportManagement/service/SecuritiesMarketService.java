package com.ssaw.ReportManagement.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * ClassName:    SecuritiesMarketService
 * Package:    com.ssaw.ReportManagement.service
 * Description:     证券市场变动报表
 * Version:
 * Datetime:    2020/9/19   10:16
 * Author:   SYT
 */
@Service
public interface SecuritiesMarketService {

    public HashMap selectSecuritiesMarket(String dateTime);

}
