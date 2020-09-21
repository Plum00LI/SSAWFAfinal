package com.ssaw.BusinessDescription.mapper;

import com.ssaw.BusinessDescription.entity.Brokers;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @program:TescComment
 * @Description:持久层
 * @author:孙浩
 * @create:2020-09-07
 */
@Mapper
public interface BrokersMapper {
    public int insertBrokers(Brokers brokers);
    public int deleteBrokers(List brokersId);
    public int updateBrokers(Brokers brokers);
    public void selectBrokers(Map map);
}



