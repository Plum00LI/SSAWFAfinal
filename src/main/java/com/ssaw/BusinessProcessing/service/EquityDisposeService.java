package com.ssaw.BusinessProcessing.service;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @program:TescComment
 * @Description:实体类
 * @author:孙浩
 * @create:2020-09-01
 */
@Service
public interface EquityDisposeService {
    public Map<String,Object> selectEquityDispose(String pageSize, String page, String equitiesType,String equitiesExright, String disposeStatus);
    public int updateEquityDispose(String equityDisPose);
    public int updateEquityDisposeTwo(String equityDisPose);

}
