package com.ssaw.BusinessData.service;

import com.ssaw.BusinessData.entity.EquityData;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @program:权益数据模块
 * @Description:Biz层接口
 * @author:孙浩
 * @create:2020-09-01
 */
@Service
public interface EquityDataService {
    public int insertEquityData(EquityData equityData);
    public int  deleteEquityData(String equityDataId);
    public int updateEquityData(EquityData equityData);
    public Map<String,Object> selectEquityData(String pageSize,String page,String equitiesType,String equitiesExright);
}
