package com.ssaw.GlobalManagement.log.service;

import com.ssaw.GlobalManagement.log.entity.SysLog;

import java.util.Map;

public interface LogService {
    public int insertLog(SysLog log);
    public Map<String, Object> selectLog(String limit,String page);
}
