package com.ssaw.GlobalManagement.log.mapper;

import com.ssaw.GlobalManagement.log.entity.SysLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface LogMapper {
    public int insertLogInfo(SysLog log);
    public void selectLogInfo(Map map);
}
