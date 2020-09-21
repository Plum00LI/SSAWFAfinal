package com.ssaw.ReportManagement.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @program: TescComment
 * @Description:成交清算日报表Biz类
 * @author: 瞿平
 * @version:1.0
 * @create: 2020-09-16
 */
@Service
public interface ClosingDateService {

    /**
     * 查询成交清算数据
     * @param dateTime
     * @return
     */
    public HashMap selectClosingDate(String dateTime);
}
