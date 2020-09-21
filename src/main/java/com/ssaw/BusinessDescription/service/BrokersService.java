package com.ssaw.BusinessDescription.service;

import com.ssaw.BusinessDescription.entity.Brokers;

import java.util.Map;

/**
 * @program:TescComment
 * @Description:业务
 * @author:孙浩
 * @create:2020-09-07
 */
public interface BrokersService {
    public int insertBrokers(Brokers brokers);
    public int deleteBrokers(String brokersId);
    public int updateBrokers(Brokers brokers);
    public Map<String,Object> selectBrokers(String pageSize, String page, String brokersName);
}
